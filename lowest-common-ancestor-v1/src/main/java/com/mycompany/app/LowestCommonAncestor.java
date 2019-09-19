package com.mycompany.app;

import main.java.com.mycompany.utils.Node;
/**
 * LowestCommonAncestor.java - for finding the common ancestor of two nodes
 * in a binary tree.
 *
 */
public class LowestCommonAncestor
{
  // TODO are we supposed to handle finding ancestors for two nodes only or also for
  // handling more than two nodes? --> need access to directions
  public static Node getLowestCommonAncestor(Node root, Node node1, Node node2) {
    if ((root == null) || (node1 == null) || (node2 == null)) {
      return null;
    }

    if ((node1 == root) || (node2 == root)) {
      return root;
    }
    Node left = getLowestCommonAncestor(root.getLeft(), node1, node2);
    Node right = getLowestCommonAncestor(root.getRight(), node1, node2);
    if (left != null && right != null) {
      return root;
    }

    Node ancestor;
    if (left == null) {
      ancestor = right;
    } else {
      ancestor = left;
    }

    return ancestor;
  }
}