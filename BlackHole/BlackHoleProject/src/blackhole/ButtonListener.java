package blackhole;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class ButtonListener implements ActionListener {
    private final int x;
    private final int y;
    private final BoardGUI boardGUI; 

    public ButtonListener(int x, int y, BoardGUI boardGUI) {
        this.x = x;
        this.y = y;
        this.boardGUI = boardGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Field field = boardGUI.getBoard().getField(x, y);

        if (field.isItASpaceship() && 
           ((boardGUI.getTurn() % 2 == 0 && field.getColor() == Color.RED) || 
           (boardGUI.getTurn() % 2 == 1 && field.getColor() == Color.YELLOW))) {
            boardGUI.setCurrentPoint(new Point(x, y));
        }

        boardGUI.update();
    }
}