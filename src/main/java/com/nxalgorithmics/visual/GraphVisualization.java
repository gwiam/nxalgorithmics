package com.nxalgorithmics.visual;

import nxopen.*;

import java.rmi.RemoteException;
import java.util.HashMap;

import com.nxalgorithmics.datastructure.*;
import nxopen.features.Feature;

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

    protected HashMap<Tag,HashMap<String,NXObject>> featurePnts;       // list of points for a feature tag
    /***
     * Create a GraphVisualization object for NX coupling
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

    /***
     * Create a new node in the NX visualization graph
     * @param node new node object
     * @param graph adding node to this graph
     */
    public void createNode(Node<?> node, Graph graph){

    }

    public void setConfig(GraphVisualConfig config){
        this.vizConfig = config;
    }

    /**
     * Write a log message
     * @param msg message
     */
    public void log(String msg){
        try {
            this.logConsole.writeFullline(msg);
        } catch (RemoteException | NXException e) {
            e.printStackTrace();
        }
    }

}
