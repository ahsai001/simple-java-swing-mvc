package model;
/**
 * class Record 
 */
public class Record 
{
    protected int id;
    protected String name;
    public Record(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public int getId()
    {
        return id;
    }
    public boolean matches(int id)
    {
       return (this.id == id);
    }
}
