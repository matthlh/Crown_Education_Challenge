import javax.swing.*;
import java.awt.*;

public class Main {

    static JFrame frame;
    static JPanel myContainer;
    static GraphLayout graphLayout;
    static ControlLayout controlLayoutPane;
    static JScrollPane scrollPane;


    public static void main(String[] args) {
        myContainer = new JPanel();
        graphLayout = new GraphLayout();
        controlLayoutPane = new ControlLayout(graphLayout);
        scrollPane = new JScrollPane(controlLayoutPane);

        graphLayout.panel.setPreferredSize(new Dimension(1500, 1000));
        scrollPane.setPreferredSize(new Dimension(1500, 400));

        myContainer.setLayout(new BoxLayout(myContainer, BoxLayout.Y_AXIS));
        myContainer.add(graphLayout.panel);
        myContainer.add(scrollPane);

        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        frame = new JFrame("Pandemic Simulator");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add components into our content page
        frame.getContentPane().add(myContainer);
        // set dimensions
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // layout all the components
        frame.pack();
        // make our frame visible to the user
        frame.setVisible(true);
    }
}
