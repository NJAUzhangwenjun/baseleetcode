package com.wjhub.leetcode.algorithm.tmf.test;

import com.wjhub.leetcode.algorithm.tmf.BizCode;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;
import java.util.HashMap;
import java.util.Map;

@Service
@BizCode("pdd")
@Priority(10)
public class PddEnrichProductInfoSpiImpl implements EnrichProductInfoSpi {
    @Override
    public Map<String, Object> getBizInfo(Long productId) {
        Map<String, Object> bizInfo = new HashMap<>();
        bizInfo.put("memo", "pdd测试商品，请勿购买");
        return bizInfo;
    }
}
