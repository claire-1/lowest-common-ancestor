package com.mycompany.app;

// TODO figure out how to format imports
import main.java.com.mycompany.utils.Node;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class LowestCommonAncestorTest
{

    @Test
    public void givenEmptyTreeShouldReturnNull() {
        Node result = LowestCommonAncestor.getLowestCommonAncestor(null, null);
        assertNull(result);
    }

}
