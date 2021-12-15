import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import model.*;
/**
 * This class can be used as a parent class for your panels
 * - helps reduce duplication of code
 * BUT only use it if you UNDERSTAND the difference between 
 * Inheritance and Inner classes - this class can cause implications.
 * 
 * This class will NOT be given in the Project C skeleton
 * You will need to reproduce it if you want to use it.
 */
public class CustomPanel extends JPanel implements MyObserver
{
    protected JLabel confirm = new JLabel("\t\t\t");
    public CustomPanel()
    {
       setVisible(true);
       setBorder(BorderFactory.createLineBorder(Color.magenta));
    }
    protected void addTitle(String title)
    {
        JLabel label = new JLabel(title);
        label.setForeground(Color.blue);
        add(label); 
    }
    protected void addPair(String label, JComponent field)
    { 
        add(new JLabel(label));
        add(field);    
    } 
    protected void addButton(String label, ActionListener listener)    
    {   
        JButton button = new JButton(label);
        button.addActionListener(listener);
        add(button);    
    } 
    protected void sayConfirm(String msg)
    {
        confirm.setForeground(Color.blue);
        confirm.setText(msg);
    }
    protected void sayError(String msg)
    {
        confirm.setForeground(Color.red);
        confirm.setText(msg);
    }
    protected void addScroll(JComponent pane)
    {
        JScrollPane scroll = new JScrollPane(pane);
        scroll.setPreferredSize(new Dimension(450, 80));
        add(scroll);
    } 
    public void update()
    {
    }
    
}
