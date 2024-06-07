import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReceivingScreen extends Thread {
    private InputStream oin;
    private JPanel cPanel;
    private BufferedImage image1;

    public ReceivingScreen(InputStream in, JPanel p) {
        oin = in;
        cPanel = p;
        start();
    }

    public void run() {
        try {
            while (true) {
                byte[] bytes = new byte[1024 * 1024];
                int count = 0;
                do {
                    int bytesRead = oin.read(bytes, count, bytes.length - count);
                    if (bytesRead == -1) break;
                    count += bytesRead;
                } while (!(count > 4 && bytes[count - 2] == (byte) -1 && bytes[count - 1] == (byte) -39)); // Check for JPEG end-of-file markers

                image1 = ImageIO.read(new ByteArrayInputStream(bytes, 0, count));
                Image scaledImage = image1.getScaledInstance(cPanel.getWidth(), cPanel.getHeight(), Image.SCALE_FAST);
                Graphics graphics = cPanel.getGraphics();
                graphics.drawImage(scaledImage, 0, 0, cPanel.getWidth(), cPanel.getHeight(), cPanel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
