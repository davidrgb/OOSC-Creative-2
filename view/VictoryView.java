package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VictoryView {
    
    private JFrame window;

    private int xResolution;
    private int yResolution;

    public VictoryView(JFrame window, int xResolution, int yResolution) {
        this.window = window;
        window.setTitle("Victory");

        this.xResolution = xResolution;
        this.yResolution = yResolution;
    }

    public void init() {
        Container container = window.getContentPane();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.setPreferredSize(new Dimension(yResolution / 5, yResolution / 5));
        window.setLocation(xResolution / 2 - yResolution / 10, yResolution / 2 - yResolution / 10);

        JButton victoryButton = new JButton("You Win!");
        victoryButton.addActionListener( e -> {
            window.getContentPane().removeAll();
            MainMenuView mainMenu = new MainMenuView(window, xResolution, yResolution);
            mainMenu.init();
            window.pack();
            window.revalidate();
        });

        panel.add(victoryButton);

        container.add(BorderLayout.CENTER, panel);
    }
}