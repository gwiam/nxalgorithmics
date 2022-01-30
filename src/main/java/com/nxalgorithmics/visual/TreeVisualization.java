package com.nxalgorithmics.visual;

import com.nxalgorithmics.datastructure.BinaryNode;
import com.nxalgorithmics.datastructure.BinarySearchTree;
import com.nxalgorithmics.datastructure.Node;
import com.nxalgorithmics.datastructure.Graph;
import nxopen.*;
import nxopen.features.CylinderBuilder;
import nxopen.features.Feature;
import nxopen.features.SphereBuilder;
import nxopen.geometricutilities.BooleanOperation;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Set;

public class TreeVisualization extends GraphVisualization{
    /***
     * Constructor
     */
    public TreeVisualization() throws NXException, RemoteException {
        super();
    }

    @Override
    public void createNode(Node<?> node, Graph graph){
        BinarySearchTree bst = (BinarySearchTree) graph;    // convert to useful object
        try {
            SphereBuilder builder = this.workingPart.features().createSphereBuilder(null);  // create an empty sphere builder
            builder.setType(SphereBuilder.Types.CENTER_POINT_AND_DIAMETER);                 // create sphere by center and diameter method

            Point3d sphereCenterPt = computeNewTreeNodeCenter(node);                   // get node center
            Point sphereCenter = workingPart.points().createPoint(sphereCenterPt);


            builder.setCenterPoint(sphereCenter);   // set center of the sphere
            builder.diameter().setValue(this.vizConfig.NODE_DIAMETER);

            builder.booleanOption().setType(BooleanOperation.BooleanType.CREATE);   // create sphere

            Feature objSphere = builder.commitFeature();
            objSphere.setName(node.getNodeName());
            // add to the list
            this.graphFeatures.put(objSphere.tag(),objSphere);
            this.featurePnts.put(objSphere.tag(),new HashMap<>());
            this.featurePnts.get(objSphere.tag()).put("center", sphereCenter);
            // associate graph structure with visual NXOpen structure
            node.setTag(objSphere.tag());

            builder.destroy();

            // check if node has parent, if so, then construct an edge
            if (node.getParent() != null){
                //not root node
                CylinderBuilder cbuilder = this.workingPart.features().createCylinderBuilder(null);
                cbuilder.setType(CylinderBuilder.Types.AXIS_DIAMETER_AND_HEIGHT);

                cbuilder.diameter().setValue(this.vizConfig.EDGE_THICKNESS);

                HashMap<String, NXObject> parentPoints = this.featurePnts.get(node.getParent().getTag());
                Point parentCenter = (Point) parentPoints.get("center");

                // calculate direction vector by comparing new center point with old center point
                Vector3d direction = new Vector3d(sphereCenter.coordinates().x -parentCenter.coordinates().x,
                                                 sphereCenter.coordinates().y - parentCenter.coordinates().x,
                                                sphereCenter.coordinates().z-parentCenter.coordinates().z);

                cbuilder.setDirection(direction);
                cbuilder.booleanOption().setType(BooleanOperation.BooleanType.CREATE);      // create edge cylinder
                Feature objEdge = cbuilder.commitFeature();
                objEdge.setName(node.getNodeName() + "_edge");
                // add to the list
                this.graphFeatures.put(objEdge.tag(),objEdge);

                // put new key into mapping with key being automatically incremented
                this.featurePnts.get(objSphere.tag()).put("edge" + String.valueOf(
                        checkOccurrenceOfStringInSet(this.featurePnts.get(objSphere.tag()).keySet(),"edge")),
                        objEdge);
                cbuilder.destroy();
            }

        } catch (NXException | RemoteException e) {
            e.printStackTrace();
        }

    }

    protected int checkOccurrenceOfStringInSet(Set<String> strings, String keyword){
        int counter = 0;
        for (String str: strings) {
            if (str.contains(keyword)) {
                counter++;
            }
        }
        return counter;
    }

    public Point3d computeNewTreeNodeCenter(Node<?> node){
        //TODO: compute new center point
        // figure out the last node from which this node needs to be attached to
        if (node instanceof BinaryNode<?> bnode){
            // we are in a binary search tree
            if (bnode.getParent() == null){
                // we do not have a parent so just set the node as root in a tree at origin
                return new Point3d(0,0,0);
            }else{
                // get the NX tag of the parent
                Tag parentTag = bnode.getParent().getTag();
                // get associated center point from this parents nodes sphere
                Point parentCenter = (Point)this.featurePnts.get(parentTag).get("center");

                // get new coordinate point
                try {
                    Point3d newNodeCenter = parentCenter.coordinates();
                    newNodeCenter.x -= this.vizConfig.EDGE_COMPONENT_LEN;
                    newNodeCenter.y -= this.vizConfig.EDGE_COMPONENT_LEN;
                    return newNodeCenter;
                } catch (NXException | RemoteException e) {
                    e.printStackTrace();
                }
            }

        }
        return new Point3d();
    }
}
