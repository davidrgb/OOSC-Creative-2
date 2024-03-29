package model;

import java.util.Random;

import view.GameView;

public class Game {

    private int[][][] board;
    private int size;
    private int root;
    private int traps;
    private int safeSpaces;

    private int firstTileX;
    private int firstTileY;

    private int state;

    private boolean cheats;

    GameView panel;

    Random random = new Random();

    public Game(int mode, boolean cheats, GameView panel) {
        switch (mode) {
            case 1: size = 9; root = 3; break;
            case 2: size = 25; root = 5; break;
            case 3: size = 64; root = 8; break;
        }

        firstTileX = -1;
        firstTileY = -1;

        board = new int[root][root][3]; // Coordinate - Coordinate - Trap? : Adjactent Traps? : Already Activated?

        traps = 0;
        safeSpaces = 0;

        state = 0;

        this.cheats = cheats;

        this.panel = panel;
    }

    public void randomizeBoard() {
        for (int x = 0; x < root; x++) {
            for (int y = 0; y < root; y++) {
                if (firstTileX == x && firstTileY == y){
                    board[x][y][2] = 1; // Marks first tile as selected
                    continue; // Skips trap assignment of file selected tile
                }
                int temp = random.nextInt(10) + 1;
                if (temp < 8 && traps < (size * 7 / 10)) { // 7 in 10 chance current space is a trap, with a maximum of 7 tenths of the board as traps.
                    board[x][y][0] = 1; // Sets tile as trap
                    for (int rows = y + 1; rows >= y - 1; rows--) { // Adjacent trap addition
                        if (rows >= 0 && rows < root) {
                            for (int columns = x - 1; columns <= x + 1; columns++) {
                                if (columns >= 0 && columns < root) board[columns][rows][1] = board[columns][rows][1] + 1;
                            }
                        }
                    }
                    board[x][y][1] = board[x][y][1] - 1; // Subtracts each trap from its own adjacent calculation
                    traps++;
                }
                else board[x][y][0] = 0; // Sets tile as safe
                board[x][y][2] = 0; // Sets tile as not activated
            }
        }

        if (cheats) {
            panel.cheats();
        }
    }

    public int revealSpace(int x, int y) {
        if (board[x][y][0] == 1) { // Trap selected
            deactivateBoard(); // Marks all tiles as activated
            return -10; // Returns loss code
        }
        else if (board[x][y][0] == 0) { // Safe space selected
            board[x][y][2] = 1; // Space marked as activated
            safeSpaces++;
        }
        if (traps + safeSpaces == size) { // All safe spaces activated
            deactivateBoard(); // Marks all tiles as activated
            return 10; // Returns victory code
        }
        return board[x][y][1]; // If no loss or win, returns number of adjacent traps
    }

    public void deactivateBoard() { // Increments through all tiles and marks them as activated for final map painting
        for (int x = 0; x < root; x++) {
            for (int y = 0; y < root; y++) {
                board[x][y][2] = 1;
            }
        }
    }

    public int[][][] getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }

    public int getRoot() {
        return root;
    }

    public int getFirstTile() {
        return firstTileX;
    }

    public void setFirstTile(int x, int y) {
        firstTileX = x;
        firstTileY = y;
    }
}
