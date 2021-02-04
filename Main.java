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

        boolean cheats = false;

        var mainMenu = new MainMenuView(window, xResolution, yResolution, cheats);
        mainMenu.init();

        window.pack();
        window.setVisible(true);
    }
}