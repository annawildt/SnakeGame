package com.company;

import java.awt.*;

public class Collision {
    private boolean collision = false;

    public boolean collisionWall(Snake snake) {
        Point p = snake.getSnakeBody().get(0).point;

        if (p.x > GameMap.WIDTH - 2 || p.x < 2) {
            collision = true;
        } else if (p.y > GameMap.HEIGHT - 1 || p.y < 1) {
            collision = true;
        }
        return collision;
    }

    public boolean collisionBody(Snake snake) {
        Point head = snake.getSnakeBody().get(0).point;

        for (int i = 1; i < snake.getSnakeBody().size(); i++) {
            if (snake.getSnakeBody().get(i).point.x == head.x && snake.getSnakeBody().get(i).point.y == head.y) {
                collision = true;
            }
        }
        return collision;
    }

    public boolean collisionApple(Snake snake) {
        Point head = snake.getSnakeBody().get(0).point;
        if (Apple.applePos.x == head.x && Apple.applePos.y == head.y) {

            MP3Player sound = new MP3Player();
            sound.playFX("Cartoon-crunching-bite.mp3");

            GameMap.appleEatenCounter++;
            Apple.spawnApple();

            return true;
        } else {
            return false;
        }
    }
}
