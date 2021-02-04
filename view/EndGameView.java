package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EndGameView {
    
    private JFrame window;

    private int xResolution;
    private int yResolution;

    private int state;

    private boolean cheats;

    public EndGameView(JFrame window, int xResolution, int yResolution, int state, boolean cheats) {
        this.window = window;
        if (state == -10) window.setTitle("Game Over");
        else window.setTitle("Victory");

        this.xResolution = xResolution;
        this.yResolution = yResolution;

        this.state = state;

        this.cheats = cheats;
    }

    public void init() {
        Container container = window.getContentPane();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.setPreferredSize(new Dimension(yResolution / 5, yResolution / 5));
        window.setLocation(xResolution / 2 - yResolution / 10, yResolution / 2 - yResolution / 10);

        String buttonText = "";

        if (state == -10) buttonText = "Game Over";
        else buttonText = "You Win!";

        JButton button = new JButton(buttonText);
        button.addActionListener( e -> {
            window.getContentPane().removeAll();
            MainMenuView mainMenu = new MainMenuView(window, xResolution, yResolution, cheats);
            mainMenu.init();
            window.pack();
            window.revalidate();
        });

        panel.add(button);

        container.add(BorderLayout.CENTER, panel);
    }
}
