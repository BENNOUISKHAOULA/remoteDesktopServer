import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetPassword extends JFrame implements ActionListener {

    static String port = "4907";
    JButton submit;
    JPanel panel;
    JTextField text1;
    String value1;
    JLabel label1;

    public SetPassword() {
        label1 = new JLabel();
        label1.setText("Set Password");
        text1 = new JTextField(15);
        submit = new JButton("Submit");
        panel = new JPanel(new GridLayout(2, 1));
        panel.add(label1);
        panel.add(text1);
        panel.add(submit);
        add(panel, BorderLayout.CENTER);
        submit.addActionListener(this);
        setTitle("Setting password for client");
    }

    public void actionPerformed(ActionEvent e) {
        value1 = text1.getText();
        dispose();
        new InitConnection(Integer.parseInt(port), value1);
    }

    public String getValue1() {
        return value1;
    }

    public static void main(String[] args) {
        SetPassword frame1 = new SetPassword();
        frame1.setSize(300, 80);
        frame1.setLocation(500, 300);
        frame1.setVisible(true);
    }
}
