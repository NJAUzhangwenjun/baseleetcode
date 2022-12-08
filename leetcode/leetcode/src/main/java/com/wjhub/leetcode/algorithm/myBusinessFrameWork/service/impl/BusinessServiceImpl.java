package com.wjhub.leetcode.algorithm.myBusinessFrameWork.service.impl;

import com.wjhub.leetcode.algorithm.myBusinessFrameWork.annotation.Business;
import com.wjhub.leetcode.algorithm.myBusinessFrameWork.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Override
    @Business("Business A1")
    public void doBusinessA1() {
        // ...
        log.info("BusinessServiceImpl_doBusinessA1");
    }

    @Override
    @Business("Business A2")
    public void doBusinessA2() {
        // ...
        log.info("BusinessServiceImpl_doBusinessA2");
    }
}