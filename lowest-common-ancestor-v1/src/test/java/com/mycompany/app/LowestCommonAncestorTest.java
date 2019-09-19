package com.mycompany.app;

// TODO figure out how to format imports
import main.java.com.mycompany.utils.Node;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class LowestCommonAncestorTest
{

    @Test
    public void givenEmptyTreeShouldReturnNull() {
        Node result = LowestCommonAncestor.getLowestCommonAncestor(null, null, null);
        assertNull(result);
    }

    @Test
    public void givenEmptyFirstNodeToFindAncestorOfShouldReturnNull() {
        Node root = new Node(5);
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, null, root);
        assertNull(result);
    }

    @Test
    public void givenEmptySecondNodeToFindAncestorOfShouldReturnNull() {
        Node root = new Node(5);
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, null, root);
        assertNull(result);
    }
    
    @Test
    public void givenTreeWithRootAndTwoChildrenShouldReturnRootAsAncestorOfChildren() {
        // Make the tree
        Node root = new Node(1);
        Node leftNode = new Node(2);
        Node rightNode = new Node(3);

        root.setLeft(leftNode);
        root.setRight(rightNode);

        // Get the common ancestor
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, leftNode, rightNode);
        assertEquals(root, result);
    }

    // TODO what should happen if root in common ancestor list?


}
