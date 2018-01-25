package com.ibingbo.design.pattern.interpreter;

/**
 * NonterminalExpression
 *
 * @author zhangbingbing
 * @date 18/1/25
 */
public class NonterminalExpression implements Expression{
    public void interpret(Context context) {
        System.out.println("nonterminal expression");
    }
}
