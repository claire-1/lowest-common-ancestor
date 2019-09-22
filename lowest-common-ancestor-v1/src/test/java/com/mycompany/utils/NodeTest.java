package com.mycompany.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NodeTest {

    @Test
    public void givenNewLeftNodeShouldSetLeftNode() {
        Node original = new Node(5); 
        Node leftNode = new Node(4);
        original.setLeft(leftNode);
        assertEquals(leftNode, original.getLeft());
    }

    @Test
    public void givenNewRightNodeShouldSetRightNode() {
        Node original = new Node(5);
        Node rightNode = new Node(2);
        original.setRight(rightNode);
        assertEquals(rightNode, original.getRight());
    }
}