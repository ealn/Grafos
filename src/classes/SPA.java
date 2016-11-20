/**
 * Copyright (c) 2016 by Adrian Luna
 *                       Ricardo Gonzales
 * All Rights Reserved
 *
 * Authors: Adrian Luna
 *          Ricardo Gonzales
 *
 * Porpuse: Class that contains the implementation of Dijkstra's Algorithm to calculate
 *          the minimum spanning tree
 **/

package classes;

public class SPA 
{
    public static String run(Graph graph, int souce, int destination)
    {
        String output = null;
        
        output = dijkstra(graph.getMatrix(), 
                          graph.getNumberOfVertices(), 
                          graph.getNodesAsStrings(), 
                          souce, 
                          destination);
        
        return output;
    }
    
  //Función Dijisktra con Matriz de pesos
    private static String dijkstra(int graph[][], int V, String[] ciudades, int src, int dst)
    {
        int dist[] = new int[V];  // Arreglo para las rutas más cortas
        String recorrido = null;                        
        Boolean sptSet[] = new Boolean[V];
        int predecesor[] = new int [V];
 
        // Inicializar todas las distacias a el entero más grande (infinito) y la bandera de recorrido en falso
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // La distancia desde el origen siempre es cero
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            //Tomamos el de distancia minima que aun no ha sido procesado
            int u = minDistance(V, dist, sptSet);
 
            // Marcamos el elemento como procesado
            sptSet[u] = true;
 
            for (int v = 0; v < V; v++)
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                {
                    dist[v] = dist[u] + graph[u][v];
                    predecesor[v] = u;
                }
        }
        
        printSolution(ciudades, V, dist, V, predecesor, src);
        recorrido = printPath (graph, ciudades, V, dst,src,predecesor);
        return recorrido;
    }
    
    private static int minDistance(int V, int dist[], Boolean sptSet[])
    {
        // Inicializamos el valor minimo
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
    
    // PrintSolution es una función que imprime las distancias más cortas desde el origen con su respectivo predecesor
    private static void printSolution(String[] ciudades, int V, int dist[], int n, int predecesor[], int origen)
    {
        System.out.println("Ciudad   Distancia desde  Predecesor");
        for (int i = 0; i < V; i++){
            if (i != origen){
            System.out.print(ciudades[i]+" \t\t "+dist[i]);
            System.out.print(" \t\t "+ ciudades[predecesor[i]]);
            System.out.println();
            }
        }
    }
    
    private static String printPath (int graph[][], String[] ciudades, int V, int destino, int origen, int predecesor []){
        int siguiente = predecesor[destino], saltos = 0;
        int path [] = new int [V];
        StringBuffer sBuffer = new StringBuffer();
        StringBuffer costStr = new StringBuffer();
        int          cost = 0;
        int          index1 = 0;
        int          index2 = 0;
        String       output = null;
        int          totalCost = 0;
        
        index1 = origen;
        sBuffer.append(ciudades[origen]+ " -> ");
        
        while (siguiente != origen ){
            path[saltos] = siguiente;
            saltos++;
            siguiente  = predecesor[siguiente];
           }
        
        while (saltos > 0){
            saltos--;
            index2 = path[saltos];
            cost = graph[index1][index2];
            totalCost += cost;
            sBuffer.append(ciudades[path[saltos]] + " -> ");
            costStr.append(cost + " + ");
            index1 = index2;
            
        }
        
        index2 = destino;
        cost = graph[index1][index2];
        totalCost += cost;
        sBuffer.append(ciudades[destino]);
        costStr.append(cost + " = " + totalCost);
        
        sBuffer.append("\nCOSTO TOTAL = " + costStr.toString());
        
        output = sBuffer.toString();
        return output;
     }
}
