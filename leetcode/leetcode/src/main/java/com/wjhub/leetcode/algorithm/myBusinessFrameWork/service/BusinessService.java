package com.wjhub.leetcode.algorithm.myBusinessFrameWork.service;

import com.wjhub.leetcode.algorithm.myBusinessFrameWork.annotation.Business;
import com.wjhub.leetcode.algorithm.myBusinessFrameWork.annotation.Scene;

@Scene("Scene A")
public interface BusinessService {

    @Business("Business A1")
    void doBusinessA1();

    @Business("Business A2")
    void doBusinessA2();
}