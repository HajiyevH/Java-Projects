package snakegame;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> body;
    private Direction currentDirection;
    private boolean hasEatenFood;

    public Snake(int startX, int startY) {
        body = new ArrayList<>();
        body.add(new Point(startX, startY));
        body.add(new Point(startX - 1, startY));
        Direction[] directions = Direction.values();
        currentDirection = directions[(int) (Math.random() * directions.length)];
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
    
    public void move() {
        Point head = body.get(0);
        Point newHead = new Point(head);

        switch (currentDirection) {
            case NORTH -> newHead.translate(0, -1);
            case SOUTH -> newHead.translate(0, 1);
            case WEST -> newHead.translate(-1, 0);
            case EAST -> newHead.translate(1, 0);
        }

        body.add(0, newHead);

        if (!hasEatenFood) {
            body.remove(body.size() - 1);
        } else {
            hasEatenFood = false;
        }
    }

    public void grow() {
        hasEatenFood = true;
    }

    public Point getHead() {
        return body.get(0);
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public void setDirection(Direction direction) {
        if ((currentDirection == Direction.NORTH && direction != Direction.SOUTH) ||
            (currentDirection == Direction.SOUTH && direction != Direction.NORTH) ||
            (currentDirection == Direction.WEST && direction != Direction.EAST) ||
            (currentDirection == Direction.EAST && direction != Direction.WEST)) {
            currentDirection = direction;
        }
    }

    public boolean checkSelfCollision() {
        Point head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
}
