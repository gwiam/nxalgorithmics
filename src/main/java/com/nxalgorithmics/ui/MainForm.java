package com.nxalgorithmics.ui;

import com.nxalgorithmics.datastructure.BinaryNode;
import com.nxalgorithmics.datastructure.BinarySearchTree;
import com.nxalgorithmics.datastructure.Graph;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MainForm extends JFrame {

    private final Dimension DIM_WINDOW = new Dimension(800,512);
    private final Dimension DIM_TOPSPLIT = new Dimension(DIM_WINDOW.width,DIM_WINDOW.height-50);
    private final Dimension DIM_RIGHTSIDE = new Dimension(250,150);
    private final Dimension DIM_TREEVIEW = new Dimension(200,150);
    private JPanel bottomPanel;
    private JButton btnCancel;
    private JButton btnApply;
    private JButton btnOk;
    private final JTabbedPane tabActionPage;
    private JTree treeSelection;
    private JSplitPane topbottomSplitPanel;
    private JSplitPane selectionSplitPanel;
    private DefaultMutableTreeNode selectionRoot;
    private TreePath currentPathSelected;
    private String currentTabName;
    private HashMap<String,JPanel> loadedActionPanels = new HashMap<>();
    private HashMap<String, JComponent> actionComponents = new HashMap<>();
    private Graph workingGraph;

    public MainForm(){
        configureForm();
        selectionRoot = new DefaultMutableTreeNode("Algorithms");   // give it a new root
        DefaultMutableTreeNode catGraphs = new DefaultMutableTreeNode("Graphs");
        DefaultMutableTreeNode catTrees = new DefaultMutableTreeNode("Trees");
        DefaultMutableTreeNode catBST = new DefaultMutableTreeNode("Binary search trees");
        DefaultMutableTreeNode catAVL = new DefaultMutableTreeNode("AVL trees");
        DefaultMutableTreeNode cat23Trees = new DefaultMutableTreeNode("(2,3)-trees");

        // build bottom Panel
        bottomPanel = new JPanel();
        bottomPanel.setMaximumSize(new Dimension(512,25));
        btnApply = new JButton("Apply");
        btnCancel = new JButton("Cancel");
        btnOk = new JButton("OK");

        btnCancel.addActionListener(e -> this.dispose());
        btnApply.addActionListener(new ApplyEventListener());

        bottomPanel.add(btnCancel);
        bottomPanel.add(btnOk);
        bottomPanel.add(btnApply);

        // build tabs
        tabActionPage = new JTabbedPane();
        tabActionPage.setPreferredSize(DIM_RIGHTSIDE);

        // build tree structure
        catTrees.add(catBST);
        catTrees.add(catAVL);
        catTrees.add(cat23Trees);
        catGraphs.add(catTrees);
        selectionRoot.add(catGraphs);
        treeSelection = new JTree(selectionRoot);
        treeSelection.setMinimumSize(DIM_TREEVIEW);
        treeSelection.setPreferredSize(DIM_TREEVIEW);
        treeSelection.addTreeSelectionListener(new TreeSelector());
        treeSelection.expandRow(1);

        //config selectionSplit
        selectionSplitPanel = new JSplitPane();
        selectionSplitPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        selectionSplitPanel.setMinimumSize(DIM_TOPSPLIT);
        selectionSplitPanel.setPreferredSize(DIM_TOPSPLIT);
        selectionSplitPanel.setDividerLocation(0.3);
        selectionSplitPanel.setLeftComponent(treeSelection);
        selectionSplitPanel.setRightComponent(tabActionPage);

        //config topbottomSplit
        topbottomSplitPanel = new JSplitPane();
        topbottomSplitPanel.setPreferredSize(DIM_WINDOW);
        topbottomSplitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        topbottomSplitPanel.setDividerLocation(0.85);
        topbottomSplitPanel.setTopComponent(selectionSplitPanel);
        topbottomSplitPanel.setBottomComponent(bottomPanel);

        // build JFrame structure
        this.add(topbottomSplitPanel, BorderLayout.CENTER);
        this.pack();
    }

    /***
     * Configure the base JFrame
     */
    protected void configureForm(){
        this.setMinimumSize(DIM_WINDOW);
        this.setLocation(200,200);
        this.setLayout(new BorderLayout());
        this.setTitle("NXAlgorithmics: Fun with NXOpen API");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void loadActionPanel(TreePath newSelection){
        switch (newSelection.getLastPathComponent().toString()) {
            case "Binary search trees" -> {
                if (!loadedActionPanels.containsKey(ActionPanelTypes.BST_ADD_NODE)) {
                    // create a new panel
                    JPanel BSTAddNewNodePanel = new JPanel();
                    BSTAddNewNodePanel.setName(ActionPanelTypes.BST_ADD_NODE);
                    BSTAddNewNodePanel.setLayout(new GridBagLayout());
                    BSTAddNewNodePanel.setPreferredSize(DIM_RIGHTSIDE);
                    GridBagConstraints gridConstraints = new GridBagConstraints();

                    // add components
                    gridConstraints.fill = GridBagConstraints.HORIZONTAL;
                    gridConstraints.gridx = 1;
                    gridConstraints.gridy = 0;
                    JLabel labelNewNode = new JLabel("Adding a new node to the BST");
                    labelNewNode.setFont(new Font("Arial", Font.BOLD, 15));
                    BSTAddNewNodePanel.add(labelNewNode, gridConstraints);

                    // labels
                    gridConstraints.gridx = 0;
                    gridConstraints.gridy = 1;
                    BSTAddNewNodePanel.add(new JLabel("Node value: "), gridConstraints);

                    gridConstraints.gridx = 0;
                    gridConstraints.gridy = 2;
                    BSTAddNewNodePanel.add(new JLabel("Node name: "), gridConstraints);

                    // input form fields
                    gridConstraints.gridx = 1;
                    gridConstraints.gridy = 1;
                    JTextField inputField = new JTextField(20);
                    inputField.setName("txtNodeValue");
                    actionComponents.put("txtNodeValue", inputField);
                    BSTAddNewNodePanel.add(inputField, gridConstraints);

                    gridConstraints.gridx = 1;
                    gridConstraints.gridy = 2;
                    JTextField nameField = new JTextField(20);
                    nameField.setName("txtNodeName");
                    actionComponents.put("txtNodeName", nameField);
                    BSTAddNewNodePanel.add(nameField, gridConstraints);

                    tabActionPage.addTab("New node", BSTAddNewNodePanel);
                    loadedActionPanels.put(ActionPanelTypes.BST_ADD_NODE, BSTAddNewNodePanel);
                }
                currentTabName = ActionPanelTypes.BST_ADD_NODE;
            }
            case "AVL tree" -> System.out.println("AVL selected");
        }
    }

    protected static class ActionPanelTypes{
        public static final String BST_ADD_NODE = "BST_ADD_NODE";
        public static final String BST_REMOVE_NODE = "BST_REMOVE_NODE";
        public static final String BST_OPERATIONS = "BST_OPS";
    }
    /***
     * Tree view selection class
     */
    private class TreeSelector implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            // when a node gets selected
            String endNode = e.getNewLeadSelectionPath().getLastPathComponent().toString();
            if (endNode.equals("Binary search trees")) {
                //create BST panel
                loadActionPanel(e.getNewLeadSelectionPath());
            }
            currentPathSelected = e.getNewLeadSelectionPath();
        }
    }

    protected class ApplyEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentTabName.equals(ActionPanelTypes.BST_ADD_NODE)){
                JTextField fieldValue = (JTextField) actionComponents.get("txtNodeValue");
                if (workingGraph != null){
                    if (workingGraph instanceof BinarySearchTree bst){
                        if (fieldValue.getText().matches("\\d+")){
                            // integer number
                            bst.addNode(new BinaryNode<>(Integer.getInteger(fieldValue.getText())));
                            fieldValue.setText("");
                            return;
                        }
                    }
                }

                // working graph is null, create new tree with root
                workingGraph =  new BinarySearchTree(new BinaryNode<>(Integer.getInteger(fieldValue.getText())));
            }
        }
    }
}
