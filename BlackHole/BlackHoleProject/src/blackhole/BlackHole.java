package blackhole;

import java.awt.Color;
import java.awt.Point;

public class BlackHole {
    private int boardSize;
    private Field[][] board;
    private int blackHoleX;
    private int blackHoleY;

    public BlackHole(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[boardSize][boardSize];
        blackHoleX = boardSize / 2;
        blackHoleY = boardSize / 2;

        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new Field(); 
            }
        }
        
        board[boardSize / 2][boardSize / 2].setBlackHole(true);
    
        for (int i = 0; i < boardSize / 2; i++) {
            board[i][i].setSpaceship(true);
            board[i][i].setColor(Color.RED);
    
            board[i][boardSize - 1 - i].setSpaceship(true);
            board[i][boardSize - 1 - i].setColor(Color.RED);
        }
    
        for (int i = boardSize / 2 + 1; i < boardSize; i++) {
                board[i][boardSize - i-  1].setSpaceship(true);
                board[i][boardSize - i - 1].setColor(Color.YELLOW);
    
                board[i][i].setSpaceship(true);
                board[i][i].setColor(Color.YELLOW);
        }
    }
    
    public Field getField(int x, int y) {
        return board[x][y];
    }

    public int getBoardSize() {
        return boardSize;
    }

    public boolean isBlackHole(int x, int y) {
        return x == blackHoleX && y == blackHoleY;
    }

    public Point getBlackHolePosition() {
        return new Point(blackHoleX, blackHoleY);
    }
}