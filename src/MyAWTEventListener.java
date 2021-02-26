import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
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
    static JTextField  output;
    static JTextField input1;
    static JTextField input2;
    boolean recording = false;
    ArrayList alae;
    static ArrayList<Integer> testFile = new ArrayList<Integer>();
    int open = 0;
    int t = 0;
    static int i = 0;

    public MyAWTEventListener(ArrayList<AWTEvent> alae) {
        this.alae = alae;
    }


    @Override
    public void eventDispatched(AWTEvent event) {
        if(open != -1){
            openFile();
            open--;
        }
        if (event.getID() == MouseEvent.MOUSE_CLICKED) {
            Object o = event.getSource();
            if(o instanceof JButton){
                JButton button = (JButton)o;
                if(button.getText().equals("Record")){
                    recording = true;

                } else if (button.getText().equals("Stop")){
                    recording = false;
                }else if (button.getText().equals("OK")){
                    if (Integer.parseInt(output.getText()) == testFile.get(t)) { // check if output is same as expected value and prints pass or failed accordingly
                        System.out.println(testFile.get(t-2) + " + " + testFile.get(t-1) + " = " + testFile.get(t) + " Pass");
                    } else {
                        System.out.println(testFile.get(t-2) + " + " + testFile.get(t-1) + " = " + testFile.get(t) +" Failed got " + output.getText() );
                    }
                    t++;
                }
            }else if (o instanceof JTextField){
                if(t < testFile.size()){
                    JTextField jtf = (JTextField) o; // give jtf the field of clicked box
                    jtf.setText(String.valueOf(testFile.get(t)));// prints from list
                    if(jtf.getY() < 175) { // not last box or output box check
                        t++;
                    }
                    if (jtf.getY() >= 30 && jtf.getY() < 107 ) {
                        input1 = jtf;
                    }else if (jtf.getY() >= 107 && jtf.getY() < 175 ){
                        input2 = jtf;
                    }else if (jtf.getY() >= 175) {// last box or output box check
                        output = jtf;
                    }
                }
            }

            if(recording){
                alae.add(event);
            }
        }

    }

    /**
     * used to add text with clicks from robot in MyGUItester
     * @param clicky pulls Y LOCATION OF ROBOTS MOVED
     */
    public static void recordClick(int clicky){
        if(clicky >= 30 && clicky <94){
            input1.setText(String.valueOf(testFile.get(i)));
            i++;
        }else if (clicky >= 107 && clicky < 171){
            input2.setText(String.valueOf(testFile.get(i)));
            i++;
        }else if (clicky >= 175 && clicky < 239){
            output.setText(String.valueOf(testFile.get(i)));
            i++;
        }
    }


    /**
     * Here to open file and save integer to testFile list.
     */
    private void openFile() {
        FileReader file;
        try {
            file = new FileReader("E:\\Sweng431\\Lab5Part2\\.idea\\input.txt"); // will need to change file stream to file location on computer.
            int i;
            StringBuilder temp = new StringBuilder(); //sets empty string
            while((i = file.read()) != -1) { //reads its char
                if(Character.isDigit((char) i)) { //check if is number
                    temp.append((char) i); // adds temp
                }else if (Character.isWhitespace((char)i) && !temp.toString().equals("")){ //check if next is whitespace and temp is not empty
                    testFile.add(Integer.valueOf(temp.toString())); //adds temp to arraylist
                    temp = new StringBuilder(); // empty temp out
                }
            }
            if(!temp.toString().equals("")){ // verify last integer is add to array
                testFile.add(Integer.valueOf(temp.toString()));
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}

