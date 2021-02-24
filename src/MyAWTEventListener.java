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
    int open = 0;
    JTextField  output;
    int t = 0;
    ArrayList<Integer> testFile = new ArrayList<>();
    boolean recording = false;
    ArrayList alae;

    public MyAWTEventListener(ArrayList<AWTEvent> alae) {
        this.alae = alae;
    }

    @Override
    public void eventDispatched(AWTEvent event) {
       // if ( open != -1) { // calls open file if used for first time
            //openFile();
          //  open--;
      //  }
        //if (event.getID() == MouseEvent.MOUSE_CLICKED && t < testFile.size()) { //check for click input on mouse and under list size
        if (event.getID() == MouseEvent.MOUSE_CLICKED) {
            Object o = event.getSource();

            if(o instanceof JButton){
                JButton button = (JButton)o;
                if(button.getText().equals("Record")){
                    recording = true;
                    System.out.println("True");
                }else if(button.getText().equals("Stop")){
                    recording = false;
                }
            }
            if(recording){
                System.out.println("Added Event");
                alae.add(event);
            }

          /*  if (o instanceof JTextField) {  // check if click object is text field
                JTextField jtf = (JTextField) o; // give jtf the field of clicked box
                jtf.setText(String.valueOf(testFile.get(t)));// prints from list
                if(jtf.getY() < 175) { // not last box or output box check
                    t++;
                } else if (jtf.getY() >= 175) {// last box or output box check
                    output = jtf;
                }
            } else if (o instanceof JButton) {
                if (Integer.parseInt(output.getText()) == testFile.get(t)) { // check if output is same as expected value and prints pass or failed accordingly
                    System.out.println(testFile.get(t-2) + " + " + testFile.get(t-1) + " = " + testFile.get(t) + " Pass");
                } else {
                    System.out.println(testFile.get(t-2) + " + " + testFile.get(t-1) + " = " + testFile.get(t) +" Failed got " + output.getText() );
                }
                t++;
            }*/

        }

    }


}

