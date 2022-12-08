package com.wjhub.leetcode.algorithm.businessFrameWork;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class BusinessFrameWork {

    // 场景注解
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Scene {
        String value();
    }

    // 业务注解
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Business {
        String value();

        int order();
    }


    // 执行业务
    public Map<String, Object> execute(String scene, Map<String, Object> params) throws Exception {
        // 获取所有实现类
        ServiceLoader<Object> loader = ServiceLoader.load(Object.class);

        // 存储所有符合条件的业务实现类
        List<Object> businessList = new ArrayList<Object>();
        for (Object obj : loader) {
            // 获取类的注解
            Annotation[] annotations = obj.getClass().getAnnotations();
            for (Annotation annotation : annotations) {
                // 如果是场景注解
                if (annotation instanceof Scene) {
                    Scene s = (Scene) annotation;
                    // 如果是指定的场景
                    if (s.value().equals(scene)) {
                        // 添加到列表中
                        businessList.add(obj);
                    }
                }
            }
        }

        // 按照业务注解的order属性值排序
        businessList.sort((o1, o2) -> {
            Business b1 = o1.getClass().getAnnotation(Business.class);
            Business b2 = o2.getClass().getAnnotation(Business.class);
            return b1.order() - b2.order();
        });
        // 存储执行结果
        Map<String, Object> resultMap = new HashMap<>();

        // 执行所有符合条件的业务实现类
        for (Object obj : businessList) {
            // 获取所有方法
            Method[] methods = obj.getClass().getMethods();
            for (Method method : methods) {
                // 获取方法的业务注解
                Business business = method.getAnnotation(Business.class);
                // 如果方法有业务注解
                if (business != null) {
                    // 执行方法
                    Object result = method.invoke(obj, params);
                    // 将结果存储在Map中
                    resultMap.put(business.value(), result);
                }
            }
        }
        return resultMap;
    }
}
