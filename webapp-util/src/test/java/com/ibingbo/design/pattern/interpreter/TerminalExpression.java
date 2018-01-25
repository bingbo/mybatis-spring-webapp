package com.ibingbo.design.pattern.interpreter;

/**
 * TerminalExpression
 *
 * @author zhangbingbing
 * @date 18/1/25
 */
public class TerminalExpression implements Expression{
    public void interpret(Context context) {
        System.out.println("terminal expression");
    }
}
