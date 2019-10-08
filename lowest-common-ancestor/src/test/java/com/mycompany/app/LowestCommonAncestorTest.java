package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    public void givenEmptyTreeAKAGraphShouldReturnNull() {
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
        for (Node temp : result) {
            System.out.println("VALUE " + temp.getData());
        }
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
        //root.addChild(firstSubtreeRoot);
        graph.addEdge(root, secondSubtreeRoot);
       // root.addChild(secondSubtreeRoot);

        Node firstSubtreeFirstChild = new Node(12);
        graph.addVertex(firstSubtreeFirstChild);

        Node firstSubtreeSecondChild = new Node(13);
        graph.addVertex(firstSubtreeSecondChild);

        Node firstSubtreeThirdChild = new Node(14);
        graph.addVertex(firstSubtreeThirdChild);

        graph.addEdge(firstSubtreeRoot, firstSubtreeFirstChild);
        //firstSubtreeRoot.addChild(firstSubtreeFirstChild);
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

    // @Test
    // public void
    // givenTreeWithThreeSubtreesAndDecsendantsInAllThreeDifferentSubtreesShouldReturnRootAsAncestor()
    // {
    // Node root = new Node(12);
    // Node firstSubtreeRoot = new Node(13);
    // root.addChild(firstSubtreeRoot);
    // Node secondSubtreeRoot = new Node(17);
    // root.addChild(secondSubtreeRoot);
    // Node thirdSubtreeRoot = new Node(19);
    // root.addChild(thirdSubtreeRoot);

    // Node firstSubtreeChild1 = new Node(99);
    // firstSubtreeRoot.addChild(firstSubtreeChild1);
    // Node firstSubtreeChild2 = new Node(98);
    // firstSubtreeRoot.addChild(firstSubtreeChild2);

    // Node secondSubtreeChild1 = new Node(88);
    // secondSubtreeRoot.addChild(secondSubtreeChild1);
    // Node secondSubtreeChild2 = new Node(82);
    // secondSubtreeRoot.addChild(secondSubtreeChild2);
    // Node secondSubtreeChild3 = new Node(86);
    // secondSubtreeRoot.addChild(secondSubtreeChild3);

    // Node thirdSubtreeChild1 = new Node(66);
    // thirdSubtreeRoot.addChild(thirdSubtreeChild1);

    // List<Node> descendants = new LinkedList<>();
    // descendants.add(firstSubtreeChild2);
    // descendants.add(secondSubtreeChild3);
    // descendants.add(thirdSubtreeRoot);

    // Node result = LowestCommonAncestor.getLowestCommonAncestor(root,
    // descendants);
    // assertEquals(root, result);
    // }

    // @Test
    // public void
    // givenTreeWithTwoNodesWithSameDataValueAsDesendantsShouldReturnCorrectAncestorWithoutErrors()
    // {
    // Node root = new Node(15);
    // Node subtreeRoot = new Node(12);
    // root.addChild(subtreeRoot);

    // Node subtreeChild1 = new Node(19);
    // subtreeRoot.addChild(subtreeChild1);
    // Node subtreeChild2 = new Node(17);
    // subtreeRoot.addChild(subtreeChild2);

    // Node subtreeChild1ChildWithRepeatData = new Node(22);
    // subtreeChild1.addChild(subtreeChild1ChildWithRepeatData);

    // Node subtreeChild2ChildWithRepeatData = new Node(22);
    // subtreeChild2.addChild(subtreeChild2ChildWithRepeatData);

    // List<Node> descendants = new LinkedList<>();
    // descendants.add(subtreeChild1ChildWithRepeatData);
    // descendants.add(subtreeChild2ChildWithRepeatData);

    // Node result = LowestCommonAncestor.getLowestCommonAncestor(root,
    // descendants);
    // assertEquals(subtreeRoot, result);
    // }

    // @Test
    // public void givenTreeWithNullNodeShouldThrowInvalidParameterException() {
    // Node root = new Node(14);
    // Node child1 = new Node(16);
    // root.addChild(child1);
    // Node child2 = new Node(17);
    // root.addChild(child2);
    // root.addChild(null); // null node

    // List<Node> descendants = new LinkedList<>();
    // descendants.add(child1);
    // descendants.add(child2);

    // assertThrows(InvalidParameterException.class, () -> {
    // LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
    // });
    // }

    // @Test
    // public void
    // givenTreeWithNullNodeInDescendantsShouldThrowInvalidParameterException() {
    // Node root = new Node(14);
    // Node child1 = new Node(16);
    // root.addChild(child1);
    // Node child2 = new Node(17);
    // root.addChild(child2);

    // List<Node> descendants = new LinkedList<>();
    // descendants.add(child1);
    // descendants.add(child2);
    // descendants.add(null); // null descendant

    // assertThrows(InvalidParameterException.class, () -> {
    // LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
    // });
    // }

    // @Test
    // public void givenDesendantNotInTreeShouldThrowInvalidParameterException() {
    // Node root = new Node(5);
    // Node notInTree = new Node(700);

    // List<Node> descendants = new LinkedList<>();
    // descendants.add(notInTree);

    // assertThrows(InvalidParameterException.class, () -> {
    // LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
    // });

    // }

    // @Test
    // public void
    // givenTreeWithDuplicateDesendantShouldThrowInvalidParameterException() {
    // Node root = new Node(5);
    // Node firstSubtree = new Node(1);
    // root.addChild(firstSubtree);
    // Node secondSubtree = new Node(2);
    // root.addChild(secondSubtree);

    // Node firstSubtreeChild1 = new Node(4);
    // firstSubtree.addChild(firstSubtreeChild1);
    // Node firstSubtreeChild2 = new Node(22);
    // firstSubtree.addChild(firstSubtreeChild2);

    // Node firstSubtreeChild1Child1 = new Node(11);
    // firstSubtreeChild1.addChild(firstSubtreeChild1Child1);

    // List<Node> descendants = new LinkedList<>();
    // descendants.add(firstSubtreeChild1Child1);
    // descendants.add(firstSubtreeChild2);
    // descendants.add(firstSubtreeChild2); // duplicate

    // assertThrows(InvalidParameterException.class, () -> {
    // LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
    // });

    // }

    // TODO add test for more than two descendants --> show thrown an exception
    
    // @Test
    // public void
    // givenDirectedAcyclicGraphAndConnectedNodesWithOnePossibleAncestorShouldReturnThatAncestor()
    // {
    // Node root = new Node(15);
    // Node childOfRoot1 = new Node(12);
    // root.addChild(childOfRoot1);
    // Node childOfRoot2 = new Node(13);
    // root.addChild(childOfRoot2);

    // Node connectedChild1 = new Node(20);
    // childOfRoot1.addChild(connectedChild1);
    // Node connectedChild2 = new Node(21);
    // childOfRoot2.addChild(connectedChild2);
    // connectedChild2.addChild(connectedChild1);

    // Node childOfChildOfRoot2 = new Node(3);
    // childOfRoot2.addChild(childOfChildOfRoot2);

    // List<Node> descendants = new LinkedList<>();
    // descendants.add(connectedChild2);
    // descendants.add(childOfRoot2);

    // // TODO: the functionality for this test isn't implemented in part 1 so this
    // // will give the incorrect node for part 1 but shouldn't throw an exception.
    // // Change this to assertEquals(childOfRoot2, result); once part 2 is
    // // implemented.
    // Node result = LowestCommonAncestor.getLowestCommonAncestor(root,
    // descendants);
    // assertEquals(childOfRoot2, result);
    // }

    // @Test
    // // TODO: this functionality will be implemented in part 2 and StackOverflow
    // // will no longer be expected.
    // public void givenGraphWithCycleInPartOneShouldThrowStackOverflowException() {
    // Node root = new Node(15);
    // Node childOfRoot1 = new Node(12);
    // root.addChild(childOfRoot1);
    // Node childOfRoot2 = new Node(13);
    // root.addChild(childOfRoot2);

    // Node connectedChild1 = new Node(20);
    // childOfRoot1.addChild(connectedChild1);
    // Node connectedChild2 = new Node(21);
    // childOfRoot2.addChild(connectedChild2);
    // connectedChild1.addChild(connectedChild2);
    // connectedChild2.addChild(connectedChild1);

    // Node childOfRoot2Child = new Node(3);
    // childOfRoot2.addChild(childOfRoot2Child);

    // List<Node> descendants = new LinkedList<>();
    // descendants.add(childOfRoot2Child);
    // descendants.add(connectedChild1);

    // // TODO: the functionality for this test isn't implemented in part 1 so this
    // // will cause a StackOverflow. Change this to be InvalidParameterException
    // // once part 2 is implemented.
    // assertThrows(StackOverflowError.class, () -> {
    // LowestCommonAncestor.getLowestCommonAncestor(root, descendants);
    // });
    // }

    // TODO: need test for graph with cycle; need tests for graph with muliple
    // options for LCA

}
