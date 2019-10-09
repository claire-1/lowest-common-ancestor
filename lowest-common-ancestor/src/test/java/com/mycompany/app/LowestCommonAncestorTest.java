package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.mycompany.utils.Node;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.junit.jupiter.api.Test;

/**
 * Unit test for LowestCommonAncestor.
 */
public class LowestCommonAncestorTest {

    @Test
    public void givenNullGraphShouldReturnNull() {
        List<Node> descendants = new LinkedList<>();
        Node notInTree = new Node(44);
        descendants.add(notInTree);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(null, descendants);
        assertNull(result);
    }
    
    @Test
    public void givenEmptyGraphShouldReturnNull() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        List<Node> descendants = new LinkedList<>();
        Node notInTree = new Node(44);
        descendants.add(notInTree);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        assertNull(result);
    }

    @Test
    public void givenNullDescendantsListShouldReturnNull() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
        Node root = new Node(5);
        graph.addVertex(root);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, null);
        assertNull(result);
    }

    @Test
    public void givenEmptyDescendantsListShouldReturnNull() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
        Node root = new Node(5);
        graph.addVertex(root);

        List<Node> descendants = new LinkedList<>();
        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        assertNull(result);
    }

    @Test
    public void givenOneNodeInTreeAndThatOneInDescendantsShouldReturnThatNode() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
        Node root = new Node(5);
        graph.addVertex(root);

        List<Node> descendants = new LinkedList<>();
        descendants.add(root);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);

        Set<Node> expected = new HashSet<Node>();
        expected.add(root);

        assertEquals(expected, result);
    }

    @Test
    public void givenTreeWithRootAndTwoChildrenShouldReturnRootAsAncestorOfChildren() {
        // From
        // https://www.programcreek.com/java-api-examples/?code=taboola/taboola-cronyx/taboola-cronyx-master/taboola-cronyx/src/main/java/com/taboola/cronyx/impl/StdNameAndGroupGraphValidator.java
        Node root = new Node(1);
        Node child1 = new Node(2);
        Node child2 = new Node(3);

        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
        graph.addVertex(root);
        graph.addVertex(child1);
        graph.addVertex(child2);
        graph.addEdge(root, child1);
        graph.addEdge(root, child2);

        List<Node> descendants = new LinkedList<>();
        descendants.add(child1);
        descendants.add(child2);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        Set<Node> expected = new HashSet<Node>();
        expected.add(root);
        assertEquals(expected, result);
    }

    @Test
    public void givenDirectedAcyclicGraphAndConnectedNodesWithTwoPossibleAncestorShouldReturnLowestAncestor() {
        // Source for how to use graph class:
        // https://www.programcreek.com/java-api-examples/?code=taboola/taboola-cronyx/taboola-cronyx-master/taboola-cronyx/src/main/java/com/taboola/cronyx/impl/StdNameAndGroupGraphValidator.java
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(15);
        graph.addVertex(root);
        Node childOfRoot1 = new Node(12);
        graph.addVertex(childOfRoot1);
        graph.addEdge(root, childOfRoot1);
        root.addChild(childOfRoot1);
        Node childOfRoot2 = new Node(13);
        graph.addVertex(childOfRoot2);
        graph.addEdge(root, childOfRoot2);
        root.addChild(childOfRoot2);

        Node connectedChild1 = new Node(20);
        graph.addVertex(connectedChild1);
        graph.addEdge(childOfRoot1, connectedChild1);
        childOfRoot1.addChild(connectedChild1);
        Node connectedChild2 = new Node(21);
        graph.addVertex(connectedChild2);
        graph.addEdge(childOfRoot2, connectedChild2);
        childOfRoot2.addChild(connectedChild2);
        graph.addEdge(connectedChild2, connectedChild1);
        connectedChild2.addChild(connectedChild1);

        Node childOfChildOfRoot2 = new Node(3);
        graph.addVertex(childOfChildOfRoot2);
        graph.addEdge(childOfRoot2, childOfChildOfRoot2);
        childOfRoot2.addChild(childOfChildOfRoot2);

        List<Node> descendants = new LinkedList<>();
        descendants.add(childOfChildOfRoot2);
        descendants.add(connectedChild1);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        Set<Node> expected = new HashSet<Node>();
        expected.add(childOfRoot2);

        assertEquals(expected, result);
    }

    @Test
    public void givenTreeWithDepthOfFourAndThreeLeafsOffOneNodeShouldReturnCorrectAncestorNodeForThisNonBinaryTree() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(100);
        graph.addVertex(root);
        Node rootChild1 = new Node(50);
        graph.addVertex(rootChild1);
        graph.addEdge(root, rootChild1);
        Node rootChild2 = new Node(45);
        graph.addVertex(rootChild2);
        graph.addEdge(root, rootChild2);
        Node firstSubtreeRoot = new Node(99);
        graph.addVertex(firstSubtreeRoot);
        graph.addEdge(root, firstSubtreeRoot);

        // Make first subtree
        Node subtreeChild1 = new Node(8);
        graph.addVertex(subtreeChild1);
        graph.addEdge(firstSubtreeRoot, subtreeChild1);
        Node secondSubtreeRoot = new Node(2);
        graph.addVertex(secondSubtreeRoot);
        graph.addEdge(firstSubtreeRoot, secondSubtreeRoot);
        firstSubtreeRoot.addChild(subtreeChild1);
        firstSubtreeRoot.addChild(secondSubtreeRoot);

        // Make second subtree with three nodes for one root
        Node secondSubtreeChild1 = new Node(77);
        graph.addVertex(secondSubtreeChild1);
        graph.addEdge(secondSubtreeRoot, secondSubtreeChild1);
        Node secondSubtreeChild2 = new Node(88);
        graph.addVertex(secondSubtreeChild2);
        graph.addEdge(secondSubtreeRoot, secondSubtreeChild2);

        Node secondSubtreeChild3 = new Node(66);
        graph.addVertex(secondSubtreeChild3);
        graph.addEdge(secondSubtreeRoot, secondSubtreeChild3);

        List<Node> descendants = new LinkedList<>();
        descendants.add(subtreeChild1);
        descendants.add(secondSubtreeChild3);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        Set<Node> expected = new HashSet<Node>();
        expected.add(firstSubtreeRoot);

        assertEquals(expected, result);
    }

    @Test
    // Allowing a node to be an ancestor of itself
    public void givenTreeWithRootInDescendantsShouldReturnRoot() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(15);
        graph.addVertex(root);
        Node firstSubtreeRoot = new Node(10);
        graph.addVertex(firstSubtreeRoot);
        Node secondSubtreeRoot = new Node(25);
        graph.addVertex(secondSubtreeRoot);

        graph.addEdge(root, firstSubtreeRoot);
        graph.addEdge(root, secondSubtreeRoot);

        // root.addChild(firstSubtreeRoot);
        // root.addChild(secondSubtreeRoot);

        Node firstSubtreeFirstChild = new Node(12);
        graph.addVertex(firstSubtreeFirstChild);

        Node firstSubtreeSecondChild = new Node(13);
        graph.addVertex(firstSubtreeSecondChild);

        Node firstSubtreeThirdChild = new Node(14);
        graph.addVertex(firstSubtreeThirdChild);

        graph.addEdge(firstSubtreeRoot, firstSubtreeFirstChild);
        graph.addEdge(firstSubtreeRoot, firstSubtreeSecondChild);
        graph.addEdge(firstSubtreeRoot, firstSubtreeThirdChild);

        List<Node> descendants = new LinkedList<>();
        descendants.add(root); // Add root to descendants
        descendants.add(firstSubtreeThirdChild);

        // Get the common ancestor
        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        Set<Node> expected = new HashSet<Node>();
        expected.add(root);

        assertEquals(expected, result);
    }

    @Test
    // Allowing a node to be an ancestor of itself
    public void givenLinearTreeAndNonRootNodesDescendantsShouldReturnNodeClosestToRootInDescendants() {
        // Make the tree
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(500);
        graph.addVertex(root);
        Node child1 = new Node(200);
        graph.addVertex(child1);
        graph.addEdge(root, child1);
        // root.addChild(child1);
        Node child2 = new Node(40);
        graph.addVertex(child2);
        graph.addEdge(child1, child2);
        // child1.addChild(child2);
        Node child3 = new Node(10);
        graph.addVertex(child3);
        graph.addEdge(child2, child3);
        // child2.addChild(child3);
        Node child4 = new Node(5);
        graph.addVertex(child4);
        graph.addEdge(child3, child4);
        // child3.addChild(child4);
        Node child5 = new Node(0);
        graph.addVertex(child5);
        graph.addEdge(child4, child5);
        // child4.addChild(child5);

        List<Node> descendants = new LinkedList<>();
        descendants.add(child5);
        descendants.add(child3);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        Set<Node> expected = new HashSet<Node>();
        expected.add(child3);

        assertEquals(expected, result);
    }

    @Test
    public void givenTreeWithTwoSubtreesAndNodesInOneSubtreeShouldReturnThatSubtreeRootAsAncestor() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(15);
        graph.addVertex(root);
        Node firstSubtreeRoot = new Node(10);
        graph.addVertex(firstSubtreeRoot);

        Node secondSubtreeRoot = new Node(25);
        graph.addVertex(secondSubtreeRoot);

        graph.addEdge(root, firstSubtreeRoot);
        // root.addChild(firstSubtreeRoot);
        graph.addEdge(root, secondSubtreeRoot);
        // root.addChild(secondSubtreeRoot);

        Node firstSubtreeFirstChild = new Node(12);
        graph.addVertex(firstSubtreeFirstChild);

        Node firstSubtreeSecondChild = new Node(13);
        graph.addVertex(firstSubtreeSecondChild);

        Node firstSubtreeThirdChild = new Node(14);
        graph.addVertex(firstSubtreeThirdChild);

        graph.addEdge(firstSubtreeRoot, firstSubtreeFirstChild);
        // firstSubtreeRoot.addChild(firstSubtreeFirstChild);
        graph.addEdge(firstSubtreeRoot, firstSubtreeSecondChild);

        // firstSubtreeRoot.addChild(firstSubtreeSecondChild);
        graph.addEdge(firstSubtreeRoot, firstSubtreeThirdChild);

        // firstSubtreeRoot.addChild(firstSubtreeThirdChild);

        Node secondSubtreeFirstChild = new Node(12);
        graph.addVertex(secondSubtreeFirstChild);
        Node secondSubtreeSecondChild = new Node(13);
        graph.addVertex(secondSubtreeSecondChild);
        Node secondSubtreeThirdChild = new Node(14);
        graph.addVertex(secondSubtreeThirdChild);

        graph.addEdge(secondSubtreeRoot, secondSubtreeFirstChild);
        secondSubtreeRoot.addChild(secondSubtreeFirstChild);
        graph.addEdge(secondSubtreeRoot, secondSubtreeSecondChild);

        secondSubtreeRoot.addChild(secondSubtreeSecondChild);
        graph.addEdge(secondSubtreeRoot, secondSubtreeThirdChild);

        secondSubtreeRoot.addChild(secondSubtreeThirdChild);

        List<Node> descendants = new LinkedList<>();
        descendants.add(firstSubtreeFirstChild);
        descendants.add(firstSubtreeThirdChild);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        HashSet<Node> expected = new HashSet<>();
        expected.add(firstSubtreeRoot);
        assertEquals(expected, result);
    }

    @Test
    public void givenTreeWithThreeSubtreesAndDecsendantsInAllThreeDifferentSubtreesShouldReturnRootAsAncestor() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(12);
        graph.addVertex(root);
        Node firstSubtreeRoot = new Node(13);
        graph.addVertex(firstSubtreeRoot);
        graph.addEdge(root, firstSubtreeRoot);
        // root.addChild(firstSubtreeRoot);
        Node secondSubtreeRoot = new Node(17);
        graph.addVertex(secondSubtreeRoot);
        graph.addEdge(root, secondSubtreeRoot);
        root.addChild(secondSubtreeRoot);
        Node thirdSubtreeRoot = new Node(19);
        graph.addVertex(thirdSubtreeRoot);
        graph.addEdge(root, thirdSubtreeRoot);
        root.addChild(thirdSubtreeRoot);

        Node firstSubtreeChild1 = new Node(99);
        graph.addVertex(firstSubtreeChild1);
        graph.addEdge(firstSubtreeRoot, firstSubtreeChild1);
        // firstSubtreeRoot.addChild(firstSubtreeChild1);
        Node firstSubtreeChild2 = new Node(98);
        graph.addVertex(firstSubtreeChild2);
        graph.addEdge(firstSubtreeRoot, firstSubtreeChild2);
        firstSubtreeRoot.addChild(firstSubtreeChild2);

        Node secondSubtreeChild1 = new Node(88);
        graph.addVertex(secondSubtreeChild1);
        graph.addEdge(secondSubtreeRoot, secondSubtreeChild1);
        secondSubtreeRoot.addChild(secondSubtreeChild1);
        Node secondSubtreeChild2 = new Node(82);
        graph.addVertex(secondSubtreeChild2);
        graph.addEdge(secondSubtreeRoot, secondSubtreeChild2);
        secondSubtreeRoot.addChild(secondSubtreeChild2);
        Node secondSubtreeChild3 = new Node(86);
        graph.addVertex(secondSubtreeChild3);
        graph.addEdge(secondSubtreeRoot, secondSubtreeChild3);
        secondSubtreeRoot.addChild(secondSubtreeChild3);

        Node thirdSubtreeChild1 = new Node(66);
        graph.addVertex(thirdSubtreeChild1);
        graph.addEdge(thirdSubtreeRoot, thirdSubtreeChild1);
        thirdSubtreeRoot.addChild(thirdSubtreeChild1);

        List<Node> descendants = new LinkedList<>();
        descendants.add(firstSubtreeChild2);
        descendants.add(thirdSubtreeRoot);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        HashSet<Node> expected = new HashSet<>();
        expected.add(root);
        assertEquals(expected, result);
    }

    @Test
    public void givenTreeWithTwoNodesWithSameDataValueInDesendantsShouldReturnCorrectAncestorWithoutErrors() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(15);
        graph.addVertex(root);
        Node subtreeRoot = new Node(12);
        graph.addVertex(subtreeRoot);
        graph.addEdge(root, subtreeRoot);
        // root.addChild(subtreeRoot);

        Node subtreeChild1WithRepeatData = new Node(22);
        graph.addVertex(subtreeChild1WithRepeatData);
        graph.addEdge(subtreeRoot, subtreeChild1WithRepeatData);
        // subtreeRoot.addChild(subtreeChild1);
        Node subtreeChild2WithRepeatData = new Node(22);
        graph.addVertex(subtreeChild2WithRepeatData);
        graph.addEdge(subtreeRoot, subtreeChild2WithRepeatData);

        // subtreeRoot.addChild(subtreeChild2);

        List<Node> descendants = new LinkedList<>();
        descendants.add(subtreeChild1WithRepeatData);
        descendants.add(subtreeChild2WithRepeatData);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        HashSet<Node> expected = new HashSet<>();
        expected.add(subtreeRoot);
        assertEquals(expected, result);
    }

    @Test
    public void givenTreeWithDuplicateDesendantShouldReturnDescendant() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(5);
        graph.addVertex(root);
        Node firstSubtree = new Node(1);
        graph.addVertex(firstSubtree);
        graph.addEdge(root, firstSubtree);
        root.addChild(firstSubtree);
        Node secondSubtree = new Node(2);
        graph.addVertex(secondSubtree);
        graph.addEdge(root, secondSubtree);
        root.addChild(secondSubtree);

        List<Node> descendants = new LinkedList<>();
        descendants.add(secondSubtree);
        descendants.add(secondSubtree); // duplicate

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        HashSet<Node> expected = new HashSet<>();
        expected.add(secondSubtree);
        assertEquals(expected, result);
    }

    // TODO: write in README: a tree with a null node can't be constructed

    @Test
    public void givenTreeWithNullNodeInDescendantsShouldThrowInvalidParameterException() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(14);
        graph.addVertex(root);
        Node child1 = new Node(16);
        graph.addVertex(child1);
        graph.addEdge(root, child1);
        root.addChild(child1);

        List<Node> descendants = new LinkedList<>();
        descendants.add(child1);
        descendants.add(null); // null descendant

        assertThrows(InvalidParameterException.class, () -> {
            LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        });
    }

    @Test
    public void givenDesendantNotInTreeShouldThrowInvalidParameterException() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(5);
        graph.addVertex(root);

        Node notInTree = new Node(700);

        List<Node> descendants = new LinkedList<>();
        descendants.add(notInTree);

        assertThrows(InvalidParameterException.class, () -> {
            LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        });

    }

    @Test
    public void givenMoreThanTwoDescendantsShouldThrowInvalidParameterException() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(14);
        graph.addVertex(root);
        Node child1 = new Node(16);
        graph.addVertex(child1);
        graph.addEdge(root, child1);
        root.addChild(child1);
        Node child2 = new Node(16);
        graph.addVertex(child2);
        graph.addEdge(root, child2);
        root.addChild(child2);
        Node child3 = new Node(16);
        graph.addVertex(child3);
        graph.addEdge(root, child3);
        root.addChild(child3);

        List<Node> descendants = new LinkedList<>();
        descendants.add(child1);
        descendants.add(child2);
        descendants.add(child3);

        assertThrows(InvalidParameterException.class, () -> {
            LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        });
    }

    @Test
    public void givenDirectedAcyclicGraphAndConnectedNodesWithOnePossibleAncestorShouldReturnThatAncestor() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(15);
        graph.addVertex(root);
        Node childOfRoot1 = new Node(12);
        graph.addVertex(childOfRoot1);
        graph.addEdge(root, childOfRoot1);
        root.addChild(childOfRoot1);
        Node childOfRoot2 = new Node(13);
        graph.addVertex(childOfRoot2);
        graph.addEdge(root, childOfRoot2);
        root.addChild(childOfRoot2);

        Node connectedChild1 = new Node(20);
        graph.addVertex(connectedChild1);
        graph.addEdge(childOfRoot1, connectedChild1);
        childOfRoot1.addChild(connectedChild1);
        Node connectedChild2 = new Node(21);
        graph.addVertex(connectedChild2);
        graph.addEdge(childOfRoot2, connectedChild2);
        graph.addEdge(connectedChild2, connectedChild1);
        // childOfRoot2.addChild(connectedChild2);
        // connectedChild2.addChild(connectedChild1);

        Node childOfChildOfRoot2 = new Node(3);
        graph.addVertex(childOfChildOfRoot2);
        graph.addEdge(childOfRoot2, childOfChildOfRoot2);
        childOfRoot2.addChild(childOfChildOfRoot2);

        List<Node> descendants = new LinkedList<>();
        descendants.add(connectedChild2);
        descendants.add(childOfRoot2);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        HashSet<Node> expected = new HashSet<>();
        expected.add(childOfRoot2);
        assertEquals(expected, result);
    }

    @Test
    // TODO: this functionality will be implemented in part 2 and StackOverflow
    // will no longer be expected.
    public void givenGraphWithCycleInPartOneShouldNotBeAbleToConstructGraph() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node root = new Node(15);
        graph.addVertex(root);
        Node connectedChild1 = new Node(12);
        graph.addVertex(connectedChild1);
        graph.addEdge(root, connectedChild1);
        root.addChild(connectedChild1);
        Node connectedChild2 = new Node(13);
        graph.addVertex(connectedChild2);
        graph.addEdge(root, connectedChild2);

        graph.addEdge(connectedChild1, connectedChild2);
        root.addChild(connectedChild2);

        assertThrows(IllegalArgumentException.class, () -> {
            graph.addEdge(connectedChild2, connectedChild1);
        });
    }

    @Test
    public void givenGraphAndDescendantsWithMultipleLCAOptionsShouldReturnSetOfLCAs() {
        DirectedAcyclicGraph<Node, DefaultEdge> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);

        Node node1 = new Node(1);
        graph.addVertex(node1);
        Node node2 = new Node(2);
        graph.addVertex(node2);
        Node node3 = new Node(3);
        graph.addVertex(node3);
        Node node4 = new Node(4);
        graph.addVertex(node4);

        graph.addEdge(node1, node3);
        graph.addEdge(node1, node4);
        graph.addEdge(node2, node3);
        graph.addEdge(node2, node4);

        List<Node> descendants = new LinkedList<>();
        descendants.add(node3);
        descendants.add(node4);

        Set<Node> result = LowestCommonAncestor.getLowestCommonAncestorForDirectedAcyclicGraph(graph, descendants);
        HashSet<Node> expected = new HashSet<>();
        expected.add(node1);
        expected.add(node2);
        assertEquals(expected, result);
    }

}
