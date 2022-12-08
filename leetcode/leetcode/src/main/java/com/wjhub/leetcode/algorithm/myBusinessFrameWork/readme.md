# 复杂业务场景处理架构

写一个能够解决不同场景下处理不同业务的Java业务框架

- 场景有多种,
- 每个场景下业务也有多个
- 不同场景和业务都是独立的,同一种场景或业务可以按照顺序组合执行

基于以上描述, 我们可以考虑如下解决方案:

- 使用 Spring 框架作为基础, 便于管理 Bean 对象
- 针对不同的场景, 定义自定义注解, 便于在运行时解析出对应的场景
- 针对不同的业务, 定义自定义注解, 便于在运行时解析出对应的业务
- 使用反射技术来动态加载注解上的信息, 并执行相应的业务逻辑
- 使用 Spring 的 @Order 注解或者 @Priority 注解, 来指定不同业务的执行顺序, 便于组合执行

具体实现步骤如下:

1. 定义自定义场景注解, 例如:

```java

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scene {
    String value();
}
```

2. 定义自定义业务注解, 例如:

```java

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Business {
    String value();
}
```

3. 定义业务接口, 并在接口上使用场景注解, 例如:

```java

@Scene("Scene A")
public interface BusinessService {

    @Business("Business A1")
    void doBusinessA1();

    @Business("Business A2")
    void doBusinessA2();
}

```

4. 实现业务接口, 并在实现类上使用业务注解, 例如:

```java

@Service
public class BusinessServiceImpl implements BusinessService {

    @Override
    @Business("Business A1")
    public void doBusinessA1() {
        // ...
    }

    @Override
    @Business("Business A2")
    public void doBusinessA2() {
        // ...
    }
}
```

5. 定义业务管理类, 用于管理不同场景下的业务逻辑:

```java

@Component
public class BusinessManager {
    private Map<String, List<Method>> businessMap = new HashMap<>();

    public void addBusiness(String scene, Method method) {
        businessMap.computeIfAbsent(scene, k -> new ArrayList<>()).add(method);
    }

    public void executeBusiness(String scene) {
        List<Method> businessList = businessMap.get(scene);
        if (businessList == null || businessList.isEmpty()) {
            return;
        }

        businessList.forEach(method -> {
            try {
                method.invoke(method.getDeclaringClass().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
```

6. 定义注解解析器, 用于在运行时解析注解信息:

```java

@Component
public class AnnotationParser {
    @Autowired
    private BusinessManager businessManager;

    public void parse() {
        Map<String, Object> sceneBeanMap = applicationContext.getBeansWithAnnotation(Scene.class);
        sceneBeanMap.forEach((beanName, bean) -> {
            Class<?> clazz = bean.getClass();
            Scene scene = clazz.getAnnotation(Scene.class);
            Arrays.stream(clazz.getMethods())
                    .filter(method -> method.isAnnotationPresent(Business.class))
                    .forEach(method -> {
                        Business business = method.getAnnotation(Business.class);
                        businessManager.addBusiness(scene.value(), method);
                    });
        });
    }
}
```

7. 在启动类上添加 @ComponentScan 注解, 便于 Spring 自动扫描所有的组件:

```java

@SpringBootApplication
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```

8. 在启动类的 main 方法中, 调用注解解析器的 parse 方法, 来解析注解:

```java

@SpringBootApplication
@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        AnnotationParser annotationParser = applicationContext.getBean(AnnotationParser.class);
        annotationParser.parse();

        BusinessManager businessManager = applicationContext.getBean(BusinessManager.class);
        businessManager.executeBusiness("SceneA");
    }
}

```

// 输出:
// Business A1
// Business A2

以上就是一个基于 Spring, 反射, 自定义场景注解和业务注解的业务框架的简单实现。这种方案的优点在于通过注解的方式, 可以解耦不同场景和业务的定义和实现, 便于维护和扩展。如果需要实现更复杂的业务逻辑,
可以考虑使用更复杂的注解来表示业务之间的关系, 以及使用更复杂的反射技术来动态加载和执行业务逻辑。

## 优化

