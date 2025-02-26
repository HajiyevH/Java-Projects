package blackhole;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowKeyListener implements KeyListener {
    private final BoardGUI boardGUI; 

    public ArrowKeyListener(BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (boardGUI.getCurrentPoint() == null) return;

        int dx = 0, dy = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> dx = -1;
            case KeyEvent.VK_DOWN -> dx = 1;
            case KeyEvent.VK_LEFT -> dy = -1;
            case KeyEvent.VK_RIGHT -> dy = 1;
            default -> { return; }
        }

        boolean isValid = boardGUI.moveSpaceship(
            boardGUI.getCurrentPoint().x,
            boardGUI.getCurrentPoint().y,
            dx,
            dy
        );

        if (isValid) {
            boardGUI.incrementTurn();
            boardGUI.update();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}