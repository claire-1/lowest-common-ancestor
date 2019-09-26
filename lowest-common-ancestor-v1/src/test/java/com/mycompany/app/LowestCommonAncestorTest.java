package com.mycompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;
import java.util.List;

import com.mycompany.utils.Node;

import org.junit.Test;

/**
 * Unit test for LowestCommonAncestor.
 * TODO create a script to run the tests
 */
public class LowestCommonAncestorTest {

    @Test
    public void givenEmptyTreeShouldReturnNull() {
        Node result = LowestCommonAncestor.getLowestCommonAncestor(null, null);
        assertNull(result);
    }

    @Test
    public void givenEmptyDescendantsListOfShouldReturnNull() {
        Node root = new Node(5);
        List<Node> descendants = new LinkedList<>();
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertNull(result);
    }

    @Test
    public void givenTreeWithRootAndTwoChildrenShouldReturnRootAsAncestorOfChildren() {
        // Make the tree
        Node root = new Node(5);
        Node child1 = new Node(4);
        Node child2 = new Node(8);
        root.addChild(child1);
        root.addChild(child2);

        List<Node> descendants = new LinkedList<>();
        descendants.add(child1);
        descendants.add(child2); 

        // Get the common ancestor
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertEquals(root, result);
    }

    @Test
    public void givenTreeWithDepthOfFourAndThreeLeafsOffOneNodeShouldReturnCorrectAncestorNodeForThisNonBinaryTree() {
        // Make root
        Node root = new Node(100);
        Node rootChild1 = new Node(50);
        Node rootChild2 = new Node(45);
        Node firstSubtreeRoot = new Node(99);
        root.addChild(rootChild1);
        root.addChild(rootChild2);
        root.addChild(firstSubtreeRoot);

        // Make first subtree
        Node subtreeChild1 = new Node(8);
        Node secondSubtreeRoot = new Node(2);
        firstSubtreeRoot.addChild(subtreeChild1);
        firstSubtreeRoot.addChild(secondSubtreeRoot);

        // Make second subtree with three nodes for one root
        Node secondSubtreeChild1 = new Node(77);
        Node secondSubtreeChild2 = new Node(88);
        Node secondSubtreeChild3 = new Node(66);
        secondSubtreeRoot.addChild(secondSubtreeChild1);
        secondSubtreeRoot.addChild(secondSubtreeChild2);
        secondSubtreeRoot.addChild(secondSubtreeChild3);

        List<Node> descendants = new LinkedList<>();
        descendants.add(subtreeChild1);
        descendants.add(secondSubtreeChild3);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertEquals(firstSubtreeRoot, result);
    }
   
   
   // TODO linear example on slides doesn't match wikipedia --> can a node
    // TODO be an ancestor of itself for this class?
    // TODO this depends on what should happen in linear case
    @Test
    public void givenTreeWithRootInDescendantsShouldReturnRoot() { // TODO need this test? lines of code in LCA.java already covered so I don't know
        // Make the tree
        Node root = new Node(15);
        Node firstSubtreeRoot = new Node(10);
        Node secondSubtreeRoot = new Node(25);
        root.addChild(firstSubtreeRoot);
        root.addChild(secondSubtreeRoot);

        Node firstSubtreeFirstChild = new Node(12);
        Node firstSubtreeSecondChild = new Node(13);
        Node firstSubtreeThirdChild = new Node(14);
        firstSubtreeRoot.addChild(firstSubtreeFirstChild);
        firstSubtreeRoot.addChild(firstSubtreeSecondChild);
        firstSubtreeRoot.addChild(firstSubtreeThirdChild);
        
        List<Node> descendants = new LinkedList<>();
        descendants.add(root); // Add root to descendants
        descendants.add(firstSubtreeFirstChild);
        descendants.add(firstSubtreeSecondChild);
        descendants.add(firstSubtreeThirdChild);

        // Get the common ancestor
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertEquals(root, result);
    }

