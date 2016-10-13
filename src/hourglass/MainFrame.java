package hourglass;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    JPanel contentPane;
    JButton hourglassButton = new JButton();
    JButton normalButton = new JButton();

    /**
     * Construct the frame
     */
    public MainFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Component initialization
     */
    private void jbInit() throws Exception {
        hourglassButton.setText("Hourglass");
        hourglassButton.setBounds(new Rectangle(84, 120, 104, 27));
        hourglassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hourglassButton_actionPerformed(e);
            }
        });
        //setIconImage(Toolkit.getDefaultToolkit().createImage(Frame1.class.getResource("[Your Icon]")));
        contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(null);
        this.setSize(new Dimension(400, 300));
        this.setTitle("Frame Title");
        normalButton.setText("Normal");
        normalButton.setBounds(new Rectangle(193, 120, 81, 27));
        normalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                normalButton_actionPerformed(e);
            }
        });
        contentPane.add(normalButton, null);
        contentPane.add(hourglassButton, null);
    }

    /**
     * Overridden so we can exit when window is closed
     */
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }

    void hourglassButton_actionPerformed(ActionEvent e) {
        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        setCursor(hourglassCursor);
    }

    void normalButton_actionPerformed(ActionEvent e) {
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        setCursor(normalCursor);
    }
}