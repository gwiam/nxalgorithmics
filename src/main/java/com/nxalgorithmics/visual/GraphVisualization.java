package com.nxalgorithmics.visual;

import nxopen.*;

import java.rmi.RemoteException;
import java.util.HashMap;

import com.nxalgorithmics.datastructure.*;
import nxopen.features.CylinderBuilder;
import nxopen.features.Feature;
import nxopen.features.SphereBuilder;
import nxopen.geometricutilities.BooleanOperation;

/***
 * Visualizing graphing components in NX
 */
public class GraphVisualization {
    protected UFSession theUFSession;
    protected Session theSession;
    protected GraphVisualConfig vizConfig;

    protected ListingWindow logConsole;

    protected Part workingPart;

    protected HashMap<Tag,Feature> graphFeatures;   // list of features

    protected HashMap<Tag,HashMap<String,Point>> featurePnts;       // list of points for a feature tag
    /***
     * Constructor
     */
    public GraphVisualization() throws NXException, RemoteException {
        this.theUFSession = (UFSession) SessionFactory.get("UFSession");
        this.theSession = (Session) SessionFactory.get("Session");

        // if log window is not open yet, open it
        if (!this.theSession.listingWindow().isOpen()){
            this.theUFSession.ui().openListingWindow(); // trying to open listing window for logging in the background
        }
        this.logConsole = this.theSession.listingWindow();

        this.workingPart = this.theSession.parts().work();      // get the current working part of the session

        this.graphFeatures = new HashMap<>();
        this.featurePnts = new HashMap<>();
        // always go from origin and construct graph structure from there
    }

    public void setConfig(GraphVisualConfig config){
        this.vizConfig = config;
    }

    public void createNode(Node<?> node, BinarySearchTree bst){
        try {
            SphereBuilder builder = this.workingPart.features().createSphereBuilder(null);  // create an empty sphere builder
            builder.setType(SphereBuilder.Types.CENTER_POINT_AND_DIAMETER);                 // create sphere by center and diameter method

            Point3d sphereCenterPt = computeNewTreeNodeCenter(node);
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
            /*if (node.getParent() != null){
                CylinderBuilder cbuilder = this.workingPart.features().createCylinderBuilder(null);
                cbuilder.setType(CylinderBuilder.Types.AXIS_DIAMETER_AND_HEIGHT);

                cbuilder.diameter().setValue(this.vizConfig.EDGE_THICKNESS);
                //cbuilder.setDirection();

            }*/

        } catch (NXException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

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
                Point parentCenter = this.featurePnts.get(parentTag).get("center");

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

    public void createEdge(){

    }
    public void log(String msg){
        try {
            this.logConsole.writeFullline(msg);
        } catch (RemoteException | NXException e) {
            e.printStackTrace();
        }
    }

}
