import gui.MyGUI;

/**
 * Testing GUI
 *
 * @author Mio Diaz, Cody Walker
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        MyGUI gui = new MyGUI();
        gui.setSize(400, 300);
        gui.setVisible(true);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MyGUItester tester;
                System.out.println("I started running.");
                tester = new MyGUItester();
                tester.createWindow();
                tester.pack();
            }
        });
    }


}
