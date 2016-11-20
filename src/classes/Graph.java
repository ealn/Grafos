/**
 * Copyright (c) 2016 by Adrian Luna
 *                       Ricardo Gonzales
 * All Rights Reserved
 *
 * Authors: Adrian Luna
 *          Ricardo Gonzales
 *
 * Porpuse: Class that contains the graph implementation
 **/

package classes;

import java.util.ArrayList;

public class Graph 
{   
    private ArrayList <String> nodes;      //Nodes
    private ArrayList <Edges> connections; //Connections
    private static int V;                  //Number of Vertices
    private static int[][] matrix;         //Matrix of connections
    
    // This matrix is generated dynamically
    //                 {    Gdl      Leon      Ags       Zacatecas Durango   San Luis P.  Mazatlan  Tepic     Mexico    Monterrey 
    // Guadalajara          {0,      219,      221,      0,        0,        0,           0,        210,      0,        0},
    // Leon                 {219,    0,        126,      0,        0,        181,         0,        0,        405,      0},
    // Aguascalientes       {221,    126,      0,        129,      0,        166,         0,        0,        0,        0},
    // Zacatecas            {0,      0,        129,      0,        291,      190,         0,        0,        0,        462},
    // Durango              {0,      0,        0,        291,      0,        0,           258,      0,        0,        586},
    // San Luis Potosi      {0,      181,      166,      190,      0,        0,           0,        0,        418,      514},
    // Mazatlan             {0,      0,        0,        0,        258,      0,           0,        272,      0,        0},
    // Tepic                {210,    0,        0,        0,        0,        0,           272,      0,        0,        0},
    // Mexico               {0,      405,      0,        0,        0,        418,         0,        0,        0,        0},
    // Monterrey            {0,      0,        0,        462,      586,      514,         0,        0,        0,        0}};
    
    public Graph()
    {
        //Allocate memory for nodes and connections
        nodes = new ArrayList<String>();
        connections = new ArrayList<Edges>();
        
        //Insert Nodes
        createNodes();
        
        //Add connections
        createConections();
        
        //Set size
        V = nodes.size();
        
        //Create Matrix
        createMatrix(V);
    }
    
    public ArrayList <String> getNodes()
    {
        return nodes;
    }
    
    public int getNumberOfVertices()
    {
        return V;
    }
    
    public int[][] getMatrix()
    {
        return matrix;
    }
    
    private void createNodes()
    {
        nodes.add("Guadalajara");
        nodes.add("Leon");
        nodes.add("Aguascalientes");
        nodes.add("Zacatecas");
        nodes.add("Durango");
        nodes.add("San Luis Potosi");
        nodes.add("Mazatlan");
        nodes.add("Tepic");
        nodes.add("Mexico");
        nodes.add("Monterrey");
    }
    
