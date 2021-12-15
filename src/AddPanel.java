import java.awt.*;
import javax.swing.*;
import model.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddPanel extends JPanel
{
    private MoviePanel moviePanel;
    private TheatrePanel theatrePanel;
    private SessionPanel sessionPanel;
    private Cinema cinema;
    public AddPanel(Cinema cinema)
    {  
        this.cinema = cinema;
        moviePanel = new MoviePanel(cinema.movies());
        theatrePanel = new TheatrePanel(cinema.theatres());
        sessionPanel = new SessionPanel(cinema.sessions(),cinema.movies(),cinema.theatres());
        setup();
        build();
    }

    public void setup()
    {  
        cinema.movies().attach(moviePanel);
        cinema.theatres().attach(theatrePanel);
        cinema.sessions().attach(sessionPanel);  

    }   

    public void build()
    {  
        Box box = Box.createVerticalBox();
        add(box);
        box.add(moviePanel);
        box.add(theatrePanel);
        box.add(sessionPanel);
    }

    private class MoviePanel extends JPanel implements MyObserver
    {
        private JLabel movie = new JLabel("movie:");
        private JTextField movieName = new JTextField(10);
        private JLabel cost = new JLabel("cost:");
        private JTextField movieCost = new JTextField(5);
        private JLabel dummy = new JLabel("");
        private JButton button1 = new JButton("add movie");
        private JLabel movieInfo = new JLabel("");
        private Movies movies;
        

        public MoviePanel(Movies movies)
        {
            setup();
            build();
            this.movies = movies;
        }

        private void setup()
        {
            setBorder(BorderFactory.createTitledBorder("Movie"));
            setLayout(new GridLayout(4,3));
            button1.addActionListener(new SetListener());
        }

        private void build()
        {
           add(movie);
           add(movieName);
           add(cost);
           add(movieCost);
           add(dummy);
           add(button1);
           add(movieInfo);
        }

        public void update()
        {
            Movie lastMovie = movies.find(movies.size());
            movieInfo.setText(lastMovie.getId() + " " + lastMovie.getName() + " " + 
                lastMovie.cost());
        }

        private class SetListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                String name = movieName.getText();
                double cost = Double.parseDouble(movieCost.getText());
                movies.add(name,cost);
            }
        }

    }
    private class TheatrePanel extends JPanel implements MyObserver
    {
        private JLabel theatre = new JLabel("theatre:"); 
        private JTextField theatreName = new JTextField(10);
        private JLabel goldSeats = new JLabel("number of gold seats:");
        private JTextField numofGSeats = new JTextField(5);
        private JLabel regularSeats = new JLabel("number of regular seats:");
        private JTextField numofRSeats = new JTextField(5);
        private JLabel dummy = new JLabel("");
        private JButton button1 = new JButton("add theatre");
        private JLabel theatreInfo = new JLabel("");
        private Theatres theatres;

        public TheatrePanel(Theatres theatres)
        {
            this.theatres = theatres;
            setup();
            build();
        }

        private void setup()
        {
            setBorder(BorderFactory.createTitledBorder("Theatre"));
            setLayout(new GridLayout(5,4));
            button1.addActionListener(new SetListener());  
            
        }

        private void build()
        {
            add(theatre);
            add(theatreName);
            add(goldSeats);
            add(numofGSeats);
            add(regularSeats);
            add(numofRSeats);
            add(dummy);
            add(button1);
            add(theatreInfo);
        }

        public void update()
        {
            Theatre lastTheatre = theatres.find(theatres.size());
            theatreInfo.setText(lastTheatre.getName() + " " + lastTheatre.getId() + " " + lastTheatre.goldSeats() + " " + lastTheatre.regularSeats());
        }

        private class SetListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                String name = theatreName.getText();
                int goldSeats = Integer.parseInt(numofGSeats.getText());
                int regularSeats = Integer.parseInt(numofRSeats.getText());
                theatres.add(name,goldSeats,regularSeats);
            }
        }
    }
    private class SessionPanel extends JPanel implements MyObserver
    {
        private JLabel session = new JLabel("session:");
        private JTextField sessionName = new JTextField(10);
        private JLabel movieId = new JLabel("Movie Id:");
        private JTextField inputMovId = new JTextField(10);
        private JLabel theatreId = new JLabel("Theatre Id:");
        private JTextField inputTheId = new JTextField(10);
        private JLabel timeOption = new JLabel("Select time");
        private String options[] = {"9 am", "12 am","3 pm" , "6 pm"};
        private JComboBox time1 = new JComboBox(options);
        private JLabel gseatsPrices = new JLabel("Prices for Gold Class Seats:");
        private JTextField goldseatsPrices = new JTextField(5);
        private JLabel rseatsPrices = new JLabel("Prices for Regular Class Seats:");
        private JTextField regseatsPrices = new JTextField(5);
        private JLabel dummy = new JLabel("");
        private JButton button1 = new JButton("add session");
        private JLabel sessionInfo = new JLabel("");
        private Sessions sessions;
        private Movies movies;
        private Theatres theatres;

        public SessionPanel(Sessions sessions,Movies movies,Theatres theatres)
        {
            this.sessions = sessions;
            this.movies = movies;
            this.theatres = theatres;
            setup();
            build();
        }

        private void setup()
        {
            setBorder(BorderFactory.createTitledBorder("Session"));
            setLayout(new GridLayout(8,7));
            button1.addActionListener(new SetListener());             
        }

        private void build()
        {
            add(session);
            add(sessionName);
            add(movieId);
            add(inputMovId);
            add(theatreId);
            add(inputTheId);
            add(timeOption);
            add(time1);
            add(gseatsPrices);
            add(goldseatsPrices);
            add(rseatsPrices);
            add(regseatsPrices);
            add(dummy);
            add(button1);
            add(sessionInfo);
        }

        public void update()
        {
            sessionInfo.setText("success");            
        }

        private class SetListener implements ActionListener
        {
            public void actionPerformed(ActionEvent action){
                String name = sessionName.getText();
                int movieId = Integer.parseInt(inputMovId.getText());
                Movie movie = movies.find(movieId);
                if(movie == null){
                    sessionInfo.setText("No matching movie");
                    return;
                }
                int theatreId = Integer.parseInt(inputTheId.getText());
                Theatre theatre = theatres.find(theatreId);
                if(theatre == null){
                    sessionInfo.setText("No matching theatre");
                    return;
                }

                int time = time1.getSelectedIndex();
                if(!theatre.vacant(time)){
                    sessionInfo.setText("Theatre already booked");
                    return;
                }
                double goldPrice = Double.parseDouble(goldseatsPrices.getText());
                double regPrice = Double.parseDouble(regseatsPrices.getText());
                sessions.setupSession(movie, theatre, name, time, goldPrice, regPrice);
            }
        }
    }
}