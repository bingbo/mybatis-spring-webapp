package com.ibingbo.design.pattern.chain_of_responsibility;

import java.util.Map;

import org.springframework.context.ApplicationContext;

/**
 * 检查链工厂类
 *
 * @author zhangbingbing
 * @title DefaultCheckerFactory
 * @date 2017/9/7
 */
public final class DefaultCheckerFactory {

    private DefaultCheckerFactory() {
    }

    public static CheckerChain createCheckerChain(ApplicationContext context) {
        CheckerChain checkerChain = new DefaultCheckerChain();
        Map<String, Checker> checkerMap = context.getBeansOfType(Checker.class);
        if (checkerMap != null && checkerMap.size() > 0) {
            for (Checker checker : checkerMap.values()) {
                checkerChain.addChecker(checker);
            }
        }
        return checkerChain;
    }

}
