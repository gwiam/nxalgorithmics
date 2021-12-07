package com.nxalgorithmics.visual;

public class GraphVisualConfig {

    // diameter of the node of the graph
    protected int NODE_DIAMETER = 30;

    // angle of edges coming out of nodes
    // default allows 4 beautiful edges per node
    protected int EDGE_ANGLES = 45;

    // length of the edge vec components
    protected int EDGE_COMPONENT_LEN = 40;

    // thickness of the edge
    protected int EDGE_THICKNESS = 10;

    // depth of node from root node of a tree
    protected int TREE_DEPTH_ANGLE_LIMIT = 3;

    // angle decrease when reaching the depth limit
    protected int TREE_DEPTH_ANGLE_DECREASE = 5;
}
