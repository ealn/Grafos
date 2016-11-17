package classes;

import java.util.ArrayList;

public class Graph 
{
    private static final int INFINITY = 2147483647;
    
    public static String[] nodes = { "Guadalajara", 
                                     "Leon", 
                                     "Aguascalientes", 
                                     "Zacatecas", 
                                     "Durango",
                                     "San Luis Potosi",
                                     "Mazatlan",
                                     "Tepic",
                                     "Mexico",
                                     "Monterrey"};
    
    public static int[][] connections = { /* Gdl      Leon      Ags       Zacatecas Durango   San Luis  Mazatlan  Tepic     Mexico    Monterrey */
                /* Guadalajara        */  { INFINITY, 2,        2,        INFINITY, INFINITY, INFINITY, INFINITY, 3,        5,        INFINITY },
                /* Leon               */  { 2,        INFINITY, 1,        INFINITY, INFINITY, 2,        INFINITY, INFINITY, 4,        INFINITY },
                /* Aguascalientes     */  { 2,        1,        INFINITY, 1,        INFINITY, 2,        INFINITY, INFINITY, INFINITY, INFINITY },
                /* Zacatecas          */  { INFINITY, INFINITY, 1,        INFINITY, 3,        2,        INFINITY, INFINITY, INFINITY, 5        },
                /* Durango            */  { INFINITY, INFINITY, INFINITY, 3,        INFINITY, INFINITY, 2,        INFINITY, INFINITY, 6        },
                /* San Luis Potosi    */  { INFINITY, 2,        2,        2,        INFINITY, INFINITY, INFINITY, INFINITY, 5,        5        },
                /* Mazatlan           */  { INFINITY, INFINITY, INFINITY, INFINITY, 2,        INFINITY, INFINITY, 3,        INFINITY, INFINITY },
                /* Tepic              */  { 3,        INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 3,        INFINITY, INFINITY, INFINITY },
                /* Mexico             */  { 5,        4,        INFINITY, INFINITY, INFINITY, 5,        INFINITY, INFINITY, INFINITY, INFINITY },
                /* Monterrey          */  { INFINITY, INFINITY, INFINITY, 5,        6,        5,        INFINITY, INFINITY, INFINITY, INFINITY }};

    private ArrayList <String> node;
    private ArrayList <Edges> connection;
    
    public Graph()
    {
        //Allocate memory for nodes and connections
        node = new ArrayList<String>();
        connection = new ArrayList<Edges>();
        
        //Insert Nodes
        node.add("Guadalajara");
        node.add("Leon");
        node.add("Aguascalientes");
        node.add("Zacatecas");
        node.add("Durango");
        node.add("San Luis Potosi");
        node.add("Mazatlan");
        node.add("Tepic");
        node.add("Mexico");
        node.add("Monterrey");
        
        //Add connections
        connection.add(new Edges("Guadalajara", "Leon", 2));
        connection.add(new Edges("Guadalajara", "Aguascalientes", 2));
        connection.add(new Edges("Guadalajara", "Tepic", 3));
        connection.add(new Edges("Guadalajara", "Mexico", 5));
        
        connection.add(new Edges("Leon", "Guadalajara", 2));
        connection.add(new Edges("Leon", "Aguascalientes", 1));
        connection.add(new Edges("Leon", "San Luis Potosi", 2));
        connection.add(new Edges("Leon", "Mexico", 4));
        
        connection.add(new Edges("Aguascalientes", "Guadalajara", 2));
        connection.add(new Edges("Aguascalientes", "Leon", 1));
        connection.add(new Edges("Aguascalientes", "Zacatecas", 1));
        connection.add(new Edges("Aguascalientes", "San Luis Potosi", 2));
        
        connection.add(new Edges("Zacatecas", "Aguascalientes", 1));
        connection.add(new Edges("Zacatecas", "Durango", 3));
        connection.add(new Edges("Zacatecas", "San Luis Potosi", 2));
        connection.add(new Edges("Zacatecas", "Monterrey", 5));
        
        connection.add(new Edges("Durango", "Zacatecas", 3));
        connection.add(new Edges("Durango", "Mazatlan", 2));
        connection.add(new Edges("Durango", "Monterrey", 6));
        
        connection.add(new Edges("San Luis Potosi", "Leon", 2));
        connection.add(new Edges("San Luis Potosi", "Aguascalientes", 2));
        connection.add(new Edges("San Luis Potosi", "Zacatecas", 2));
        connection.add(new Edges("San Luis Potosi", "Mexico", 5));
        connection.add(new Edges("San Luis Potosi", "Monterrey", 5));
        
        connection.add(new Edges("Mazatlan", "Durango", 2));
        connection.add(new Edges("Mazatlan", "Tepic", 3));
        
        connection.add(new Edges("Tepic", "Guadalajara", 3));
        connection.add(new Edges("Tepic", "Mazatlan", 3));
        
        connection.add(new Edges("Mexico", "Guadalajara", 5));
        connection.add(new Edges("Mexico", "Leon", 4));
        connection.add(new Edges("Mexico", "San Luis Potosi", 5));
        
        connection.add(new Edges("Monterrey", "Zacatecas", 5));
        connection.add(new Edges("Monterrey", "Durango", 6));
        connection.add(new Edges("Monterrey", "San Luis Potosi", 5));
    }

    public String[] getNodes()
    {
        String[] listNodes = new String[node.size()];
        
        for (int i = 0; i < node.size(); i++)
        {
            listNodes[i] = node.get(i);
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
        for (i = 0; i < connection.size(); i++)
        {
            sortedList.add(new Edges(connection.get(i).getSource(),
                                     connection.get(i).getDestination(),
                                     connection.get(i).getWeight()));
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
