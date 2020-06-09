import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationButtonListener implements ActionListener {
    JFrame frame;
    public InformationButtonListener() {
        JPanel panel = new JPanel();

        panel.add(new JLabel("Hover over Text on the left for specific information"));

        frame = new JFrame("Information");

        // set dimensions
        frame.setPreferredSize(new Dimension(2000, 1000));
        // add components into our content page
        frame.getContentPane().add(panel);
        // layout all the components
        frame.pack();
        frame.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(true);
    }
}
