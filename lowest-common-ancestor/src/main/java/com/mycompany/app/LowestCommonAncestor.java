package com.mycompany.app;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mycompany.utils.Node;

import org.jgrapht.Graph;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultEdge;

/**
 * LowestCommonAncestor.java - for finding the common ancestor of two nodes in a
 * binary tree. Adapted from: https://www.careercup.com/question?id=13437666 and
 * https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/.
 *
 */
public class LowestCommonAncestor {

  // Method to get the Lowest Common Ancestor from a tree.
  public static Node getLowestCommonAncestor(Node root, List<Node> descendants) {

    if (root == null || descendants == null || descendants.size() == 0) {
      return null;
    }

    if (hasDuplicates(descendants)) {
      throw new InvalidParameterException("Duplicates are not allowed in descendants.");
    }

    for (Node descendant : descendants) {
      if (!pathExists(root, descendant)) {
        throw new InvalidParameterException("Node not in tree.");
      }
    }

    return getLowestCommonAncestorHelper(root, descendants);
  }

  // Method to ensure all nodes in the list of descendants are in the tree.
  private static boolean pathExists(Node root, Node nodeToFind) {
    if (root == nodeToFind) {
      return true;
    }

    for (Node child : root.getChildren()) {
      if (pathExists(child, nodeToFind)) {
        return true;
      }
    }

    return false;
  }

  private static boolean hasDuplicates(List<Node> descendants) {
    // Hash to store seen values
    HashSet<Node> seenNodes = new HashSet<>();

    for (int i = 0; i < descendants.size(); i++) {
      Node currentNode = descendants.get(i);
      // If current value is seen before
      if (seenNodes.contains(currentNode)) {
        return true;
      } else {
        seenNodes.add(currentNode);
      }
    }

    return false;
  }

  public static Set<Node> getLowestCommonAncestorForDirectedAcyclicGraph(Graph<Node, DefaultEdge> graph,
      List<Node> descendants) {
    // from https://jgrapht.org/javadoc/org/jgrapht/alg/lca/NaiveLCAFinder.html
    // source for understanding: https://www.codota.com/code/java/methods/org.jgrapht.alg.DijkstraShortestPath/findPathBetween
    // Descendants contain the root so return the root
    if (graph == null || descendants == null || graph.vertexSet().size() == 0 || descendants.size() == 0) {
      return null;
    }    
    
    if (descendants.size() > 2) {
      // TODO: possible allow more than two descendants in the future
      // This is simple for binary trees but complicated for directed, acyclic graphs.
      throw new InvalidParameterException("More than two descendants isn't allowed right now");
    }

    if (descendants.size() == 1 && graph.containsVertex(descendants.get(0))) {
      Set<Node> ancestor = new HashSet<>();
      ancestor.add(descendants.get(0));  
      return ancestor;
    }
    
    for (Node descendant : descendants) {
      if (!graph.containsVertex(descendant)) {
        throw new InvalidParameterException("Node not in graph.");
      }
    }

    NaiveLcaFinder<Node, DefaultEdge> lcaFinder = new NaiveLcaFinder<>(graph);

    return lcaFinder.findLcas(descendants.get(0), descendants.get(1));
  }

  // Method to do the work of finding the lowest common ancestor for a tree.
  public static Node getLowestCommonAncestorHelper(Node root, List<Node> descendants) {

    // Descendants contain the root so return the root
    if (descendants.contains(root)) {
      return root;
    }

    Node currentLCA = null;
    int count = 0;

    // Find the LCA from all the children
    try {
      for (Node node : root.getChildren()) {
        Node result = getLowestCommonAncestorHelper(node, descendants);
        if (result != null) {
          count++;
          currentLCA = result;
        }
      }
    } catch (NullPointerException e) {
      throw new InvalidParameterException("Tree contains a null node " + e);
    }

    if (count == descendants.size()) {
      return root;
    }

    return currentLCA;
  }
}