package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Game;
import view.GameView;

public class ButtonListener implements ActionListener {
    
    private GameView panel;
    private Game game;

    public ButtonListener(GameView panel, Game game) {
        this.panel = panel;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setState(GameView.State.PLAYING);
        for (int i = 0; i < game.getSize(); i++) {
            if (e.getSource() == panel.getButtons()[i]) {
                int modRoot = i % game.getRoot();
                int divRoot = i / game.getRoot();
                if (game.getBoard()[modRoot][divRoot][2] != 1) {
                    if (game.getFirstTile() == -1) {
                        game.setFirstTile(modRoot, divRoot);
                        game.randomizeBoard();
                        panel.getButtons()[i].setText(String.valueOf(game.revealSpace(modRoot, divRoot)));
                    }
                    else {
                        int state = game.revealSpace(modRoot, divRoot);
                        switch (state) {
                            case -10: panel.endGame(state); break;
                            case 10: panel.endGame(state); break;
                            default: panel.getButtons()[i].setText(String.valueOf(state)); break;
                        }
                    }
                }
                panel.getButtons()[i].setEnabled(false);
            }
        }
        panel.getCanvas().repaint();
    }
}
