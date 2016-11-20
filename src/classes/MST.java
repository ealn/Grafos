package classes;

import java.util.ArrayList;

public class MST 
{
    private static ArrayList <Edges>  sortedConnectionList;
    private static ArrayList <Edges>  listMST;
    private static ArrayList <String> visitedNodes;
    private static int       mark = 0;
    
    public static String run(Graph graph)
    {
        String output = null;

        output = kruskalAlgorithm(graph);

        return output;
    }
    
    private static String kruskalAlgorithm(Graph graph)
    {
        String ret = null;
        
        listMST = new ArrayList <Edges>();
        visitedNodes = new ArrayList <String>();
                
        //sort connection table
        sortedConnectionList = graph.getSortedConnections();
        
        //Whole table of connections will be visited
        for (int i = 0; i < sortedConnectionList.size(); i++)
        {
            //validate if the nodes were not visited previously
            if (valideConnection(sortedConnectionList.get(i).getSource(),
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
    
    private static boolean valideConnection(String src, String dst)
    {
        boolean ret = true;
        boolean srcWasVisited = false;
        boolean dstWasVisited = false;
        boolean isCircularRef = false;
        int     indexSrc      = -1;
        int     indexDst      = -1;
        
        for (int i = 0; i < visitedNodes.size(); i++)
        {
            if (src.equals(visitedNodes.get(i)))
            {
                srcWasVisited = true;
                indexSrc = i;
            }
            if (dst.equals(visitedNodes.get(i)))
            {
                dstWasVisited = true;
                indexDst = i;
            }
        }
        
        //if the source and destination are in the list of nodes visited
        if (srcWasVisited && dstWasVisited)
        {
        	//Check if these elements do not make a circular reference
        	isCircularRef = true;
        	
        	
        	if (isCircularRef)
        	{
        		//Cannot be added these elements to the list of MST
        		ret = false;
        	}
        }
        else
        {
            if (!srcWasVisited)
            {
                visitedNodes.add(src); 
            }
            if (!dstWasVisited)
            {
                visitedNodes.add(dst);
            }
        }
            
        return ret;
    }
    
    private static String convertListMSTToString()
    {
        String str = null;
        StringBuffer stringBuf = new StringBuffer();
        
        for (int i = 0; i < listMST.size(); i++)
        {
            stringBuf.append(listMST.get(i).getSource() +
                             " -> " +
                             listMST.get(i).getDestination() +
                             " = " +
                             listMST.get(i).getWeight() +
                             "\n");
        }
        
        str = stringBuf.toString();
        
        return str;
    }
}
