import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import view.MainMenuView;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
        final int xResolution = resolution.width;
        final int yResolution = resolution.height;

        var mainMenu = new MainMenuView(window, xResolution, yResolution);
        mainMenu.init();

        window.pack();
        window.setVisible(true);
    }
}