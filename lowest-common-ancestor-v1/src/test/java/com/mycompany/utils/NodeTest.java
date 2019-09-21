package com.mycompany.utils;

// TODO figure out how to format imports
import com.mycompany.utils.Node;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

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