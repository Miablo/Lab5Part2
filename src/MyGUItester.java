/*
(5 pts)
Complete an animated GUI testing tool for the previous GUI.jar file.
Run the first 10 test cases that were prepared by your team last week
 */
import javax.swing.*;
/**
 *
 * @author Mio Diaz, Cody
 * @version 1.0
 */
public class MyGUItester extends JFrame {
    public static JButton rec; // The “Record” button stores all the mouse move and press events for activities inside the MyGUI window.
    public static JButton play; // The “Play” button executes the recorded steps based on the same time progression.
    public static JButton stop; // The “Stop” button terminates the recording.

    /**
     *
     */
    public void createWindow(){
        // set location to top left corner of screen
        JFrame window = new JFrame("My GUI Tester"); // The window includes three buttons: “Record”, “Stop” and “Play”.

        // button record
        rec = new JButton("Record");
        rec.setBounds(100, 50, 200, 40);
        // button play
        play = new JButton("Play");
        play.setBounds(100, 150, 200, 40);
        // button stop
        stop = new JButton("Stop");
        stop.setBounds(100, 100, 200, 40);

        window.add(rec);
        window.add(play);
        window.add(stop);

        window.setLocation(0,0); // Your tool window is placed at the upper left corner of the screen.
        window.setResizable(false);
        window.setLayout(null);

        window.setSize(400, 300);
        window.setVisible(true);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
