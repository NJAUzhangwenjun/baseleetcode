package com.wjhub.leetcode.algorithm.tmf;

import java.lang.annotation.*;


/**
 *
 * @author zhangwenjun
 * @date 2022/11/25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BizCode {
    String value() default "";
}