package com.nxalgorithmics.datastructure;

import nxopen.Tag;

import java.util.UUID;

/***
 * Represents a generic node in a graph
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
    private String nodeName;
    private final UUID nodeId;
    private T nodeValue;
    private Tag objTag;     // tag reference for NXOpen
    protected Node<?> parent;

    public Node(T nodeValue){
        this.nodeValue = nodeValue;
        this.nodeId = UUID.randomUUID();
    }

    public Node(T value, String name){
        this.nodeValue = value;
        this.nodeName = name;
        this.nodeId = UUID.randomUUID();
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.nodeValue.compareTo(o.nodeValue);
    }

    public UUID getNodeId(){
        return this.nodeId;
    }

    public void setTag(Tag tag){
        this.objTag = tag;
    }

    public Tag getTag(){
        return this.objTag;
    }

    public Node<?> getParent(){
        return this.parent;
    }

    /**
     * Gets the value of this node
     * @return value of the node
     */
    public T getNodeValue(){
        return this.nodeValue;
    }
    public String getNodeName() {
        return this.nodeName;
    }
}
