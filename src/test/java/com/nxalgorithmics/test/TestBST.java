package com.nxalgorithmics.test;

import com.nxalgorithmics.datastructure.BinaryNode;
import  com.nxalgorithmics.datastructure.BinarySearchTree;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBST {
    protected BinarySearchTree bst;

    @Test
    @DisplayName("Testing to create a BST with no nodes")
    public void testCreatingEmptyBST(){
        bst = new BinarySearchTree(false);
        assertEquals(bst.numberOfNodes(),0,"Empty BST is not empty.");
    }

    @Test
    @DisplayName("Testing to create a BST with a single node")
    public void testCreatingOneNodeBST(){
        bst = new BinarySearchTree(new BinaryNode<>(0),false);
        assertEquals(bst.numberOfNodes(),1,"BST does not have 1 node.");
    }

    @Test
    @DisplayName("Testing to create empty BST and then adding a new node")
    public void testAddOneNode(){
        bst = new BinarySearchTree(false);        // turn off visual components for test
        bst.addNode(new BinaryNode<>(5));
        assertEquals(bst.numberOfNodes(),1,"Expected BST number of node to be 1.");
    }
    @Test
    @DisplayName("Testing to add nodes for a simple integer BST from empty tree")
    public void testBuildSimpleIntBST(){
        bst = new BinarySearchTree(false); // no viz component

        // construct simple tree
        /*
                         0
                       /   \
                      -1    6
         */
        bst.addNode(new BinaryNode<>(0));
        bst.addNode(new BinaryNode<>(6));
        bst.addNode(new BinaryNode<>(-1));
        assertEquals(bst.numberOfNodes(),3,"Expected BST number of nodes to be 3.");
        String preorderStr = bst.traversePreorder().toString();
        assertEquals(preorderStr, Arrays.asList(0, -1, 6).toString(), "Preorder traversal results failed.");
    }
}