    // TODO linear wikipedia doesn't match slides --> which to follow?
        //     // TODO what is the correct output when the node is in a line?
    //     // TODO the class slides suggest that it can't be the a node looking for
    //     // decendants itself,
    //     // TODO but the online description says that it can be
    //     // TODO "The lowest common ancestor between two nodes n1 and n2 is defined as
    //     // the lowest node in T that has both n1 and n2 as descendants (where we allow a
    //     // node to be a descendant of itself)"
    //     // TODO from
    //     // https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
    @Test
    public void givenLinearTreeAndNonRootNodesDescendantsShouldReturnNodeClosestToRootInDescendants() {
        // Make the tree
        Node root = new Node(500);
        Node child1 = new Node(200);
        root.addChild(child1);
        Node child2 = new Node(40);
        child1.addChild(child2);
        Node child3 = new Node(10);
        child2.addChild(child3);
        Node child4 = new Node(5);
        child3.addChild(child4);
        Node child5 = new Node(0);
        child4.addChild(child5);

        List<Node> descendants = new LinkedList<>();
        descendants.add(child5);
        descendants.add(child4);
        descendants.add(child3);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);

        assertEquals(child3, result); // based on online def TODO
    }

     @Test
     public void givenTreeWithTwoSubtreesAndNodesInOneSubtreeShouldReturnThatSubtreeRootAsAncestor() {
        Node root = new Node(15);
        Node firstSubtreeRoot = new Node(10);
        Node secondSubtreeRoot = new Node(25);
        root.addChild(firstSubtreeRoot);
        root.addChild(secondSubtreeRoot);

        Node firstSubtreeFirstChild = new Node(12);
        Node firstSubtreeSecondChild = new Node(13);
        Node firstSubtreeThirdChild = new Node(14);
        firstSubtreeRoot.addChild(firstSubtreeFirstChild);
        firstSubtreeRoot.addChild(firstSubtreeSecondChild);
        firstSubtreeRoot.addChild(firstSubtreeThirdChild);

        Node secondSubtreeFirstChild = new Node(12);
        Node secondSubtreeSecondChild = new Node(13);
        Node secondSubtreeThirdChild = new Node(14);
        secondSubtreeRoot.addChild(secondSubtreeFirstChild);
        secondSubtreeRoot.addChild(secondSubtreeSecondChild);
        secondSubtreeRoot.addChild(secondSubtreeThirdChild);

        List<Node> descendants = new LinkedList<>();
        descendants.add(firstSubtreeFirstChild);
        descendants.add(firstSubtreeThirdChild);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertEquals(firstSubtreeRoot, result);
     }

    // @Test
    // public void givenTreeWithTwoSubtreesAndNodesInDifferentSubtreeShouldReturnRootAsAncestor() {
    //     // Make the tree - root
    //     Node root = new Node(15);
    //     Node leftSubtreeRoot = new Node(10);
    //     root.addChild(leftSubtreeRoot);
    //     Node rightSubtreeRoot = new Node(25);
    //     root.setRight(rightSubtreeRoot);

    //     // Make the tree - left subtree
    //     Node leftSubtreeLeftChild = new Node(8);
    //     leftSubtreeRoot.addChild(leftSubtreeLeftChild);
    //     Node leftSubtreeRightChild = new Node(12);
    //     leftSubtreeRoot.setRight(leftSubtreeRightChild);

    //     // Make the tree - right subtree
    //     Node rightSubtreeLeftChild = new Node(20);
    //     rightSubtreeRoot.addChild(rightSubtreeLeftChild);
    //     Node rightSubtreeRightChild = new Node(30);
    //     rightSubtreeRoot.setRight(rightSubtreeRightChild);

    //     Node result = LowestCommonAncestor.getLowestCommonAncestor(root, rightSubtreeLeftChild, leftSubtreeLeftChild);
    //     assertEquals(root, result);
    // }

    // TODO test --> what happens if given a node that isn't in the tree/isn't
    // attached to the root
    // TODO and can't be found?

}
