/**
 * Copyright (c) 2016 by Adrian Luna
 *                       Ricardo Gonzales
 * All Rights Reserved
 *
 * Authors: Adrian Luna
 *          Ricardo Gonzales
 *
 * Porpuse: Class that contains the methods to handle the edges
 **/

package classes;

public class Edges
{
    private String source;
    private String destination;
    private int    weight;
    
    public Edges(String src, String dst, int w)
    {
        source = src;
        destination = dst;
        weight = w;
    }
    
    public String getSource()
    {
        return this.source;
    }
    
    public String getDestination()
    {
        return this.destination;
    }
    
    public int getWeight()
    {
        return this.weight;
    }
    
    public void setSource(String src)
    {
        this.source = src;
    }
    
    public void setDestination(String dst)
    {
        this.destination = dst;
    }
    
    public void setWeight(int w)
    {
        this.weight = w;
    }
}
