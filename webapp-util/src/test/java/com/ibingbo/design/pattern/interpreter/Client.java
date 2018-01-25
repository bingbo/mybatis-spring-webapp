package com.ibingbo.design.pattern.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Client
 *
 * @author zhangbingbing
 * @date 18/1/25
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        List<Expression> list = new ArrayList<Expression>();
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());
        list.add(new TerminalExpression());

        for (Expression expression : list) {
            expression.interpret(context);
        }
    }
}
