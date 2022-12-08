package com.wjhub.leetcode.algorithm.tmf;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author zhangwenjun
 * @date 2022/11/25
 */
@AllArgsConstructor
public class SpiLoader {

    private ApplicationContext applicationContext;

    private static final Map<ApplicationContext, SpiLoader> instances = new HashMap<>();

    public static SpiLoader from(ApplicationContext context) {
        return instances.computeIfAbsent(context, SpiLoader::new);
    }

    /**
     *
     * @param spi
     * @param bizCodes
     * @return
     * @param <SPI>
     */
    public <SPI> SpiExecutor<SPI> load(Class<SPI> spi, String... bizCodes) {
        List<SPI> spiList = this.applicationContext.getBeansOfType(spi)
                .values()
                .stream()
                .filter(s -> {
                    if(null == bizCodes || bizCodes.length == 0){
                        return true;
                    }
                    return Optional.ofNullable(AnnotationUtils.findAnnotation(s.getClass(), BizCode.class))
                            .map(bizCode -> Arrays.asList(bizCodes).contains(bizCode.value()))
                            .orElse(false);
                })
                .sorted(AnnotationAwareOrderComparator.INSTANCE)
                .collect(Collectors.toList());
        return new SpiExecutor(spiList);
    }

    @AllArgsConstructor
    public class SpiExecutor<SPI> {
        private List<SPI> spiList;

        public <O> O executeFirstMatched(SpiCallback<SPI, O> callback) {
            List<O> result = reduceExecute(callback,o -> true);
            if(result == null || result.isEmpty()){
                return null;
            }
            return result.get(0);
        }

        public <O> List<O> executeAllMatched(SpiCallback<SPI, O> callback) {
            return reduceExecute(callback,o -> false);
        }

        public <O> O executeFirstResultNotNull(SpiCallback<SPI, O> callback) {
            return reduceExecute(callback, Objects::nonNull)
                    .stream()
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);
        }

        protected <O> List<O> reduceExecute(SpiCallback<SPI, O> callback,Predicate<O> breaker) {
            if (null == spiList || spiList.isEmpty()) {
                return Collections.emptyList();
            }
            List<O> outputs = new ArrayList<>();
            for (SPI spi : spiList) {
                O output = callback.apply(spi);
                outputs.add(output);
                if (null != breaker && breaker.test(output)) {
                    break;
                }
            }
            return outputs;
        }

    }

    public interface SpiCallback<SPI, O> extends Function<SPI, O> {
        O apply(SPI spi);
    }
}