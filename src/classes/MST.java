/**
 * Copyright (c) 2016 by Adrian Luna
 *                       Ricardo Gonzales
 * All Rights Reserved
 *
 * Authors: Adrian Luna
 *          Ricardo Gonzales
 *
 * Porpuse: Class that contains the implementation of Kruskal's Algorithm to calculate
 *          the minimum spanning tree
 **/

package classes;

import java.util.ArrayList;

public class MST 
{
    private static ArrayList <Edges>  sortedConnectionList;
    private static ArrayList <Edges>  listMST;
    private static Subset[] subsets;
    
    public static String run(Graph graph)
    {
        String output = null;

        output = kruskalAlgorithm(graph);

        return output;
    }
    
    private static String kruskalAlgorithm(Graph graph)
    {
        String ret = null;
        int    V = graph.getNumberOfVertices();
        subsets = new Subset[V];
        listMST = new ArrayList <Edges>();
        
        //create subsets
        for(int i=0; i< V; ++i)
        {
            subsets[i] = new Subset();
        }
        
        Subset.initSubsets(subsets, V);
        
        //sort connection table
        sortedConnectionList = graph.getSortedConnections();
        
        //Whole table of connections will be visited
        for (int i = 0; i < sortedConnectionList.size(); i++)
        {
            //validate if the nodes were not visited previously
            if (valideConnection(graph,
                                 sortedConnectionList.get(i).getSource(),
                                 sortedConnectionList.get(i).getDestination()))
            {
                //Insert Nodes to the list
                listMST.add(sortedConnectionList.get(i));
                
                //if the number of connections is equal to the number of nodes - 1
                if (listMST.size() == (graph.getNodes().size() - 1))
                {
                	//break the loop
                	break;
                }
            }
        }
        
        //Convert result to a string
        ret = convertListMSTToString();
        
        return ret;
    }
    
    private static boolean valideConnection(Graph graph, String src, String dst)
    {
        boolean ret = false;
        int x;
        int y;
        
        x = Subset.find(subsets, graph.getIndexFromNodeList(src));
        y = Subset.find(subsets, graph.getIndexFromNodeList(dst));

        // If including this edge does't cause cycle, include it
        // in result and increment the index of result for next edge
        if (x != y)
        {
            ret = true;
            Subset.Union(subsets, x, y);
        }
            
        return ret;
    }
    
    private static String convertListMSTToString()
    {
        String str = null;
        StringBuffer stringBuf = new StringBuffer();
        int    total = 0;
        
        for (int i = 0; i < listMST.size(); i++)
        {
            stringBuf.append(listMST.get(i).getSource() +
                             " -> " +
                             listMST.get(i).getDestination() +
                             " = " +
                             listMST.get(i).getWeight() +
                             "\n");
            
            total += listMST.get(i).getWeight();
        }
        
        stringBuf.append("COSTO TOTAL = " + total);
        str = stringBuf.toString();
        
        return str;
    }
}
