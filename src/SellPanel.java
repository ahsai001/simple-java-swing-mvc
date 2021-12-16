import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SellPanel extends JPanel
{   
    private TopPanel topPanel;
    private MidPanel midPanel;
    private Cinema cinema;
    private Session session = null;
    public SellPanel(Cinema cinema)
    {
        topPanel = new TopPanel(cinema.sessions());
        midPanel = new MidPanel();
        setup();
        build();
    }
    
    public void setup()
    {
        
    }
    
    public void build()
    {
        Box box = Box.createVerticalBox();
        add(box);
        box.add(topPanel);
        box.add(midPanel);

        midPanel.setVisible(false);

    }
    
    private class TopPanel extends JPanel
    {
        private JLabel sessionIdLabel = new JLabel("Session Id:");
        private JTextField sessionIdTF = new JTextField(5);
        private JButton sessionIdCheck = new JButton("Check");
        private JLabel dummy = new JLabel("");
        private JLabel sessionInfo = new JLabel(""); 
        private Sessions sessions;
        public TopPanel(Sessions sessions)
        {
            this.sessions = sessions;
            setup();
            build();
        }
        
        public void setup()
        {
            setBorder(BorderFactory.createTitledBorder("Find Session"));
            sessionIdCheck.addActionListener(new IdCheckListener());
        }
        
        public void build()
        {
            setLayout(new GridLayout(3,2));
            add(sessionIdLabel);
            add(sessionIdTF);
            add(dummy);
            add(sessionIdCheck);
            add(sessionInfo);
        }
        
        
        private class IdCheckListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                int id = Integer.parseInt(sessionIdTF.getText());
                session = sessions.find(id);
                if(session == null )
                {
                    sessionInfo.setText("No matching session found");
                    midPanel.setVisible(false);
                }
                else
                {
                    sessionInfo.setText(session.movie().getName());
                    midPanel.setVisible(true);
                }
                
            }
        }
        
    }
    
    private class MidPanel extends JPanel
    {
        private JLabel goldSeatSalesLabel = new JLabel("Gold Class Seats sold:");
        private JTextField goldSeatSalesTF = new JTextField(5);
        private JLabel regSeatSalesLabel = new JLabel("Regular Class Seats sold:");
        private JTextField regSeatSalesTF = new JTextField(5);
        private JButton sellButton = new JButton("Sell Tickets");
        
        public MidPanel()
        {
            setup();
            build();
        }
        
        private void setup()
        {
            setBorder(BorderFactory.createTitledBorder("Seat Sales"));
            sellButton.addActionListener(new SellButtonListener());
        }
        
        private void build()
        {
            add(goldSeatSalesLabel);
            add(goldSeatSalesTF);
            add(regSeatSalesLabel);
            add(regSeatSalesTF);
            add(sellButton);
        }
        
        private class SellButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                int goldSeat = Integer.parseInt(goldSeatSalesTF.getText()); 
                int regularSeat = Integer.parseInt(regSeatSalesTF.getText());
                session.sellTickets(goldSeat,regularSeat);
            }
        }
    }
    
    private class BottomPanel extends JPanel
    {
        
        public BottomPanel()
        {
            
        }
    }
}