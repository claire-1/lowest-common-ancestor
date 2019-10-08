package com.mycompany.app;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.mycompany.utils.Node;

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
      LinkedList<Node> path = new LinkedList<>();
      path.add(root);
      path = pathExists(root, descendant, path);
      if (!path.contains(descendant)) { // TODO this isn't right --> probably need to check if the descendant is in the path
        throw new InvalidParameterException("Node not in tree.");
      }
      
      // TODO delete this
      System.out.println("THE PATH IS: ");
      for (Node node : path) {
        System.out.println("NODE "+ node.getData());
      }

    }

    return getLowestCommonAncestorHelper(root, descendants);
  }

  // Method to ensure all nodes in the list of descendants are in the tree.
  private static LinkedList<Node> pathExists(Node root, Node nodeToFind, LinkedList<Node> path) {
    if (root == nodeToFind && !path.contains(root)) {
      path.add(root);
      return path;
    }

    for (Node child : root.getChildren()) {
      if (pathExists(child, nodeToFind, path).size() != 1) {
        return path;
      }
    }

    return path;
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

  // public static Set<Node> getLowestCommonAncestorForDirectedAcyclicGraph(DirectedGraph<Node, Node> graph,
  //     Node descendant1, Node descendant2) {
  //   // from https://jgrapht.org/javadoc/org/jgrapht/alg/lca/NaiveLCAFinder.html
  //   NaiveLcaFinder<Node, Node> lcaFinder = new NaiveLcaFinder<>(graph);
  //   return lcaFinder.findLcas(descendant1, descendant2);
  // }

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