package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ButtonListener;
import model.Game;

public class GameView {
    
    private JFrame window;
    private Game game;

    private int xResolution;
    private int yResolution;

    private JButton buttons[];

    public GameView(JFrame window, int xResolution, int yResolution, int mode) {
        this.window = window;
        switch (mode) {
            case 1: window.setTitle("Easy"); break;
            case 2: window.setTitle("Normal"); break;
            case 3: window.setTitle("Hard"); break;
        }

        game = new Game(mode);

        this.xResolution = xResolution;
        this.yResolution = yResolution;

        buttons = new JButton[game.getSize()];
    }

    public void init() {
        Container container = window.getContentPane();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(game.getRoot(), game.getRoot()));
        panel.setPreferredSize(new Dimension(yResolution * 2 / 3, yResolution * 2 / 3));
        window.setLocation(xResolution / 2 - yResolution / 3, yResolution / 2 - yResolution / 3);

        ButtonListener buttonListener = new ButtonListener(this);
        
        for (int i = 0; i < game.getSize(); i++) {
            JButton button = new JButton();
            buttons[i] = button;
            button.addActionListener(buttonListener);
            panel.add(button);
        }

        container.add(BorderLayout.CENTER, panel);
    }

    /*public void gameOver() {
        window.getContentPane().removeAll();
        var gameOver = new GameOverView(window, xResolution, yResolution);
        gameOver.init();
        window.pack();
        window.revalidate();
    }

    public void victory() {
        window.getContentPane().removeAll();
        var victory = new VictoryView(window, xResolution, yResolution);
        victory.init();
        window.pack();
        window.revalidate();
    }*/

    public void endGame(int state) {
        window.getContentPane().removeAll();
        var endGame = new EndGameView(window, xResolution, yResolution, state);
        endGame.init();
        window.pack();
        window.revalidate();
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public Game getGame() {
        return game;
    }
}
