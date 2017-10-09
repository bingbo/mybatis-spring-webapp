package com.ibingbo.design.pattern.chain_of_responsibility;

import java.util.Map;

/**
 * @author zhangbingbing
 * @title CheckerChain
 * @date 17/10/9
 */
public interface CheckerChain {

    void doCheck(Map param) throws Exception;
    void addChecker(Checker checker);
}
