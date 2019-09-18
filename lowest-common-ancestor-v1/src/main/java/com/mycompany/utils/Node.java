package main.java.com.mycompany.utils;
/*
 * Basic Node class in Java for nodes with integer values.
 * From here: https://algorithms.tutorialhorizon.com/lowest-common-ancestor-in-a-binary-search-tree/.
 */

public class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}