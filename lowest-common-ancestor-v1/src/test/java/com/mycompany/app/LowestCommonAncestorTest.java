package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import com.mycompany.utils.Node;

import org.junit.jupiter.api.Test;

/**
 * Unit test for LowestCommonAncestor. TODO create a script to run the tests
 */
public class LowestCommonAncestorTest {

    @Test
    public void givenEmptyTreeShouldReturnNull() {
        Node result = LowestCommonAncestor.getLowestCommonAncestor(null, null);
        assertNull(result);
    }

    @Test
    public void givenNullDescendantsListShouldReturnNull() {
        Node root = new Node(5);
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, null);
        assertNull(result);
    }

    @Test
    public void givenEmptyDescendantsListShouldReturnNull() {
        Node root = new Node(5);
        List<Node> descendants = new LinkedList<>();
        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertNull(result);
    }

    @Test
    public void givenOneNodeInTreeAndInDescendantsShouldReturnThatNode() {
        Node root = new Node(5);
        List<Node> descendants = new LinkedList<>();
        descendants.add(root);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertEquals(root, result);
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

    @Test
    // Allowing a node to be an ancestor of itself
    public void givenTreeWithRootInDescendantsShouldReturnRoot() {
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

    @Test
    // Allowing a node to be an ancestor of itself
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
        assertEquals(child3, result);
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

    @Test
    public void givenTreeWithThreeSubtreesAndDecsendantsInAllThreeDifferentSubtreesShouldReturnRootAsAncestor() {
        Node root = new Node(12);
        Node firstSubtreeRoot = new Node(13);
        root.addChild(firstSubtreeRoot);
        Node secondSubtreeRoot = new Node(17);
        root.addChild(secondSubtreeRoot);
        Node thirdSubtreeRoot = new Node(19);
        root.addChild(thirdSubtreeRoot);

        Node firstSubtreeChild1 = new Node(99);
        firstSubtreeRoot.addChild(firstSubtreeChild1);
        Node firstSubtreeChild2 = new Node(98);
        firstSubtreeRoot.addChild(firstSubtreeChild2);

        Node secondSubtreeChild1 = new Node(88);
        secondSubtreeRoot.addChild(secondSubtreeChild1);
        Node secondSubtreeChild2 = new Node(82);
        secondSubtreeRoot.addChild(secondSubtreeChild2);
        Node secondSubtreeChild3 = new Node(86);
        secondSubtreeRoot.addChild(secondSubtreeChild3);

        Node thirdSubtreeChild1 = new Node(66);
        thirdSubtreeRoot.addChild(thirdSubtreeChild1);

        List<Node> descendants = new LinkedList<>();
        descendants.add(firstSubtreeChild2);
        descendants.add(secondSubtreeChild3);
        descendants.add(thirdSubtreeRoot);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertEquals(root, result);
    }

    @Test
    public void givenTreeWithTwoNodesWithSameDataValueAsDesendantsShouldReturnCorrectAncestorWithoutErrors() {
        Node root = new Node(15);
        Node subtreeRoot = new Node(12);
        root.addChild(subtreeRoot);

        Node subtreeChild1 = new Node(19);
        subtreeRoot.addChild(subtreeChild1);
        Node subtreeChild2 = new Node(17);
        subtreeRoot.addChild(subtreeChild2);

        Node subtreeChild1ChildWithRepeatData = new Node(22);
        subtreeChild1.addChild(subtreeChild1ChildWithRepeatData);

        Node subtreeChild2ChildWithRepeatData = new Node(22);
        subtreeChild2.addChild(subtreeChild2ChildWithRepeatData);

        List<Node> descendants = new LinkedList<>();
        descendants.add(subtreeChild1ChildWithRepeatData);
        descendants.add(subtreeChild2ChildWithRepeatData);

        Node result = LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        assertEquals(subtreeRoot, result);
    }

    @Test
    public void givenTreeWithNullNodeShouldThrowInvalidParameterException() {
        Node root = new Node(14);
        Node child1 = new Node(16);
        root.addChild(child1);
        Node child2 = new Node(17);
        root.addChild(child2);
        root.addChild(null); // null node

        List<Node> descendants = new LinkedList<>();
        descendants.add(child1);
        descendants.add(child2);

        assertThrows(InvalidParameterException.class, () -> {
            LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        });        
    }

    @Test
    public void givenTreeWithNullNodeInDescendantsShouldThrowInvalidParameterException() {
        Node root = new Node(14);
        Node child1 = new Node(16);
        root.addChild(child1);
        Node child2 = new Node(17);
        root.addChild(child2);

        List<Node> descendants = new LinkedList<>();
        descendants.add(child1);
        descendants.add(child2);
        descendants.add(null); // null descendant

        assertThrows(InvalidParameterException.class, () -> {
            LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        });
    }

    @Test
    public void givenDesendantNotInTreeShouldThrowInvalidParameterException() {
        Node root = new Node(5);
        Node notInTree = new Node(700);

        List<Node> descendants = new LinkedList<>();
        descendants.add(notInTree);

        assertThrows(InvalidParameterException.class, () -> {
            LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        });

    }

    @Test
    public void givenTreeWithDuplicateDesendantShouldThrowInvalidParameterException() {
        Node root = new Node(5);
        Node firstSubtree = new Node(1);
        root.addChild(firstSubtree);
        Node secondSubtree = new Node(2);
        root.addChild(secondSubtree);

        Node firstSubtreeChild1 = new Node(4);
        firstSubtree.addChild(firstSubtreeChild1);
        Node firstSubtreeChild2 = new Node(22);
        firstSubtree.addChild(firstSubtreeChild2);

        Node firstSubtreeChild1Child1 = new Node(11);
        firstSubtreeChild1.addChild(firstSubtreeChild1Child1);

        List<Node> descendants = new LinkedList<>();
        descendants.add(firstSubtreeChild1Child1);
        descendants.add(firstSubtreeChild2);
        descendants.add(firstSubtreeChild2); // duplicate

        assertThrows(InvalidParameterException.class, () -> {
            LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        });

    }

    @Test
    // TODO: this functionality will be implemented in part 2 and should
    // return the correct node then. For part 1, it should simply
    // not thrown an exception. 
    public void givenDirectedAcyclicGraphInPartOneShouldNotThrowAnException() {
        Node root = new Node(15);
        Node childOfRoot1 = new Node(12);
        root.addChild(childOfRoot1);
        Node childOfRoot2 = new Node(13);
        root.addChild(childOfRoot2);

        Node connectedChild1 = new Node(20);
        childOfRoot1.addChild(connectedChild1);
        Node connectedChild2 = new Node(21);
        childOfRoot2.addChild(connectedChild2);
        connectedChild1.addChild(connectedChild2);

        Node childOfRoot2Child = new Node(3);
        childOfRoot2.addChild(childOfRoot2Child);

        List<Node> descendants = new LinkedList<>();
        descendants.add(childOfRoot2Child);
        descendants.add(connectedChild1);

        // TODO: the functionality for this test isn't implemented in part 1 so this
        // will give the incorrect node for part 1 but shouldn't throw an exception.
        // Change this to assertEquals(childOfRoot2, result); once part 2 is
        // implemented.
        assertDoesNotThrow(() -> {
            LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        });
    }

    @Test
    // TODO: this functionality will be implemented in part 2 and StackOverflow
    // will no longer be expected.
    public void givenGraphWithCycleInPartOneShouldThrowStackOverflowException() {
        Node root = new Node(15);
        Node childOfRoot1 = new Node(12);
        root.addChild(childOfRoot1);
        Node childOfRoot2 = new Node(13);
        root.addChild(childOfRoot2);

        Node connectedChild1 = new Node(20);
        childOfRoot1.addChild(connectedChild1);
        Node connectedChild2 = new Node(21);
        childOfRoot2.addChild(connectedChild2);
        connectedChild1.addChild(connectedChild2);
        connectedChild2.addChild(connectedChild1);

        Node childOfRoot2Child = new Node(3);
        childOfRoot2.addChild(childOfRoot2Child);

        List<Node> descendants = new LinkedList<>();
        descendants.add(childOfRoot2Child);
        descendants.add(connectedChild1);

        // TODO: the functionality for this test isn't implemented in part 1 so this
        // will cause a StackOverflow. Change this to be InvalidParameterException
        // once part 2 is implemented.
        assertThrows(StackOverflowError.class, () -> {
            LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
        });
    }

}
