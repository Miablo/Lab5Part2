import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
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

    @Override
    public void eventDispatched(AWTEvent event) {
        Object o = event.getSource();
        int ID = event.getID();
        if ( open != -1) { // calls open file if used for first time
            openFile();
            open--;
        }
        if (ID == 500 && t < testFile.size()) { //check for click input on mouse and under list size
            if (o instanceof JTextField) {  // check if click object is text field
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
            }
        }

    }

    /**
     * Here to open file and save integer to testFile list.
     */
    private void openFile() {
        FileReader file;
        try {
            file = new FileReader("E:\\Sweng431\\Lab4Part3\\.idea\\input.txt"); // will need to change file stream to file location on computer.
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

