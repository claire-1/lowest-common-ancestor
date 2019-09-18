package com.mycompany.app;

// TODO figure out how to format imports
import main.java.com.mycompany.utils.Node;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class LowestCommonAncestorTest
{

    @Test
    public void givenEmptyTreeShouldReturnNull() {
        //getLowestCommonAncestor(new Node(5), new LinkedList());
        Node result = LowestCommonAncestor.getLowestCommonAncestor(null, null);
        assertEqual(result, null);
    }

}
