/*
(5 pts)
Complete an animated GUI testing tool for the previous GUI.jar file.
Run the first 10 test cases that were prepared by your team last week
 */
import gui.MyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mio Diaz, Cody Walker
 * @version 1.0
 */
public class MyGUItester extends JFrame {
    public static JButton rec; // The “Record” button stores all the mouse move and press events for activities inside the MyGUI window.
    public static JButton play; // The “Play” button executes the recorded steps based on the same time progression.
    public static JButton stop; // The “Stop” button terminates the recording.

    ArrayList<AWTEvent> alae = new ArrayList<>(); // holds all events recorded
    Robot robot; // robot used to perform keyboard / mouse actions based on events recorded

    /**
     * Constructor
     */
    public MyGUItester() {
        try{
            robot = new Robot();
        }catch(AWTException ex){
            Logger.getLogger(MyGUItester.class.getName()).log(Level.SEVERE, null, ex);
        }
        Toolkit tk = Toolkit.getDefaultToolkit();
        AWTEventListener listener = new MyAWTEventListener(alae);
        tk.addAWTEventListener(listener, AWTEvent.MOUSE_EVENT_MASK+AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    /**
     * Creates animated tester window
     */
    public void createWindow(){
        // set location to top left corner of screen
        JFrame window = new JFrame("My GUI Tester"); // The window includes three buttons: “Record”, “Stop” and “Play”.

        window.setLocation(0,0); // Your tool window is placed at the upper left corner of the screen.
        window.setResizable(false); // dont allow resize of test window
        window.setLayout(null);
        window.setSize(280, 60); // set size
        window.setLocation(0,0);

        // button record
        rec = new JButton("Record");
        rec.setBounds(0, 0, 100, 30);
        // button play
        play = new JButton("Play");
        play.setBounds(90, 0, 100, 30);
        // button stop
        stop = new JButton("Stop");
        stop.setBounds(180, 0, 100, 30);

        // Add buttons to GUI Window
        window.add(rec);
        window.add(play);
        window.add(stop);

        rec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { }});

        // Play events when play is clicked
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playActionPerformed(e);
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { }});

        window.setVisible(true);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void playActionPerformed(ActionEvent evt) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // Run all events recorded once play is clicked
                for (AWTEvent event : alae) {
                    MouseEvent me = (MouseEvent) event;
                    if (event.getID() == MouseEvent.MOUSE_CLICKED) {
                        int click = me.getButton();
                        Point p = me.getLocationOnScreen();
                        robot.mouseMove(p.x, p.y);
                        MyAWTEventListener.recordClick((int)p.getY());
                        robot.delay(800);
                    }
                }
                // Remove all events once played
                alae.removeAll(alae);
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    /**
     * Creates GUI windows for program and tester window
     */
    public void initGUI(){
        // Creates GUI Window for arithmetic
        MyGUI gui = new MyGUI();
        gui.setSize(400, 300);
        gui.setVisible(true);
        // Creates window for gui tester in upper left hand corner
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MyGUItester tester;
                tester = new MyGUItester();
                tester.createWindow();
            }
        });
    }

}
