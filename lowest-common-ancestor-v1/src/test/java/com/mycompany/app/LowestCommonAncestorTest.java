package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.mycompany.utils.Node;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class LowestCommonAncestorTest {

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
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, root, null);
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

    @Test
    public void givenTreeWithRootAsFirstNodeToFindAncestorOfShouldReturnRoot() {
        // Make the tree
        Node root = new Node(5);
        Node leftNode = new Node(2);
        root.setLeft(leftNode);

        // Get the common ancestor
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, root, leftNode);
        assertEquals(root, result);
    }

    @Test
    public void givenTreeWithRootAsSecondNodeToFindAncestorOfShouldReturnRoot() {
        // Make the tree
        Node root = new Node(5);
        Node leftNode = new Node(2);
        root.setLeft(leftNode);

        // Get the common ancestor
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, leftNode, root);
        assertEquals(root, result);
    }

    @Test
    public void givenLinearTreeAndNonRootNodesToFindNonRootAncestorOfShouldReturnCorrectNode() {
        // Make the tree
        Node root = new Node(500);
        Node leftNode1 = new Node(200);
        root.setLeft(leftNode1);
        Node leftNode2 = new Node(40);
        leftNode1.setLeft(leftNode2);
        Node leftNode3 = new Node(10);
        leftNode2.setLeft(leftNode3);
        Node leftNode4 = new Node(5);
        leftNode3.setLeft(leftNode4);
        Node leftNode5 = new Node(0);
        leftNode4.setLeft(leftNode5);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, leftNode5, leftNode2);
        // System.out.println("result: " + (result.getData()));
        // TODO what is the correct output when the node is in a line?
        // TODO the class slides suggest that it can't be the a node looking for
        // decendants itself,
        // TODO but the online description says that it can be
        // TODO "The lowest common ancestor between two nodes n1 and n2 is defined as
        // the lowest node in T that has both n1 and n2 as descendants (where we allow a
        // node to be a descendant of itself)"
        // TODO from
        // https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
        // assertEquals(leftNode1, result);
        assertEquals(leftNode2, result); // based on online def
    }

    @Test
    public void givenBinaryTreeWithTwoSubtreesAndNodesInRightSubtreeShouldReturnRightSubtreeRootAsAncestor() {
        // Make the tree - root
        Node root = new Node(15);
        Node leftSubtreeRoot = new Node(10);
        root.setLeft(leftSubtreeRoot);
        Node rightSubtreeRoot = new Node(25);
        root.setRight(rightSubtreeRoot);

        // Make the tree - left subtree
        Node leftSubtreeLeftChild = new Node(8);
        leftSubtreeRoot.setLeft(leftSubtreeLeftChild);
        Node leftSubtreeRightChild = new Node(12);
        leftSubtreeRoot.setRight(leftSubtreeRightChild);

        // Make the tree - right subtree
        Node rightSubtreeLeftChild = new Node(20);
        rightSubtreeRoot.setLeft(rightSubtreeLeftChild);
        Node rightSubtreeRightChild = new Node(30);
        rightSubtreeRoot.setRight(rightSubtreeRightChild);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, rightSubtreeLeftChild, rightSubtreeRightChild);
        assertEquals(rightSubtreeRoot, result);
    }

    @Test
    public void givenBinaryTreeWithTwoSubtreesAndNodesInLeftSubtreeShouldReturnLeftSubtreeRootAsAncestor() {
        // Make the tree - root
        Node root = new Node(15);
        Node leftSubtreeRoot = new Node(10);
        root.setLeft(leftSubtreeRoot);
        Node rightSubtreeRoot = new Node(25);
        root.setRight(rightSubtreeRoot);

        // Make the tree - left subtree
        Node leftSubtreeLeftChild = new Node(8);
        leftSubtreeRoot.setLeft(leftSubtreeLeftChild);
        Node leftSubtreeRightChild = new Node(12);
        leftSubtreeRoot.setRight(leftSubtreeRightChild);

        // Make the tree - right subtree
        Node rightSubtreeLeftChild = new Node(20);
        rightSubtreeRoot.setLeft(rightSubtreeLeftChild);
        Node rightSubtreeRightChild = new Node(30);
        rightSubtreeRoot.setRight(rightSubtreeRightChild);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, leftSubtreeLeftChild, leftSubtreeRightChild);
        assertEquals(leftSubtreeRoot, result);
    }

    @Test
    public void givenBinaryTreeWithTwoSubtreesAndNodesInDifferentSubtreeShouldReturnRootAsAncestor() {
        // Make the tree - root
        Node root = new Node(15);
        Node leftSubtreeRoot = new Node(10);
        root.setLeft(leftSubtreeRoot);
        Node rightSubtreeRoot = new Node(25);
        root.setRight(rightSubtreeRoot);

        // Make the tree - left subtree
        Node leftSubtreeLeftChild = new Node(8);
        leftSubtreeRoot.setLeft(leftSubtreeLeftChild);
        Node leftSubtreeRightChild = new Node(12);
        leftSubtreeRoot.setRight(leftSubtreeRightChild);

        // Make the tree - right subtree
        Node rightSubtreeLeftChild = new Node(20);
        rightSubtreeRoot.setLeft(rightSubtreeLeftChild);
        Node rightSubtreeRightChild = new Node(30);
        rightSubtreeRoot.setRight(rightSubtreeRightChild);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, rightSubtreeLeftChild, leftSubtreeLeftChild);
        assertEquals(root, result);
    }

    // TODO test --> what happens if given a node that isn't in the tree/isn't
    // attached to the root
    // TODO and can't be found?
}
