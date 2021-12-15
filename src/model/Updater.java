package model;

import java.io.*;
import java.util.*;

public class Updater
{   private transient LinkedList<MyObserver> views = new LinkedList<MyObserver>();

    public void attach(MyObserver o)
    {   
        views.add(o);   
    }
    
    public void detach(MyObserver o)
    {   
        views.remove(o);    
    }
                
    public void updateViews()
    {   if(views!= null)
        {
            for(MyObserver view: views)
                view.update(); 
        }
    }
}
