package model;
import java.util.*;
/**
 * class Sessions 
 */
public class Sessions extends Records
{
    public Sessions()
    {
    }
    public void add(Session session)
    {
        super.add(session);
    }
    public void sellTickets(int id, int gold, int regular)
    {
        Session session = find(id);
        if (session == null)
        {
            System.out.println("No matching session found");
        }
        else
        {
            session.sellTickets(gold, regular);
        }
    }
   
    public double cost()
    {
        double cost = 0;
        for(Record record: records)
        {   
             cost+= ((Session)record).cost(); 
        }
        return cost;
    }
    public Session find(int id)
    {   
        return (Session) super.find(id);    
    }
     public boolean canSell(int id, int gold, int regular)
    {
       boolean result = false;
       Session session = find(id);
       if (session == null)
       {
            System.out.println("No matching session found");
       }
       else
       {
            result = session.canSell(gold, regular);
       }
       return result;
    }
    public void setupSession(Movie movie, Theatre theatre, String name, int time, double gold, double regular)
    {   
        if(theatre.vacant(time))
        {
            Session session = new Session(++id, name, movie, theatre);   
            double cost[] = {gold, regular};
            session.setPrices(cost);
            add(session);
            theatre.setBooked(time);
        }
        else
            System.out.println("Theatre not available for the selected time, session aborted");
    }
    public String name(int time)
    {
        String name = "";
        switch (time)
        {
            case 0: name = "9am"; break; 
            case 1: name = "12noon"; break; 
            case 2: name = "3pm"; break; 
            case 3: name = "6pm"; break; 
            default: name = "unknown";
        }
        return name;
    }
    public double income()
    {
        double income = 0;
        for(Record record : records)
        {   
             income+= ((Session)record).income(); 
        }
        return income;
    }
   
}
