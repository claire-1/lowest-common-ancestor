package com.mycompany.app;

import java.security.InvalidParameterException;
import java.util.List;

import com.mycompany.utils.Node;

/**
 * LowestCommonAncestor.java - for finding the common ancestor of two nodes in a
 * binary tree. Adapted from: https://www.careercup.com/question?id=13437666
 * and https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/.
 *
 */
public class LowestCommonAncestor {

  private static boolean pathExists(Node root, Node nodeToFind) {
    if (root == null) {
      return false;
    }

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

  public static Node getLowestCommonAncestor(Node root, List<Node> descendants) {

    if (root == null || descendants.size() == 0) {
      return null;
    } 

    for (Node descendant : descendants) {
      if (!pathExists(root, descendant)) {
        throw new InvalidParameterException("Node not in tree.");
      }
    }

    return getLowestCommonAncestorHelper(root, descendants);
    
   }

  public static Node getLowestCommonAncestorHelper(Node root, List<Node> descendants) {
    if (root == null || descendants.size() == 0) {
      return null;
    }

    // Descendants contain the root so return the root 
    // (TODO this doesn't match with slides but does match wikipedia --> what to do?)
    if (descendants.contains(root)) {
      return root;
    }

    Node currentLCA = null;
    int count = 0;

    // Find the LCA from all the children
    for (Node node : root.getChildren()) {
      Node result = getLowestCommonAncestorHelper(node, descendants);
      if (result != null) {
        count++;
        currentLCA = result;
      }
    }

    if (count == descendants.size()) {
      return root;
    }

    return currentLCA;
  }
}