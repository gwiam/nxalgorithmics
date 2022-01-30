package com.nxalgorithmics.datastructure;

import com.nxalgorithmics.visual.TreeVisualization;
import nxopen.NXException;
import com.nxalgorithmics.visual.GraphVisualization;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

/***
 * Represents an ordered binary tree which is searchable
 */
public class BinarySearchTree extends Graph{

    protected BinaryNode<?> rootNode;        // convenient anchor reference

    /***
     * Creates an empty BST
     * @param visualize If true then NX visualization will be turned on
     */
    public BinarySearchTree(boolean visualize){

        // implementation
        this.nodes = new HashMap<>();
        this.edgesList = new LinkedList<>();
        this.turnOnViz = visualize;
        if (visualize){
            try {
                this.graphViz = new TreeVisualization();
            }catch (RemoteException | NXException e) {
                e.printStackTrace();
            }
        }
    }


    /***
     * Initialize tree with a root node
     * @param rootNode Root of the tree
     * @param visualize If true then NX visualization will be turned on
     */
    public BinarySearchTree(Node<?> rootNode, boolean visualize){
        // implementation
        this.nodes = new HashMap<>();
        this.edgesList = new LinkedList<>();
        this.turnOnViz = visualize;

        if (!(rootNode instanceof BinaryNode<?>)){
            System.out.println("All nodes in a binary tree need to be of type BinaryNode.");
        }else{
            this.nodes.put(rootNode.getNodeId(),rootNode);
            this.rootNode = (BinaryNode<?>) rootNode;
            if (this.turnOnViz){
                try {
                    this.graphViz = new TreeVisualization();
                    this.graphViz.createNode(this.rootNode, this);            // visualize tree root in NX
                } catch (NXException | RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * Initialize tree with a root node
     * @param rootNode Root of the tree
     */
    public BinarySearchTree(Node<?> rootNode){
        // implementation
        this.nodes = new HashMap<>();
        this.edgesList = new LinkedList<>();

        if (!(rootNode instanceof BinaryNode)){
            System.out.println("All nodes in a binary tree need to be of type BinaryNode.");
        }else{
            this.nodes.put(rootNode.getNodeId(),rootNode);
            this.rootNode = (BinaryNode<?>) rootNode;
            if (this.turnOnViz){
                this.graphViz.createNode(this.rootNode, this);            // visualize tree root in NX
            }
        }
    }

    @Override
    boolean isAdjacent(Node<?> node1, Node<?> node2) {
        return false;
    }

    @Override
    public Node<?> addNode(Node<?> node) {
        BinaryNode<?> bnode = (BinaryNode<?>) node;
        BinaryNode<?> potentialNode = null;
        BinaryNode<?> lastNode = rootNode;

        // put node into flat list
        this.nodes.put(bnode.getNodeId(),bnode);
        if (this.rootNode != null){
            // there are already nodes inside the BST
            while (lastNode != null){
                potentialNode = lastNode;
                if (bnode.compareTo((Node)lastNode) < 0){
                    // strictly smaller in order
                    lastNode = lastNode.leftChild;
                }else{
                    // greater or equal than
                    lastNode = lastNode.rightChild;
                }
            }
            // potentialNode is the parent
            bnode.parent = potentialNode;
            if (bnode.compareTo((Node)potentialNode)< 0){
                potentialNode.leftChild = bnode;
            }else{
                potentialNode.rightChild = bnode;
            }

            // visualization
            if (this.turnOnViz){
                this.graphViz.createNode(bnode,this);
            }
            return potentialNode;
        }else{
            // there is no root node, so use this new node as root
            this.rootNode = bnode;
            // visualization
            if (this.turnOnViz){
                this.graphViz.createNode(bnode,this);
            }
            return bnode;
        }
    }

    @Override
    Node<?> removeNode(Node<?> node) {
        return null;
    }

    @Override
    void addEdge(Node<?> node1, Node<?> node2) {

    }

    @Override
    void addEdgeById(UUID idNode1, UUID idNode2) {

    }

    @Override
    void addEdgeByName(String nodeName1, String nodeName2) {

    }

    @Override
    void removeEdge(Node<?> node1, Node<?> node2) {

    }

    @Override
    void removeEdgeById(UUID idNode1, UUID idNode2) {

    }

    @Override
    void removeEdgeByName(String nodeName1, String nodeName2) {

    }

    @Override
    public int numberOfNodes() {
        return this.nodes.size();
    }

    @Override
    public void setVisualization(boolean viz) {
        this.turnOnViz = viz;
        if (viz && this.graphViz == null){
            try {
                this.graphViz = new GraphVisualization();
            } catch (NXException | RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * Traverses the BST in preorder and returns an ordered list of all encountered values
     * @return List of node values in Preorder
     */
    public ArrayList<?> traversePreorder(){
        ArrayList<Comparable<?>> results = new ArrayList<>();
        traversePreORec(this.rootNode, results);
        return results;
    }

    private void traversePreORec(BinaryNode<?> node, ArrayList<Comparable<?>> list){
        if (node != null){
            list.add(node.getNodeValue());
            traversePreORec(node.leftChild,list);
            traversePreORec(node.rightChild,list);
        }
    }

    /***
     * Traverses the BST inorder and returns an ordered list of all encountered values
     * @return List of node values in order LR
     */
    public ArrayList<?> traverseInorder(){
        ArrayList<Comparable<?>> results = new ArrayList<>();
        traverseInORec(this.rootNode, results);
        return results;
    }
    private void traverseInORec(BinaryNode<?> node, ArrayList<Comparable<?>> list){
        if (node != null){
            traversePreORec(node.leftChild,list);
            list.add(node.getNodeValue());
            traversePreORec(node.rightChild,list);
        }
    }
}
