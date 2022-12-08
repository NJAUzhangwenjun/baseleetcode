package com.wjhub.leetcode.algorithm.myBusinessFrameWork.annotation;

import com.wjhub.leetcode.algorithm.myBusinessFrameWork.service.BusinessManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class AnnotationParser implements ApplicationContextAware, InitializingBean {
    @Autowired
    private BusinessManager businessManager;

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> sceneBeanMap = context.getBeansWithAnnotation(Scene.class);
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