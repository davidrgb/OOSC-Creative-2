package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenuView {
    private JFrame window;

    private int xResolution;
    private int yResolution;

    private boolean cheats;
    private String cheatsButtonText;

    public MainMenuView(JFrame window, int xResolution, int yResolution, boolean cheats) {
        this.window = window;
        window.setTitle("Main Menu");

        this.xResolution = xResolution;
        this.yResolution = yResolution;

        this.cheats = cheats;
        if (!cheats) cheatsButtonText = "Cheats Off";
        else cheatsButtonText = "Cheats On";
    }

    public void init() {
        Container container = window.getContentPane();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.setPreferredSize(new Dimension(xResolution / 5, xResolution / 5));
        window.setLocation(xResolution / 2 - xResolution / 10, yResolution / 2 - xResolution / 10);

        JButton instructionsButton = new JButton("How To Play");
        JButton easyButton = new JButton("Easy - 3 x 3");
        JButton normalButton = new JButton("Normal - 5 x 5");
        JButton hardButton = new JButton("Hard - 8 x 8");
        JButton cheatsButton = new JButton(cheatsButtonText);

        panel.add(instructionsButton);
        panel.add(easyButton);
        panel.add(normalButton);
        panel.add(hardButton);
        panel.add(cheatsButton);

        container.add(BorderLayout.CENTER, panel);

        instructionsButton.addActionListener( e -> {
            window.getContentPane().removeAll();
            var instructions = new InstructionsView(window, xResolution, yResolution, cheats);
            instructions.init();
            window.pack();
            window.revalidate();
        });

        easyButton.addActionListener( e -> {
            window.getContentPane().removeAll();
            var easy = new GameView(window, xResolution, yResolution, 1, cheats);
            easy.init();
            window.pack();
            window.revalidate();
        });

        normalButton.addActionListener( e -> {
            window.getContentPane().removeAll();
            var normal = new GameView(window, xResolution, yResolution, 2, cheats);
            normal.init();
            window.pack();
            window.revalidate();
        });

        hardButton.addActionListener( e -> {
            window.getContentPane().removeAll();
            var hard = new GameView(window, xResolution, yResolution, 3, cheats);
            hard.init();
            window.pack();
            window.revalidate();
        });

        cheatsButton.addActionListener( e -> {
            cheats = !cheats;
            if (!cheats) cheatsButton.setText("Cheats Off");
            else cheatsButton.setText("Cheats On");
        });
    }
}
