package model;



import java.util.*;
/**
 * class Movie
 */
public class Movie extends Record
{   
    private String name;
    private int id;
    private double cost;
    
    public Movie(int id, String name, double cost)
    {
        super(id, name);
        this.name = name;
        this.cost = cost;  
        this.id = id;
    }
    public double cost()
    {
        return cost;
    }

}
