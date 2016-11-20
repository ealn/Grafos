/**
 * Copyright (c) 2016 by Adrian Luna
 *                       Ricardo Gonzales
 * All Rights Reserved
 *
 * Authors: Adrian Luna
 *          Ricardo Gonzales
 *
 * Porpuse: Class that contains the methods to handle the subset used in 
 *          Kruskal's Algorithm
 **/

package classes;

public class Subset 
{
    private int parent;
    private int rank;
    
    public void setParent(int p)
    {
        this.parent = p;
    }
    
    public void setRank(int r)
    {
        this.rank = r;
    }
    
    public int getParent()
    {
        return this.parent;
    }
    
    public int getRank()
    {
        return this.rank;
    }
    
    // A utility function to find set of an element i
    // (uses path compression technique)
    public static int find(Subset subsets[], int i)
    {
        // find root and make root as parent of i (path compression)
        if (subsets[i].parent != i)
        {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        
        return subsets[i].parent;
    }
 
    // A function that does union of two sets of x and y
    // (uses union by rank)
    public static void Union(Subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
 
        // Attach smaller rank tree under root of high rank tree
        // (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
        {
            subsets[xroot].parent = yroot;
        }
        else if (subsets[xroot].rank > subsets[yroot].rank)
        {
            subsets[yroot].parent = xroot;
        }
        
        // If ranks are same, then make one as root and increment
        // its rank by one
        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    
    public static void initSubsets(Subset[] subsets, int size)
    {
        for (int i = 0; i < size; ++i)
        {
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
    }
    
}
