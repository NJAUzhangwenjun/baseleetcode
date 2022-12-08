package com.wjhub.leetcode.algorithm.tmf.test.model;

import lombok.Data;

import java.util.Map;

/**
 * 产品信息
 *
 * @author zhangwenjun
 * @date 2022/11/25
 */
@Data
public class ProductInfo {
    private String bizCode;
    private Long productId;
    private Map<String, Object> bizInfo;
}
