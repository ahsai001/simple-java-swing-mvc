package model;

import java.util.*;
/**
 * class Theatres 
 */
public class Theatres extends Records
{
    public Theatres()
    {
    }
    public void add(Theatre theatre)
    {
        super.add(theatre);
    }
    
    public void add(String name, int gold, int regular)
    {   
        System.out.println("Add a Theatre");
        Theatre theatre = new Theatre(++id, name, gold, regular);
        add(theatre);
    }
    public Theatre find(int id)
    {   
        return (Theatre) super.find(id);   
    }
 
}
