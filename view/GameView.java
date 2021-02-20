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
    private GameCanvas canvas;
    private Game game;
    private JPanel southPanel;

    private int xResolution;
    private int yResolution;

    private JButton buttons[];

    private boolean cheats;

    public enum State {
        READY, PLAYING, GAMEOVER
    }

    private State state = State.READY;

    public GameView(JFrame window, int xResolution, int yResolution, int mode, boolean cheats) {
        this.window = window;
        switch (mode) {
            case 1: window.setTitle("Easy"); break;
            case 2: window.setTitle("Normal"); break;
            case 3: window.setTitle("Hard"); break;
        }

        game = new Game(mode, cheats, this);

        this.xResolution = xResolution;
        this.yResolution = yResolution;

        buttons = new JButton[game.getSize()];

        this.cheats = cheats;
    }

    public void init() {
        Container container = window.getContentPane();

        canvas = new GameCanvas(this, xResolution, yResolution, game);
        container.add(BorderLayout.CENTER, canvas);
        canvas.repaint();

        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(game.getRoot(), game.getRoot()));
        southPanel.setPreferredSize(new Dimension(yResolution / 3, yResolution / 3));
        window.setLocation(xResolution / 2 - yResolution / 6, yResolution / 2 - yResolution / 3);

        ButtonListener buttonListener = new ButtonListener(this);
        
        for (int i = 0; i < game.getSize(); i++) {
            JButton button = new JButton();
            buttons[i] = button;
            button.addActionListener(buttonListener);
            southPanel.add(button);
        }

        container.add(BorderLayout.SOUTH, southPanel);
    }

    public void cheats() {
        for (int i = 0; i < game.getRoot(); i++) {
            for (int j = 0; j < game.getRoot(); j++) {
                String cheatsText = "";
                if (game.getBoard()[j][i][0] == 0) {
                    cheatsText = "+";
                }
                else {
                    cheatsText = "-";
                }
                buttons[j + i * game.getRoot()].setText(cheatsText);
            }
        }
    }

    public void endGame(int state) {
        //window.getContentPane().removeAll();
        //var endGame = new EndGameView(window, xResolution, yResolution, state, cheats);
        //endGame.init();
        //window.pack();
        //window.revalidate();
        for (int i = 0; i < game.getSize(); i++) {
            southPanel.remove(buttons[i]);
        }
        southPanel.setLayout(new GridLayout(1, 1));
        String exitButtonText = "Exit to Main Menu";
        if (state == 10) exitButtonText = "You Win - " + exitButtonText;
        else if (state == -10) exitButtonText = "Game Over - " + exitButtonText;
        JButton exitButton = new JButton(exitButtonText);
        exitButton.addActionListener( e -> {
            window.getContentPane().removeAll();
            var mainMenu = new MainMenuView(window, xResolution, yResolution, cheats);
            mainMenu.init();
            window.pack();
            window.revalidate();
        });
        southPanel.add(exitButton);
        window.pack();
        window.revalidate();
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public Game getGame() {
        return game;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public GameCanvas getCanvas() {
        return canvas;
    }
}
