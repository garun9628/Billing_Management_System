package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
        Splash() {
            ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/splash/splash.jpg"));
            Image splashIcon = imgIcon.getImage().getScaledInstance(768, 614, Image.SCALE_DEFAULT);
            ImageIcon splashImgIcon = new ImageIcon(splashIcon);
            JLabel imgLabel = new JLabel(splashImgIcon);
            add(imgLabel);

            setSize(768, 614);
            setLocation(850, 400);
            setVisible(true);

            try {
                Thread.sleep(3000);
                setVisible(false);

                new Login();
            } catch(Exception e) {
                e.printStackTrace();

            }
        }
    public static void main(String[] args) {
            new Splash();
    }
}