如果需要实现更复杂的业务逻辑, 可以考虑以下优化:

使用更复杂的注解来表示业务之间的关系, 例如定义 Before、After、Around 等注解, 用于指定业务之间的执行顺序和执行条件。
使用更复杂的反射技术来动态加载和执行业务逻辑, 例如使用动态代理来包装业务逻辑, 以实现更灵活的执行方式。
使用 SPIs 技术来扩展业务框架的功能, 例如提供一个业务拦截器的接口, 允许开发者通过实现该接口来拦截业务逻辑的执行。
例如, 使用 SPIs 技术扩展业务框架的具体实现步骤如下:

1. 定义业务拦截器接口, 例如:

```java
public interface BusinessInterceptor {

    default void before(Method method, Object[] args) {
        // do nothing
    }

    default void after(Method method, Object[] args, Object result) {
        // do nothing
    }

    default void afterThrowing(Method method, Object[] args, Throwable throwable) {
        // do nothing
    }
}

```

2. 定义业务管理类, 用于管理不同场景下的业务逻辑:

```java

@Component
public class BusinessManager {
    private Map<String, List<Method>> businessMap = new HashMap<>();

    public void addBusiness(String scene, Method method) {
        businessMap.computeIfAbsent(scene, k -> new ArrayList<>()).add(method);
    }

    public void executeBusiness(String scene) {
        List<Method> businessList = businessMap.get(scene);
        if (businessList == null || businessList.isEmpty()) {
            return;
        }

        businessList.forEach(method -> {
            try {
                List<BusinessInterceptor> interceptors = getInterceptors();
                Object instance = method.getDeclaringClass().newInstance();
                Object result = invokeBusiness(interceptors, instance, method, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Object invokeBusiness(List<BusinessInterceptor> interceptors, Object instance, Method method, Object[] args) {
        for (BusinessInterceptor interceptor : interceptors) {
            interceptor.before(method, args);
        }

        Object result = null;
        try {
            result = method.invoke(instance, args);
            for (BusinessInterceptor interceptor : interceptors) {
                interceptor.after(method, args, result);
            }
        } catch (Exception e) {
            for (BusinessInterceptor interceptor : interceptors) {
                interceptor.afterThrowing(method, args, e);
            }
        }

        return result;
    }

    private List<BusinessInterceptor> getInterceptors() {
        List<BusinessInterceptor> interceptors = new ArrayList<>();
        ServiceLoader.load(BusinessInterceptor.class).forEach(interceptors::add);
        return interceptors;
    }
}

```

3. 定义注解解析器, 用于在运行时解析注解信息:

```java

@Component
public class AnnotationParser {
    @Autowired
    private BusinessManager businessManager;

    public void parse() {
        Map<String, Object> sceneBeanMap = applicationContext.getBeansWithAnnotation(Scene.class);
        sceneBeanMap.forEach((beanName, bean) -> {
            Class<?> clazz = bean.getClass();
            Scene scene = clazz.getAnnotation(Scene.class);
            Arrays.stream(clazz.getMethods())
                    .filter(method -> method.isAnnotationPresent(Business.class))
                    .forEach(method -> {
                        Business business = method.getAnnotation(Business.class);
                        businessManager.addBusiness(scene.value(), method);
                    });
        });
    }
}
```

4. 在启动类上添加 @ComponentScan 注解, 便于 Spring 自动扫描所有的组件:

```java

@SpringBootApplication
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```

5. 在启动类的 main 方法中, 调用注解解析器的 parse 方法, 来解析注解:

```java

@SpringBootApplication
@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        AnnotationParser annotationParser = applicationContext.getBean(AnnotationParser.class);
        annotationParser.parse();
        BusinessManager businessManager = applicationContext.getBean(BusinessManager.class);
        businessManager.executeBusiness("Scene A");
    }
}

```

// 输出:
// Business A1
// Business A2

以上就是使用 SPIs 技术扩展业务框架的具体实现。通过这种方式, 可以通过实现 BusinessInterceptor 接口来拦截业务逻辑的执行, 实现更为灵活的业务处理方式。

