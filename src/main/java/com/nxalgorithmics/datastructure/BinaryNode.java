package com.nxalgorithmics.datastructure;

/***
 * Representation of a node in a binary search tree (BST)
 * @param <T> data type of the value stored in the node
 */
public class BinaryNode<T extends Comparable<T>> extends Node<T> {

    protected BinaryNode<?> leftChild;
    protected BinaryNode<?> rightChild;
    protected boolean isLeaf = false;

    /***
     * Create a new node
     * @param nodeValue the value stored in the node
     */
    public BinaryNode(T nodeValue) {
        super(nodeValue);
    }

    public BinaryNode(T value, String name) {
        super(value, name);
    }
    public boolean isLeaf(){
        return this.isLeaf;
    }

    /***
     * Add a child to this node
     * @param node the node to add
     * @param direction add the node to the left or right
     */
    public void addChild(BinaryNode<?> node, byte direction){
        if (direction == 0) {
            // left
            this.leftChild = node;
        }else{
            // right
            this.rightChild = node;
        }
    }
}
