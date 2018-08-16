package com.ibingbo.design.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * NodeContext
 *
 * @author zhangbingbing
 * @date 2018/8/16
 */
public class NodeContext {

    private List<Node> nodes = new ArrayList<Node>();

    public void accept(Visitor visitor) {
        for (Node node : this.nodes) {
            node.accept(visitor);
        }
    }

    public void add(Node node) {
        this.nodes.add(node);
    }
}
