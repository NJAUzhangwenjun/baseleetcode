package com.wjhub.leetcode.algorithm.tmf.test;

import com.wjhub.leetcode.algorithm.tmf.SpiLoader;
import com.wjhub.leetcode.algorithm.tmf.test.model.ProductInfo;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 * @author zhangwenjun
 * @date 2022/11/25
 */
@Service
public class EnrichProductInfoAction implements ApplicationContextAware {
    @Setter
    private ApplicationContext applicationContext;

    public void execute() {
        ProductInfo productInfo = new ProductInfo();
        // SPI 的调用
        Map<String, Object> bizInfo = SpiLoader.from(applicationContext)
                .load(EnrichProductInfoSpi.class, productInfo.getBizCode())
                .executeFirstMatched(e -> e.getBizInfo(productInfo.getProductId()));

        productInfo.setBizInfo(bizInfo);
    }
}
