import model.*;
import java.awt.*;
import javax.swing.*;

public class StoreWindow extends JFrame
{
    public StoreWindow()
    {   setup();
        build();
        setVisible(true);   
    }
    private void setup()
    {   setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
    } 
    private void build()
    {   
        add(new Tabber()); 
    }
    public class Tabber extends JTabbedPane
    {
        public Tabber()
        {   
            Cinema cinema = new Cinema();
            add("Add", new AddPanel(cinema));
            add("Sell", new SellPanel(cinema));   
            add("Boss", new ShowPanel(cinema));
        }
    }
}
