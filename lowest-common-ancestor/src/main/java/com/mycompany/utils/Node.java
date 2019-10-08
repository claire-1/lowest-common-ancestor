package com.mycompany.utils;
/*
 * Basic Node class in Java for nodes with integer values.
 * From here: https://stackoverflow.com/questions/3522454/java-tree-data-structure
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