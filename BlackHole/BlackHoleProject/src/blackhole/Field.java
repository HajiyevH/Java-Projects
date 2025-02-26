/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackhole;

import java.awt.Color;

/**
 *
 * @author hajiaga
 */

public class Field {
    private boolean isSpaceship;
    private boolean isBlackHole;
    private Color color;

    public Field() {
        this.isSpaceship = false;
        this.isBlackHole = false;
        this.color = Color.WHITE; 
    }

    public boolean isItASpaceship() {
        return isSpaceship;
    }

    public void setSpaceship(boolean isSpaceship) {
        this.isSpaceship = isSpaceship;
    }

    public boolean isItABlackhole() {
        return isBlackHole;
    }

    public void setBlackHole(boolean isBlackHole) {
        this.isBlackHole = isBlackHole;
        if (isBlackHole) {
            this.color = Color.BLACK;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}