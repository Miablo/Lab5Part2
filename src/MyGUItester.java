/*
(5 pts)
Complete an animated GUI testing tool for the previous GUI.jar file.
Run the first 10 test cases that were prepared by your team last week
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mio Diaz, Cody
 * @version 1.0
 */
public class MyGUItester extends JFrame {
    public static JButton rec; // The “Record” button stores all the mouse move and press events for activities inside the MyGUI window.
    public static JButton play; // The “Play” button executes the recorded steps based on the same time progression.
    public static JButton stop; // The “Stop” button terminates the recording.

    ArrayList<AWTEvent> alae = new ArrayList<>();
    Robot robot;

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
        window.setResizable(false);
        window.setLayout(null);
        window.setSize(280, 60);

        // button record
        rec = new JButton("Record");
        rec.setBounds(0, 0, 100, 30);
        // button play
        play = new JButton("Play");
        play.setBounds(90, 0, 100, 30);
        // button stop
        stop = new JButton("Stop");
        stop.setBounds(180, 0, 100, 30);

        window.add(rec);
        window.add(play);
        window.add(stop);

        window.setVisible(true);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void playActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (AWTEvent event : alae) {
                    Object o = event.getSource();
                    MouseEvent me = (MouseEvent) event;
                    KeyEvent ke = (KeyEvent) o;

                    robot.setAutoWaitForIdle(true);
                    System.out.println("This is in run");

                    System.out.println(event);

                }
                alae.removeAll(alae);
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

}
