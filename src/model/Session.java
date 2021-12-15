package model;
import java.util.*;
/**
 * class Session 
 */
public class Session extends Record
{
    private Movie movie;
    private Theatre theatre;
    private LinkedList<Seat> seats = new LinkedList<Seat>();
    private double profit = 0.0;
    public Session(int id, String name, Movie movie, Theatre theatre)
    {
        super(id, name);
        this.movie = movie; 
        this.theatre = theatre;
        seats.add(new Seat("Gold Class", theatre.goldSeats()));
        seats.add(new Seat("Regular", theatre.regularSeats()));   
    }
    public LinkedList<Seat> seats()
    {   
        return seats;   
    }
    public int groups()
    {   
        return seats.size();   
    }
    public Movie movie()
    {
        return movie;
    }
    public Theatre theatre()
    {
        return theatre;
    }
    public double cost()
    {
        return movie.cost();
    }
    public double profit()
    {
        double income = income();
        profit = income - cost();
        return profit;
    }
     public void sellTickets(int gold, int regular)
    {   
        int sold[] = {gold, regular};
        sellSeats(sold);
    }
    public boolean canSell(int gold, int regular)
    {
        int sell[] = {gold, regular};
        int i = 0;
       for(Seat seat : seats)
        {
            if (seat.left() < sell[i] )
                return false;
            i++;
        }
        return true;
    }
    public double income()
    {   
        double income = 0;
        for(Seat seat : seats)
            income += seat.income();
        return income;  
    }
    public void setPrices(double[] prices)
    {
       int i = 0;
       for(Seat seat : seats)
        {
            seat.setPrice(prices[i++]);
        }
    }
    public void sellSeats(int[] sold)
    {
        int i = 0;
        for(Seat seat : seats)
        {
            seat.addSold(sold[i++]);
        }
    }
    public void show()
    {   
        System.out.println(toString()); 
    }                
    public String toString()
    {   String s = "Session: " + id;
        s += " cost = $" + cost();
        s += " income = $" + income();
        s += " profit = $" + profit();
        return s;   
    }

}