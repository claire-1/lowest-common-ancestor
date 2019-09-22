package com.mycompany.utils;
/*
 * Basic Node class in Java for nodes with integer values.
 * From here: https://algorithms.tutorialhorizon.com/lowest-common-ancestor-in-a-binary-search-tree/.
 * TODO: need this? equals adapted from: https://stackoverflow.com/questions/26864477/assert-assertequals-fails-for-custom-class-objects.
 */

public class Node {
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public int getData() {
        return this.data; // TODO delete this; just for debugging
    }
}