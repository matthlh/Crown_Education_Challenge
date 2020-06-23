import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class HTTPListener implements ActionListener {

    URI uri;
    public HTTPListener(URI uri) {
        this.uri = uri;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Checks if the computer has a browser
        if(Desktop.isDesktopSupported()) {
            try {
                // Tries to redirect user to website
                Desktop.getDesktop().browse(uri);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
