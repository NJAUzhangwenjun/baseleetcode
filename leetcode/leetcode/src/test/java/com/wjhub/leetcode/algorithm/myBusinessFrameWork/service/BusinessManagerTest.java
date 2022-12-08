package com.wjhub.leetcode.algorithm.myBusinessFrameWork.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BusinessManagerTest {

    @Autowired
    private  BusinessManager businessManager;

    @Test
    public void testExecuteBusiness() {
        businessManager.executeBusiness("Business A1");
        businessManager.executeBusiness("Business A2");
    }

}