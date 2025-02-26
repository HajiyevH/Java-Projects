package blackhole;

import javax.swing.*;
import java.awt.*;

public class BoardGUI {
    private BlackHole board; 
    private final JButton[][] buttons;
    private final JPanel boardPanel;
    private final JLabel turnLabel; 
    private int redPlayers;
    private int yellowPlayers;
    private Point currentPoint;
    private int turn;
    private String winner;

    public BoardGUI(int boardSize) {
        board = new BlackHole(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        buttons = new JButton[boardSize][boardSize];
        redPlayers = boardSize - 1;
        yellowPlayers = boardSize - 1;
        currentPoint = null;
        turn = 0;

        turnLabel = new JLabel("Turn: Red"); 
        turnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        initializeButtons(); 
        update();
    }

    public JPanel getBoardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(turnLabel, BorderLayout.NORTH); 
        panel.add(boardPanel, BorderLayout.CENTER); 
        return panel;
    }

    private void initializeButtons() {
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(80, 80));
                button.addActionListener(new ButtonListener(i, j, this));
                button.addKeyListener(new ArrowKeyListener(this));
                buttons[i][j] = button;

                boardPanel.add(button);
            }
        }
    }

    public BlackHole getBoard() {
        return board;
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        turn++;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public boolean moveSpaceship(int x, int y, int dx, int dy) {
        Field currentField = board.getField(x, y);
        int newX = x + dx;
        int newY = y + dy;

        if (newX < 0 || newX >= board.getBoardSize() || newY < 0 || newY >= board.getBoardSize() ||
            board.getField(newX, newY).isItASpaceship()) {
            return false; 
        }

        if (board.getField(newX, newY).isItABlackhole()) {
            if (currentField.getColor() == Color.RED) redPlayers--;
            if (currentField.getColor() == Color.YELLOW) yellowPlayers--;

            currentField.setSpaceship(false);
            currentField.setColor(Color.WHITE);
            currentPoint = null;

            if (checkGameOver()) update();
            return true;
        }

        while (true) {
            newX += dx;
            newY += dy;

            if (newX < 0 || newX >= board.getBoardSize() || newY < 0 || newY >= board.getBoardSize()) {
                newX -= dx;
                newY -= dy;
                break;
            }

            Field targetField = board.getField(newX, newY);

            if (targetField.isItASpaceship() || targetField.isItABlackhole()) {
                if (targetField.isItABlackhole()) {
                    if (currentField.getColor() == Color.RED) redPlayers--;
                    if (currentField.getColor() == Color.YELLOW) yellowPlayers--;

                    currentField.setSpaceship(false);
                    currentField.setColor(Color.WHITE);
                    currentPoint = null;

                    if (checkGameOver()) update();
                    return true;
                }
                newX -= dx;
                newY -= dy;
                break;
            }
        }

        Field targetField = board.getField(newX, newY);
        targetField.setSpaceship(true);
        targetField.setColor(currentField.getColor());
        currentField.setSpaceship(false);
        currentField.setColor(Color.WHITE);
        currentPoint = null;

        if(checkGameOver()) return false;
        return true;
    }

    private boolean checkGameOver() {
        int totalShipsAtStart = board.getBoardSize() - 1;
        int requiredShipsInBlackHole = totalShipsAtStart / 2;

        return (totalShipsAtStart - redPlayers) >= requiredShipsInBlackHole || 
               (totalShipsAtStart - yellowPlayers) >= requiredShipsInBlackHole;
    }

    private void restartGame() {
        int boardSize = board.getBoardSize();
        board = new BlackHole(boardSize);
        redPlayers = boardSize - 1;
        yellowPlayers = boardSize - 1;
        currentPoint = null;
    
        boardPanel.removeAll(); 
        turn = 0; 
        winner = null;
        initializeButtons(); 
        boardPanel.revalidate();
        boardPanel.repaint();
        System.err.println(turn);
        update(); 
        System.err.println(turn);
    }

    public void update() {
        String currentTurn = (turn % 2 == 0) ? "Red" : "Yellow";
        turnLabel.setText("Turn: " + currentTurn);

        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                Field field = board.getField(i, j);
                JButton button = buttons[i][j];

                if (field.isItABlackhole()) {
                    button.setBackground(Color.BLACK);
                    button.setOpaque(true);      
                    button.setBorderPainted(false);
                } else if (field.isItASpaceship()) {
                    button.setBackground(field.getColor());
                    button.setOpaque(true);          
                    button.setBorderPainted(false);
                } else {
                    button.setBackground(Color.WHITE);
                    button.setOpaque(true);          
                    button.setBorderPainted(false);
                }

                button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        }

        if (currentPoint != null) {
            buttons[currentPoint.x][currentPoint.y].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        }


        if (checkGameOver()) {
            winner = redPlayers < yellowPlayers ? "Red" : "Yellow";
            JOptionPane.showMessageDialog(boardPanel, winner + " wins!");
            restartGame();
        }
    }
}