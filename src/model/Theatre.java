package model;
import java.util.*;
/**
 * class Theatre
 */
public class Theatre extends Record
{   
    private int  gold, regular;
    private boolean vacant[] = {true, true, true, true};
    private final int MIN = 0;
    private final int MAX = 4;
    public Theatre(int id, String name, int gold, int regular)
    {   
        super(id, name);
        this.gold = gold;
        this.regular = regular;
    }     
    public int goldSeats()
    {   
        return gold;   
    }
    public int regularSeats()
    {   
        return regular;   
    }
    public boolean vacant(int time)
    {
        if (time >= MIN && time < MAX)
            return vacant[time];
        else 
            return false;
    }
    public void setBooked(int time)
    {
       vacant[time] = false;
    }
}
