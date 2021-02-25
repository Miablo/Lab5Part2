import gui.MyGUI;

/**
 * Testing GUI
 *
 * @author Mio Diaz, Cody Walker
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
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
