package model;

import java.util.Random;

public class Game {

    private int mode;

    private int[][][] board;
    private int size;
    private int root;

    private int firstTileX;
    private int firstTileY;

    Random random = new Random();

    public Game(int mode) {
        this.mode = mode;
        switch (mode) {
            case 1: size = 9; root = 3; break;
            case 2: size = 25; root = 5; break;
            case 3: size = 64; root = 8; break;
        }

        firstTileX = -1;
        firstTileY = -1;
    }

    public void randomizeBoard() {
        for (int x = 0; x < root; x++) {
            for (int y = 0; y < root; y++) {
                if (firstTileX == x && firstTileY == y){
                    board[x][y][2] = 1; // Marks first tile as selected
                    continue; // Skips trap assignment of file selected tile
                }
                int temp = random.nextInt(10) + 1;
                if (temp < 8) {
                    board[x][y][0] = 1;
                    for (int rows = y + 1; rows >= y - 1; rows--) { // Adjacent trap addition
                        if (rows >= 0 && rows < root) {
                            for (int columns = x - 1; columns <= x + 1; columns++) {
                                if (columns >= 0 && columns < root && (columns != x && rows != y)) board[columns][rows][1] = board[columns][rows][1] + 1;
                            }
                        }
                    }
                }
                else board[x][y][0] = 0;
                board[x][y][2] = 0;
            }
        }
    }

    public void revealSpace(int x, int y) {
        if (board[x][y][0] == 1) gameOver();
        else board[x][y][2] = 2;
    }

    public void gameOver() {
        System.out.println("GAME OVER");
    }

    public int getSize() {
        return size;
    }

    public int getRoot() {
        return root;
    }
    
}
