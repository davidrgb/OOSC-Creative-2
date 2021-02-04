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
        for (int i = 0; i < panel.getGame().getSize(); i++) {
            if (e.getSource() == panel.getButtons()[i]) {
                if (panel.getGame().getBoard()[i % panel.getGame().getRoot()][i / panel.getGame().getRoot()][2] != 1) {
                    if (panel.getGame().getFirstTile() == -1) {
                        panel.getGame().setFirstTile(i % panel.getGame().getRoot(), i / panel.getGame().getRoot());
                        panel.getGame().randomizeBoard();
                        panel.getButtons()[i].setText(String.valueOf(panel.getGame().revealSpace(i % panel.getGame().getRoot(), i / panel.getGame().getRoot())));
                    }
                    else {
                        int state = panel.getGame().revealSpace(i % panel.getGame().getRoot(), i / panel.getGame().getRoot());
                        switch (state) {
                            case -10: panel.gameOver(); break;
                            case 10: panel.victory(); break;
                            default: panel.getButtons()[i].setText(String.valueOf(state)); break;
                        }
                    }
                }
            }
        }
    }
}
