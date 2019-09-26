package com.mycompany.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NodeTest {

    @Test
    public void givenNoChildrenShouldParentShouldReturnEmptyList() {
        Node parent = new Node(5);
        assertEquals(0, parent.getChildren().size());
    }
    @Test
    public void givenOneNewChildNodeShouldAttachChildNodeToParentNodeAndChildShouldNotHaveChildren() {
        Node parent = new Node(5);
        Node child = new Node(4);
        parent.addChild(child);
        assertEquals(1, parent.getChildren().size());
        assertEquals(child, parent.getChildren().get(0));
        assertEquals(0, parent.getChildren().get(0).getChildren().size());
    }

    @Test
    public void givenThreeNewChildNodesShouldAttachChildrenToParentAndAllChildrenShouldNotHaveChildren() {
        Node parent = new Node(5);
        Node child1 = new Node(4);
        Node child2 = new Node(3);
        Node child3 = new Node(2);

        parent.addChild(child1);
        parent.addChild(child2);
        parent.addChild(child3);

        assertEquals(3, parent.getChildren().size());
        assertTrue(parent.getChildren().contains(child1));
        assertTrue(parent.getChildren().contains(child2));
        assertTrue(parent.getChildren().contains(child3));

        assertEquals(0, parent.getChildren().get(0).getChildren().size());
        assertEquals(0, parent.getChildren().get(1).getChildren().size());
        assertEquals(0, parent.getChildren().get(2).getChildren().size());
    }

    @Test
    public void givenOneChildWithAChildOfItsOwnShouldAttachChildToParentButNotChildsChild() {
        Node parent = new Node(5);
        Node childOfParent = new Node(4);
        Node childOfChildOfParent = new Node(3);

        parent.addChild(childOfParent);
        childOfParent.addChild(childOfChildOfParent);

        assertEquals(1, parent.getChildren().size());
        assertEquals(childOfParent, parent.getChildren().get(0));

        assertEquals(1, childOfParent.getChildren().size());
        assertEquals(childOfChildOfParent, childOfParent.getChildren().get(0));

        assertEquals(0, childOfChildOfParent.getChildren().size());
    }
}