package com.nxalgorithmics.datastructure;

public class BinaryNode extends Node {

    protected BinaryNode leftChild;
    protected BinaryNode rightChild;
    protected boolean isLeaf = false;

    public BinaryNode(Comparable nodeValue) {
        super(nodeValue);
    }

    public BinaryNode(Comparable value, String name) {
        super(value, name);
    }
    public boolean isLeaf(){
        return this.isLeaf;
    }

    public void addChild(BinaryNode node, byte direction){
        if (direction == 0) {
            // left
            this.leftChild = node;
        }else{
            // right
            this.rightChild = node;
        }
    }
}
