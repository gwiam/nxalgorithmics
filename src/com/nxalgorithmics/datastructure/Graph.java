package com.nxalgorithmics.datastructure;

import com.nxalgorithmics.visual.GraphVisualization;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

/***
 * Representing a graph structure
 */
public abstract class Graph {
    protected HashMap<UUID,Node> nodes;
    protected LinkedList<Node> edgesList; // implementation via adjacency lists
    protected Node[][] edgesMatrix; // implementation via adjacency matrices
    protected GraphVisualization graphViz;  // visualization component

    /***
     * Checks if two given nodes are adjacent in this graph
     * @param node1 First node
     * @param node2 Second node
     * @return True if they are adjacent
     */
    abstract boolean isAdjacent(Node node1, Node node2);

    /***
     * Adds a new node to the graph
     * @param node Node to add
     * @return parent node which the new node got attached to
     */
    abstract Node addNode(Node node);

    /***
     * Removes a node from the graph
     * @param node Node to remove
     * @return Returns the removed node
     */
    abstract Node removeNode(Node node);


    abstract void addEdge(Node node1, Node node2);
    abstract void addEdgeById(UUID idNode1, UUID idNode2);
    abstract void addEdgeByName(String nodeName1, String nodeName2);

    abstract void removeEdge(Node node1, Node node2);
    abstract void removeEdgeById(UUID idNode1, UUID idNode2);
    abstract void removeEdgeByName(String nodeName1, String nodeName2);
}
