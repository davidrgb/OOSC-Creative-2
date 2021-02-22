package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Game;

public class GameCanvas extends JPanel {
    
    private GameView panel;

    private FontMetrics fontMetrics;

    private int yResolution;

    private int resolutionTile;

    private int root;
    private int[][][] board;

    public GameCanvas(GameView panel, int xResolution, int yResolution, Game game) {
        this.panel = panel;
        setBackground(Color.white);
        this.yResolution = yResolution;
        setPreferredSize(new Dimension(yResolution / 3, yResolution / 3));
        root = game.getRoot();
        resolutionTile = (yResolution / 3) / root;
        board = game.getBoard();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Courier New", Font.BOLD, 20));
        fontMetrics = g.getFontMetrics();

        GameView.State state = panel.getState();
        if (state == GameView.State.READY) {
            g2.setColor(Color.black);
            g2.setFont(new Font("Courier New", Font.BOLD, 20));
            String startString = "Press any tile to start";
            int xCenteredLocation = (yResolution / 6) - (fontMetrics.stringWidth(startString) / 2);
            int yCenteredLocation = (yResolution / 6) - (fontMetrics.getHeight() / 2);
            g2.drawString(startString, xCenteredLocation, yCenteredLocation);
        } else {

            if (state == GameView.State.GAMEOVER) {
                g2.setColor(Color.red);
                g2.drawString("GAMEOVER", 70, 150);
            }

            g2.setColor(Color.black);
            g2.setFont(new Font("Courier New", Font.BOLD, 12));

            for (int i = 0; i < root; i++) {
                for (int j = 0; j < root; j++) {
                    if (board[j][i][2] == 1) {
                        if (board[j][i][0] == 0) {
                            g2.setColor(Color.green);
                            g2.fillRect(resolutionTile * j, resolutionTile * i, resolutionTile, resolutionTile);
                        }
                        else {
                            g2.setColor(Color.red);
                            g2.fillRect(resolutionTile * j, resolutionTile * i, resolutionTile, resolutionTile);
                        }
                    }
                    g2.setColor(Color.black);
                    g2.drawRect(resolutionTile * j, resolutionTile * i, resolutionTile, resolutionTile);
                }
                
            }
        }
    }
}
