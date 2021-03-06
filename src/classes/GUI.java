/**
 * Copyright (c) 2016 by Adrian Luna
 *                       Ricardo Gonzales
 * All Rights Reserved
 *
 * Authors: Adrian Luna
 *          Ricardo Gonzales
 *
 * Porpuse: Class that contains the infrastructure for the Graphic User interface
 **/

package classes;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class GUI extends JFrame 
{
    /** Constants **/
    private static final long serialVersionUID = 1L;
    private static final int  SUCCESS = 0;
    private static final int  FAIL = -1;

    /** Variables **/
    private boolean        showMap = true;
    private Graph          graph;
    
    /** UI Components **/
    private JPanel         mainPanel;
    private JTabbedPane    mainTabPanel;
    
    /** UI tab **/
    private JPanel         panelGraphs; 
    private JLabel         map;
    private JTextArea      textArea;
    private JLabel         city1Label;
    private JLabel         city2Label;
    private JButton        MSTButton;
    private JButton        SPAButton;
    private JLabel         algorithmLabel;
    private JComboBox      city1ComboBox;
    private JComboBox      city2ComboBox;
    private JButton        showButton;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the UI.
     */
    public GUI() 
    {
        //Create graph
        graph = new Graph();
        
        setResizable(false);
        setTitle("Grafos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 668, 680);
        
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainPanel.setLayout(new BorderLayout(0, 0));
        setContentPane(mainPanel);
        
        mainTabPanel = new JTabbedPane(JTabbedPane.TOP);
        mainTabPanel.setBackground(Color.WHITE);
        mainPanel.add(mainTabPanel, BorderLayout.CENTER);
        
        createTab();
    }
    
    /** UI tab **/
    private void createTab()
    {
        String[] nodes = graph.getNodesAsStrings();
        
        panelGraphs = new JPanel();
        panelGraphs.setBackground(Color.WHITE);
        mainTabPanel.addTab("Grafos", null, panelGraphs, null);
        panelGraphs.setLayout(null);
        
        map = new JLabel("");
        map.setBounds(10, 0, 627, 395);
        map.setIcon(new ImageIcon(GUI.class.getResource("/images/mapa.png")));
        panelGraphs.add(map);
        
        MSTButton = new JButton("MST");
        MSTButton.setBounds(10, 409, 119, 23);
        panelGraphs.add(MSTButton);
        
        SPAButton = new JButton("SPA");
        SPAButton.setBounds(139, 409, 119, 23);
        panelGraphs.add(SPAButton);
        
        JLabel outputLabel = new JLabel("Resultado");
        outputLabel.setBounds(10, 443, 64, 14);
        panelGraphs.add(outputLabel);
        
        city1Label = new JLabel("Ciudad #1:");
        city1Label.setBounds(268, 413, 119, 14);
        panelGraphs.add(city1Label);
        
        city2Label = new JLabel("Ciudad #2:");
        city2Label.setBounds(455, 413, 89, 14);
        panelGraphs.add(city2Label);
        
        algorithmLabel = new JLabel("");
        algorithmLabel.setBounds(10, 457, 64, 14);
        panelGraphs.add(algorithmLabel);
        
        city1ComboBox = new JComboBox(nodes);
        city1ComboBox.setBounds(330, 410, 119, 20);
        panelGraphs.add(city1ComboBox);
        
        city2ComboBox = new JComboBox(nodes);
        city2ComboBox.setBounds(518, 410, 119, 20);
        panelGraphs.add(city2ComboBox);
        
        showButton = new JButton("Mostrar Grafo");
        showButton.setBounds(503, 204, 119, 23);
        panelGraphs.add(showButton);
        
        textArea = new JTextArea();
        textArea.setBackground(SystemColor.control);
        textArea.setEditable(false);
        textArea.setBounds(75, 443, 562, 159);
        panelGraphs.add(textArea);
        
        createActions();
    }
    /** Validates **/
    private int validateMST()
    {
        int ret = SUCCESS;
        
        return ret;
    }
    
    private int validateSPA()
    {
        int ret = SUCCESS;
        
        if (city1ComboBox.getSelectedIndex() == city2ComboBox.getSelectedIndex())
        {
            //Show error message
            JOptionPane.showMessageDialog(null,
                                          "Las ciudades no pueden ser las mismas",
                                          "Error", 
                                          JOptionPane.ERROR_MESSAGE);
            ret = FAIL;
        }
        
        return ret;
    }
    
    private void createActions()
    {                        
        MSTButton.addActionListener(new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
              if (validateMST() == SUCCESS)
              {
                  algorithmLabel.setText("del MST");
                  textArea.setText(MST.run(graph));
              }
          }
        });
        
        SPAButton.addActionListener(new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
              if (validateSPA() == SUCCESS)
              {
                  algorithmLabel.setText("del SPA");
                  textArea.setText(SPA.run(graph,
                                           city1ComboBox.getSelectedIndex(),
                                           city2ComboBox.getSelectedIndex()));
              }
          }
        });
        
        showButton.addActionListener(new ActionListener() 
        {
          public void actionPerformed(ActionEvent e) 
          {
              //toggle button
              if (showMap)
              {
                  showMap = false;
                  showButton.setText("Mostrar Mapa");
                  map.setIcon(new ImageIcon(GUI.class.getResource("/images/grafo.png")));
              }
              else
              {
                  showMap = true;
                  showButton.setText("Mostrar Grafo");
                  map.setIcon(new ImageIcon(GUI.class.getResource("/images/mapa.png")));
              }
          }
        });
    }
}
