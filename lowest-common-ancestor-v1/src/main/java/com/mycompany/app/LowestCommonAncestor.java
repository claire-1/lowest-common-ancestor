package com.mycompany.app;

import java.util.List;

import com.mycompany.utils.Node;

/**
 * LowestCommonAncestor.java - for finding the common ancestor of two nodes in a
 * binary tree. Adapted from:
 * https://medium.com/algorithm-problems/lowest-common-ancestor-of-a-binary-tree-8f69531087b1
 *
 */
public class LowestCommonAncestor {
  // TODO are we supposed to handle finding ancestors for two nodes only or also
  // for
  // handling more than two nodes? --> need access to directions
  public static Node getLowestCommonAncestor(Node root, List<Node> descendants) {
    if (root == null || descendants.size() == 0) {
      return null;
    }

    return root;
    // if ((root == null) || (node1 == null) || (node2 == null)) {
    //   return null;
    // }

    // if ((node1 == root) || (node2 == root)) {
    //   return root;
    // }
    // Node left = getLowestCommonAncestor(root.getLeft(), node1, node2);
    // Node right = getLowestCommonAncestor(root.getRight(), node1, node2);
    // if (left != null && right != null) {
    //   return root;
    // }

    // Node ancestor;
    // if (left == null) {
    //   ancestor = right;
    // } else {
    //   ancestor = left;
    // }

    // return ancestor;
   
  }
}