    private void createConections()
    {
        connections.add(new Edges("Guadalajara", "Leon", 219));
        connections.add(new Edges("Guadalajara", "Aguascalientes", 221));
        connections.add(new Edges("Guadalajara", "Tepic", 210));
        
        connections.add(new Edges("Leon", "Guadalajara", 219));
        connections.add(new Edges("Leon", "Aguascalientes", 126));
        connections.add(new Edges("Leon", "San Luis Potosi", 181));
        connections.add(new Edges("Leon", "Mexico", 405));
        
        connections.add(new Edges("Aguascalientes", "Guadalajara", 221));
        connections.add(new Edges("Aguascalientes", "Leon", 126));
        connections.add(new Edges("Aguascalientes", "Zacatecas", 129));
        connections.add(new Edges("Aguascalientes", "San Luis Potosi", 166));
        
        connections.add(new Edges("Zacatecas", "Aguascalientes", 129));
        connections.add(new Edges("Zacatecas", "Durango", 291));
        connections.add(new Edges("Zacatecas", "San Luis Potosi", 190));
        connections.add(new Edges("Zacatecas", "Monterrey", 462));
        
        connections.add(new Edges("Durango", "Zacatecas", 291));
        connections.add(new Edges("Durango", "Mazatlan", 258));
        connections.add(new Edges("Durango", "Monterrey", 586));
        
        connections.add(new Edges("San Luis Potosi", "Leon", 181));
        connections.add(new Edges("San Luis Potosi", "Aguascalientes", 166));
        connections.add(new Edges("San Luis Potosi", "Zacatecas", 190));
        connections.add(new Edges("San Luis Potosi", "Mexico", 418));
        connections.add(new Edges("San Luis Potosi", "Monterrey", 514));
        
        connections.add(new Edges("Mazatlan", "Durango", 258));
        connections.add(new Edges("Mazatlan", "Tepic", 272));
        
        connections.add(new Edges("Tepic", "Guadalajara", 210));
        connections.add(new Edges("Tepic", "Mazatlan", 272));
        
        connections.add(new Edges("Mexico", "Leon", 405));
        connections.add(new Edges("Mexico", "San Luis Potosi", 418));
        
        connections.add(new Edges("Monterrey", "Zacatecas", 462));
        connections.add(new Edges("Monterrey", "Durango", 586));
        connections.add(new Edges("Monterrey", "San Luis Potosi", 514));
    }
    
    private void createMatrix(int size)
    {
        matrix = new int[size][size];
        int srcIndex;
        int dstIndex;
        int weight;
        int i;
        
        for (i = 0; i < connections.size(); i++)
        {
            //get values
            srcIndex = getIndexFromNodeList(connections.get(i).getSource());
            dstIndex = getIndexFromNodeList(connections.get(i).getDestination());
            weight = connections.get(i).getWeight();
            
            //insert values to the matrix
            matrix[srcIndex][dstIndex] = weight;   
        }
    }

    public int getIndexFromNodeList(String str)
    {
        int ret = 0;
        int i;
        
        for (i = 0; i < nodes.size(); i++)
        {
            if (str.equals(nodes.get(i)))
            {
                ret = i;
                break;
            }
        }
        
        return ret;
    }
    
    public String[] getNodesAsStrings()
    {
        String[] listNodes = new String[nodes.size()];
        
        for (int i = 0; i < nodes.size(); i++)
        {
            listNodes[i] = nodes.get(i);
        }
        
        return listNodes;
    }
    
    public ArrayList<Edges> getSortedConnections ()
    {
        ArrayList<Edges> sortedList = new ArrayList<Edges>();
        Edges tempEdge = new Edges(null, null, 0);
        int i;
        int j;
        
        //Copy connection list
        for (i = 0; i < connections.size(); i++)
        {
            sortedList.add(new Edges(connections.get(i).getSource(),
                                     connections.get(i).getDestination(),
                                     connections.get(i).getWeight()));
        }
        
        //Sort list using the bubble algorithm
        for (i = 0; i < sortedList.size(); i++)
        {
            for (j = 1; j < (sortedList.size() - i); j++)
            {           
                if (sortedList.get(j - 1).getWeight() > sortedList.get(j).getWeight())
                {
                    //save data
                    tempEdge.setSource(sortedList.get(j -1).getSource());
                    tempEdge.setDestination(sortedList.get(j -1).getDestination());
                    tempEdge.setWeight(sortedList.get(j - 1).getWeight());
                    
                    //copy data
                    sortedList.get(j -1).setSource(sortedList.get(j).getSource());
                    sortedList.get(j -1).setDestination(sortedList.get(j).getDestination());
                    sortedList.get(j -1).setWeight(sortedList.get(j).getWeight());
                    
                    //restore data
                    sortedList.get(j).setSource(tempEdge.getSource());
                    sortedList.get(j).setDestination(tempEdge.getDestination());
                    sortedList.get(j).setWeight(tempEdge.getWeight());
                    
                }
            }
        }

        return sortedList;
    }
    
}
