package com.mycompany.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NodeTest {

    @Test
    public void givenNewChildNodeShouldAttachChildNodeToParentNode() {
        Node parent = new Node(5);
        Node child = new Node(4);
        parent.addChild(child);
        assertEquals(1, parent.getChildren().size());
        assertEquals(child, parent.getChildren().get(0));
    }

    // TODO add test for more than one node
    // TODO add test for zero nodes
}