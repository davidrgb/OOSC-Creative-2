package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameView;

public class ButtonListener implements ActionListener {
    
    private GameView panel;

    public ButtonListener(GameView panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setState(GameView.State.PLAYING);
        for (int i = 0; i < panel.getGame().getSize(); i++) {
            if (e.getSource() == panel.getButtons()[i]) {
                int modRoot = i % panel.getGame().getRoot();
                int divRoot = i / panel.getGame().getRoot();
                if (panel.getGame().getBoard()[modRoot][divRoot][2] != 1) {
                    if (panel.getGame().getFirstTile() == -1) {
                        panel.getGame().setFirstTile(modRoot, divRoot);
                        panel.getGame().randomizeBoard();
                        panel.getButtons()[i].setText(String.valueOf(panel.getGame().revealSpace(modRoot, divRoot)));
                    }
                    else {
                        int state = panel.getGame().revealSpace(modRoot, divRoot);
                        switch (state) {
                            case -10: panel.endGame(state); panel.setState(GameView.State.GAMEOVER); break;
                            case 10: panel.endGame(state); panel.setState(GameView.State.GAMEOVER); break;
                            default: panel.getButtons()[i].setText(String.valueOf(state)); break;
                        }
                    }
                }
            }
        }
        panel.getCanvas().repaint();
    }
}
