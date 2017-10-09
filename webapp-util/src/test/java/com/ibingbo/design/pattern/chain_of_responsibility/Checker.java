package com.ibingbo.design.pattern.chain_of_responsibility;

import java.util.Map;

/**
 * @author zhangbingbing
 * @title Checker
 * @date 17/10/9
 */
public interface Checker {
    void doCheck(Map params, CheckerChain chain);
}
