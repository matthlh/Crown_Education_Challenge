import javax.swing.*;
import java.awt.*;

public class Main {

    static JFrame frame;
    static JPanel myContainer = new JPanel();
    static GraphLayout graphLayoutPane = new GraphLayout();
    static ControlLayout controlLayoutPane = new ControlLayout(graphLayoutPane);


    public static void main(String[] args) {
        myContainer.setLayout(new BoxLayout(myContainer, BoxLayout.Y_AXIS));
        myContainer.add(graphLayoutPane.panel);
        myContainer.add(controlLayoutPane);

        frame = new JFrame("Pandemic Simulator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set dimensions
        frame.setPreferredSize(new Dimension(1500, 1000));
        // add components into our content page
        frame.getContentPane().add(myContainer);
        // layout all the components
        frame.pack();
        // make our frame visible to the user
        frame.setVisible(true);
    }
}
