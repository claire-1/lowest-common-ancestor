package com.mycompany.utils;
/*
 * Basic Node class in Java for nodes with integer values.
 * From here: https://algorithms.tutorialhorizon.com/lowest-common-ancestor-in-a-binary-search-tree/.
 * TODO: need this? equals adapted from: https://stackoverflow.com/questions/26864477/assert-assertequals-fails-for-custom-class-objects.
 */

import java.util.LinkedList;
import java.util.List;

public class Node {
    private int data;
    private List<Node> children;

    public Node(int data) {
        this.data = data;
        this.children = new LinkedList<>();
    }

    public void addChild(Node newChild) {
        this.children.add(newChild);
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getData() {
        return data;
    }
}