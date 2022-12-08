package com.wjhub.leetcode.algorithm.myBusinessFrameWork.service;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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