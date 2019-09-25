package com.mycompany.app;

import java.util.List;

import com.mycompany.utils.Node;

/**
 * LowestCommonAncestor.java - for finding the common ancestor of two nodes in a
 * binary tree. Adapted from:
 * https://www.careercup.com/question?id=13437666
 *
 */
public class LowestCommonAncestor {
  public static Node getLowestCommonAncestor(Node root, List<Node> descendants) {
    // TODO what happens if a node isn't in the tree?

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
      Node result = getLowestCommonAncestor(node, descendants);
      if (result != null) {
        count++;
        currentLCA = result;
      }
    }

    if (count == descendants.size()) {
      return root; // TODO check this with what the linear case should be
    }

    return currentLCA;
   
  }
}