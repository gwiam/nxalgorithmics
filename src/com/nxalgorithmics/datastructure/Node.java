package com.nxalgorithmics.datastructure;

import nxopen.Tag;

import java.util.UUID;

/***
 * Represents a generic node in a graph
 */
public class Node implements Comparable<Node>{
    private String nodeName;
    private final UUID nodeId;
    private Comparable nodeValue;
    private Tag objTag;     // tag reference for NXOpen
    protected Node parent;

    public Node(Comparable nodeValue){
        this.nodeValue = nodeValue;
        this.nodeId = UUID.randomUUID();
    }

    public Node(Comparable value, String name){
        this.nodeValue = value;
        this.nodeName = name;
        this.nodeId = UUID.randomUUID();
    }

    @Override
    public int compareTo(Node o) {
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

    public Node getParent(){
        return this.parent;
    }
}
