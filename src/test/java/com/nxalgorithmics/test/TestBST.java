package com.nxalgorithmics.test;

import com.nxalgorithmics.datastructure.BinaryNode;
import  com.nxalgorithmics.datastructure.BinarySearchTree;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.lang.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBST {
    protected BinarySearchTree bst;

    @Test
    @Order(1)
    @DisplayName("Testing to create a BST with no nodes")
    public void testCreatingEmptyBST(){
        bst = new BinarySearchTree(false);
        assertEquals(0, bst.numberOfNodes(),"Empty BST is not empty.");
    }

    @Test
    @Order(2)
    @DisplayName("Testing to create a BST with a single node")
    public void testCreatingOneNodeBST(){
        bst = new BinarySearchTree(new BinaryNode<>(0),false);
        assertEquals(1, bst.numberOfNodes(),"BST does not have 1 node.");
    }

    @Test
    @DisplayName("Testing to create empty BST and then adding a new node")
    public void testAddOneNode(){
        bst = new BinarySearchTree(false);        // turn off visual components for test
        bst.addNode(new BinaryNode<>(5));
        assertEquals(1, bst.numberOfNodes(),"Expected BST number of node to be 1.");
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
        assertEquals(3, bst.numberOfNodes(),"Expected BST number of nodes to be 3.");
        String preorderStr = bst.traversePreorder().toString();
        assertEquals(Arrays.asList(0, -1, 6).toString(),preorderStr , "Preorder traversal results failed.");
    }

    @Test
    @DisplayName("Testing two-layered integer BST")
    public void testBuildTwoLayerIntBST(){
        bst = new BinarySearchTree(new BinaryNode<>(0),false); // no viz component

        // construct simple tree
        /*
                         0
                       /   \
                      -3     6
                    /   \   /  \
                  -10   -2  4   9
         */
        bst.addNode(new BinaryNode<>(6));
        bst.addNode(new BinaryNode<>(-3));
        bst.addNode(new BinaryNode<>(-10));
        bst.addNode(new BinaryNode<>(4));
        bst.addNode(new BinaryNode<>(-2));
        bst.addNode(new BinaryNode<>(9));
        assertEquals(7,bst.numberOfNodes(),"Expected BST number of nodes to be 7.");
        String preorderStr = bst.traversePreorder().toString();
        assertEquals(Arrays.asList(0, -3, -10,-2,6,4,9).toString(),preorderStr , "Preorder traversal results failed.");
        String inorderStr = bst.traverseInorder().toString();
        assertEquals(Arrays.asList(-3, -10,-2,0,6,4,9).toString(),inorderStr , "Inorder traversal results failed.");
    }

    @Test
    @DisplayName("Testing to add nodes for a mixed number type BST from empty tree")
    public void testBuildSimpleMixedNumberBST(){
        bst = new BinarySearchTree(false); // no viz component

        // construct simple tree
        /*
                         0
                       /   \
                     -1.5    6
         */
        bst.addNode(new BinaryNode<>(0));
        bst.addNode(new BinaryNode<>(6));
        bst.addNode(new BinaryNode<>(-1.5));
        assertEquals(3,bst.numberOfNodes(),"Expected BST number of nodes to be 3.");
        String preorderStr = bst.traversePreorder().toString();
        assertEquals(Arrays.asList(0, -1.5, 6).toString(), preorderStr, "Preorder traversal results failed.");
    }
    @Test
    @DisplayName("Testing two-layered double-typed BST")
    public void testBuildTwoLayerDblBST(){
        bst = new BinarySearchTree(new BinaryNode<>(1),false); // no viz component

        // construct simple tree
        /*
                          1
                       /    \
                     -3.2     e
                    /   \    /  \
                  -5.1  -2  1.3   9
         */
        bst.addNode(new BinaryNode<>(Math.E));
        bst.addNode(new BinaryNode<>(-3.2));
        bst.addNode(new BinaryNode<>(-5.1));
        bst.addNode(new BinaryNode<>(1.3));
        bst.addNode(new BinaryNode<>(-2));
        bst.addNode(new BinaryNode<>(9));
        assertEquals(7,bst.numberOfNodes(),"Expected BST number of nodes to be 7.");
        String preorderStr = bst.traversePreorder().toString();
        assertEquals(Arrays.asList(1, -3.2, -5.1, -2, Math.E, 1.3, 9).toString(),preorderStr , "Preorder traversal results failed.");
        String inorderStr = bst.traverseInorder().toString();
        assertEquals(Arrays.asList(-3.2,-5.1,-2,1,Math.E,1.3,9).toString(),inorderStr , "Inorder traversal results failed.");
    }
    @Test
    @DisplayName("Testing to add nodes for string BST from empty tree")
    public void testBuildSimpleStringBST(){
        bst = new BinarySearchTree(false); // no viz component

        // construct simple tree
        /*
                      NullRoot
                       /   \
                   Short    RightSideLongString
         */
        bst.addNode(new BinaryNode<>("NullRoot"));
        bst.addNode(new BinaryNode<>("Short"));
        bst.addNode(new BinaryNode<>("RightSideLongString"));
        assertEquals(3,bst.numberOfNodes(),"Expected BST number of nodes to be 3.");
        String preorderStr = bst.traversePreorder().toString();
        assertEquals(Arrays.asList("NullRoot", "Short", "RightSideLongString").toString(), preorderStr, "Preorder traversal results failed.");
    }


}
