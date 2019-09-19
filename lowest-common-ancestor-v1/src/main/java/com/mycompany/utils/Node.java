package main.java.com.mycompany.utils;
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
    // TODO need this?
    // public boolean equals(Node obj) {
    //     if (obj==this) return true;
    //     if (obj==null || obj.getClass()!=this.getClass()) return false;
    //     if (obj.data != this.data || !((obj.left).equals(obj.right)) || !((obj.right).equals(this.right))) return false;
    //     return true;
    // }
    // TODO override hashCode() method
    // public int hashCode() {
    //     int result = 17;

    //     result = 31 * result + this.data;
    //     if (this.right != null) {
    //         result += right.hashCode();
    //     }

    //     if (this.left != null) {
    //         result += left.hashCode();
    //     }

    //     return result;
    // } 
}