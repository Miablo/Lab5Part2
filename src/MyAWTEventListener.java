import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Event Listener extends AWTEventListener used
 * to test GUI with input.txt after mouseclick is detected
 *
 * @author Cody Walker, Mio Diaz
 * @version 1.0
 */
public class MyAWTEventListener implements AWTEventListener {
    JTextField  output;
    boolean recording = false;
    ArrayList alae;

    public MyAWTEventListener(ArrayList<AWTEvent> alae) {
        this.alae = alae;
    }

    @Override
    public void eventDispatched(AWTEvent event) {

        if (event.getID() == MouseEvent.MOUSE_CLICKED) {
            Object o = event.getSource();

            if(o instanceof JButton){
                JButton button = (JButton)o;
                if(button.getText().equals("Record")){
                    recording = true;
                    System.out.println("Recording is true");
                    alae.add(event);
                } else if (button.getText().equals("Stop")){
                    recording = false;
                    System.out.println("I stopped recording.");
                }
            }
            if(recording){
                System.out.println("Added Event");
                alae.add(event);
            }


        }

    }



}

