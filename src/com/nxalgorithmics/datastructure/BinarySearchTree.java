package com.nxalgorithmics.datastructure;

import nxopen.NXException;
import com.nxalgorithmics.visual.GraphVisualization;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

/***
 * Represents an ordered binary tree which is searchable
 */
public class BinarySearchTree extends Graph{

    protected BinaryNode rootNode;        // convenient anchor reference

    public BinarySearchTree(){

        // implementation
        this.nodes = new HashMap<>();
        this.edgesList = new LinkedList<>();
        try {
            this.graphViz = new GraphVisualization();
        } catch (NXException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /***
     * Initialize tree with a root node
     * @param rootNode Root of the tree
     */
    public BinarySearchTree(Node rootNode){
        // implementation
        this.nodes = new HashMap<>();
        this.edgesList = new LinkedList<>();

        if (!(rootNode instanceof BinaryNode)){
            System.out.println("All nodes in a binary tree need to be of type BinaryNode.");
        }else{
            this.nodes.put(rootNode.getNodeId(),rootNode);
            this.rootNode = (BinaryNode) rootNode;
            this.graphViz.createNode(this.rootNode, this);            // visualize tree root in NX
        }
    }

    @Override
    boolean isAdjacent(Node node1, Node node2) {
        return false;
    }

    @Override
    protected Node addNode(Node node) {
        BinaryNode bnode = (BinaryNode) node;
        BinaryNode potentialNode = null;
        BinaryNode lastNode = rootNode;
        while (lastNode != null){
            potentialNode = lastNode;
            if (bnode.compareTo(lastNode) < 0){
                // strictly smaller in order
                lastNode = lastNode.leftChild;
            }else{
                // greater or equal than
                lastNode = lastNode.rightChild;
            }
        }
        // potentialNode is the parent
        bnode.parent = potentialNode;
        if (bnode.compareTo(potentialNode)< 0){
            potentialNode.leftChild = bnode;
        }else{
            potentialNode.rightChild = bnode;
        }
        this.nodes.put(bnode.getNodeId(),bnode);

        // visualization
        this.graphViz.createNode(bnode,this);
        return potentialNode;
    }

    @Override
    Node removeNode(Node node) {
        return null;
    }

    @Override
    void addEdge(Node node1, Node node2) {

    }

    @Override
    void addEdgeById(UUID idNode1, UUID idNode2) {

    }

    @Override
    void addEdgeByName(String nodeName1, String nodeName2) {

    }

    @Override
    void removeEdge(Node node1, Node node2) {

    }

    @Override
    void removeEdgeById(UUID idNode1, UUID idNode2) {

    }

    @Override
    void removeEdgeByName(String nodeName1, String nodeName2) {

    }


}
