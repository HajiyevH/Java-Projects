package blackhole;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    private static JFrame frame;
    private static BoardGUI board;
    private static JButton restartButton; 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Black Hole Game");

            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    confirmExit();
                }
            });

            int boardSize = getBoardSizeFromUser();
            initializeRestartButton(); 
            startGame(boardSize); 
        });
    }

    private static void startGame(int boardSize) {
        board = new BoardGUI(boardSize);

        JPanel boardPanel = board.getBoardPanel();

        frame.getContentPane().removeAll(); 
        frame.setLayout(new BorderLayout());
        frame.add(boardPanel, BorderLayout.CENTER); 
        frame.add(restartButton, BorderLayout.SOUTH); 

        frame.revalidate();
        frame.repaint();
        frame.pack();
        frame.setVisible(true);
    }

    private static int getBoardSizeFromUser() {
        String[] options = {"5x5", "7x7", "9x9"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select Board Size:",
                "Board Size Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 1:
                return 7;
            case 2:
                return 9;
            default:
                return 5;
        }
    }

    private static void initializeRestartButton() {
        restartButton = null;
        if (restartButton == null) {
            restartButton = new JButton("Restart Game");
            restartButton.addActionListener((ActionEvent e) -> {
                confirmRestart(); 
            });
        }
    }

    private static void confirmExit() {
        int choice = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to exit the game?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private static void confirmRestart() {
        int choice = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to restart the game?",
                "Confirm Restart",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            int newBoardSize = getBoardSizeFromUser();
            startGame(newBoardSize); 
        }
    }
}