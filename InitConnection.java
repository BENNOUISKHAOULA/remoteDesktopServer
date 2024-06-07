import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InitConnection {
    ServerSocket socket = null;
    DataInputStream password = null;
    DataOutputStream Verify = null;
    String width = "";
    String height = "";

    public InitConnection(int port, String value1) {
        Robot robot = null;
        Rectangle rectangle = null;
        try {
            System.out.println("waiting for client to get connected");
            socket = new ServerSocket(port);
            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            width = "" + dim.getWidth();
            height = "" + dim.getHeight();
            rectangle = new Rectangle(dim);
            robot = new Robot(gDev);

            drawGUI();

            while (true) {
                Socket sc = socket.accept();
                password = new DataInputStream(sc.getInputStream());
                Verify = new DataOutputStream(sc.getOutputStream());
                String psword = password.readUTF();
                if (psword.equals(value1)) {
                    Verify.writeUTF("valid");
                    Verify.writeUTF(width);
                    Verify.writeUTF(height);
                    new SendScreen(sc, robot, rectangle);
                    new RecieveEvents(sc, robot);
                } else {
                    Verify.writeUTF("Invalid");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawGUI() {
    }
}
