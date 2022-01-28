package com.nxalgorithmics.test;

import com.nxalgorithmics.datastructure.BinaryNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBinaryNode {

    @Test
    @DisplayName("Testing different creation methods for binary nodes")
    public void testCreateNode(){
        BinaryNode<?> node;
        node = new BinaryNode<>("strNode");
        assertEquals(node.getNodeValue(),"strNode","String initialization does not work.");
        node = new BinaryNode<>(20);
        assertEquals(node.getNodeValue(), 20, "Integer initialization does not work.");
        node = new BinaryNode<>(2/3.0);
        assertEquals(node.getNodeValue(), 2/3.0, "Float initialization does not work.");
    }

    @Test
    @DisplayName("Testing value comparison method of binary nodes")
    public void testNodeCompareInt(){
        BinaryNode<Integer> cmpNode = new BinaryNode<>(50);
        assertEquals(cmpNode.compareTo(new BinaryNode<>(50)),0, "Node int equal-comparison failed.");
        assertEquals(cmpNode.compareTo(new BinaryNode<>(83)), -1,"Node int less-comparison test failed.");

    }
}
