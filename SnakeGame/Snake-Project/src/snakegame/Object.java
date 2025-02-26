package snakegame;

import java.awt.Point;

public class Object {
    protected Point position;

    public Object(Point point){
        this.position = point;
    }

    public Point getPoint(){
        return position;
    }
}
