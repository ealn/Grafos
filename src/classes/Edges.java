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
        return source;
    }
    
    public String getDestination()
    {
        return destination;
    }
    
    public int getWeight()
    {
        return weight;
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
