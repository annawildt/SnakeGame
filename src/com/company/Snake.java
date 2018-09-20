package com.company;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    List<SnakePart> snakeBody;

    public Snake() {
        snakeBody = new ArrayList<>();
    }

    public List<SnakePart> startSnake() {
        for (int i = 0; i < 4; i++) {
            SnakePart snakePart = new SnakePart(GameMap.WIDTH / 2, (GameMap.HEIGHT / 2 - i));
            addSnakeBodyParts(snakePart);
        }
        return snakeBody;
    }

    public void addSnakeBodyParts(SnakePart snake) {
        snakeBody.add(0, snake);
    }

    public boolean moveSnakeAndCheckCollision(Point newPos, Snake snake) {
        SnakePart snakePart = new SnakePart(newPos.x, newPos.y);
        snakeBody.add(0, snakePart);

        Collision col = new Collision();

        //If there's no apple collision, move normally by adding new head and removing last bodypart.
        boolean isAppleFound = col.collisionApple(this);
        if (!isAppleFound) {
            snakeBody.remove(snakeBody.size() - 1);
        }

        boolean colCheckBody = col.collisionBody(snake);
        boolean colCheckWall = col.collisionWall(snake);

        if (colCheckBody || colCheckWall) {
            return true;
        } else {
            return false;
        }
    }

    public List<SnakePart> getSnakeBody() {
        return snakeBody;
    }
}

class SnakePart {
    public Point point;
    public SnakePart(int x, int y) {
        this.point = new Point(x,y);
    }
}
