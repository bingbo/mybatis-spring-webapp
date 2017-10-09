package com.ibingbo.design.pattern.chain_of_responsibility;

import java.util.Map;

public class DefaultCheckerChain implements CheckerChain {

    private Checker[] checkers = new Checker[0];

    /**
     * checker链现在执行到了哪个位子
     */
    private int pos = 0;

    /**
     * 当前checker链中有checker数量
     */
    private int n = 0;
    public static final int INCREMENT = 10;

    public void doCheck(Map params) throws Exception {
        internalDoCheck(params);
    }

    private void internalDoCheck(Map params) throws Exception {
        if (pos < n) {
            try {
                Checker checker = checkers[pos++];
                checker.doCheck(params, this);
            } catch (Exception e) {
                throw e;
            }
            return;
        }
    }

    public void addChecker(Checker checker) {
        for (Checker chk : checkers) {
            if (chk == checker) {
                return;
            }
        }
        if (n == checkers.length) {
            Checker[] newCheckers = new Checker[n + INCREMENT];
            System.arraycopy(checkers, 0, newCheckers, 0, n);
            checkers = newCheckers;
        }
        checkers[n++] = checker;
    }
}
