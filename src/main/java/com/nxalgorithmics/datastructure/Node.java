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

    /***
     * Create a node with a stored value
     * @param nodeValue the value to be stored
     */
    public Node(T nodeValue){
        this.nodeValue = nodeValue;
        this.nodeId = UUID.randomUUID();
    }

    /***
     * Create a named node with a stored value
     * @param value the value to be stored
     * @param name the name of the node
     */
    public Node(T value, String name){
        this.nodeValue = value;
        this.nodeName = name;
        this.nodeId = UUID.randomUUID();
    }

    @Override
    public int compareTo(Node<T> o) {
        if (this.nodeValue instanceof Number && o.nodeValue instanceof Number){
            // both are numbers
            return Double.compare(((Number) this.nodeValue).doubleValue(),((Number) o.nodeValue).doubleValue());
        }else{
            // one of them isn't a number
            if (o.nodeValue instanceof T){
                //both have the same type, give standard comparison
                return this.nodeValue.compareTo(o.nodeValue);
            }else{
                return -10;
            }
        }

    }

    /***
     * Get the unique generated node id of this node
     * @return node id as UUID
     */
    public UUID getNodeId(){
        return this.nodeId;
    }

    public void setTag(Tag tag){
        this.objTag = tag;
    }

    /***
     * Get the NXTag of this node
     * @return NXTag object of this node
     */
    public Tag getTag(){
        return this.objTag;
    }

    /***
     * Get the parent node of this node
     * @return parent node
     */
    public Node<?> getParent(){
        return this.parent;
    }

    /***
     * Gets the value of this node
     * @return value of the node
     */
    public T getNodeValue(){
        return this.nodeValue;
    }

    /***
     * Gets the name of the node
     * @return the name of this node
     */
    public String getNodeName() {
        return this.nodeName;
    }
}
