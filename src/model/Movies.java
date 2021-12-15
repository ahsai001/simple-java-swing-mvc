package model;

import java.util.*;
/**
 * class Movies 
 */
public class Movies extends Records
{
    public Movies()
    {
    }
    public void add(Movie movie)
    {
        super.add(movie);
    }
    public void add(String name, double cost)
    { 
        System.out.println("Setup a Movie");
        Movie movie = new Movie(++id, name, cost); 
        add(movie);

    }
    public Movie find(int id)
    {   
        return (Movie) super.find(id);
    }
}
