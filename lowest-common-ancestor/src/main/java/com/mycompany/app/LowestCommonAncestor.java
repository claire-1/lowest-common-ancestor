package com.mycompany.app;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.NaiveLcaFinder;
import org.jgrapht.graph.DefaultEdge;

/**
 * LowestCommonAncestor.java - for finding the common ancestor of two nodes in a
 * binary tree or directed acyclic graph.
 *
 */
public class LowestCommonAncestor {

  public static Set<Node> getLowestCommonAncestor(Graph<Node, DefaultEdge> graph,
      List<Node> descendants) {

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

  public static class  Node {
    private int data;

    public Node(int data) {
        this.data = data;
    }

    // Just for debugging
    public int getData() {
      return data;
  }
  }
}