package com.company;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.Point;

public class Movement {

    private boolean keyUp = false;
    private boolean keyDown = false;
    private boolean keyRight = false;
    private boolean keyLeft = false;

    private boolean isCollision = false;
    private int counterForSnakeUpdate = 0;
    private final int SNAKE_UPDATE_THRESHOLD = 15;

    public boolean snakeMovementLoop(Terminal terminal, Snake snake) {

        while (!isCollision) {
            Key input = keyPress(terminal);
            updateDirectionStatus(input);
            boolean isAllowedToUpdate = snakeUpdateDelay();
            if (isAllowedToUpdate) {
                isCollision = moveSnakeInDirection(terminal, snake);
            }
        }
        return isCollision;
    }

    private boolean moveSnakeInDirection(Terminal terminal, Snake snake) {
        Point head = snake.getSnakeBody().get(0).point;
        Point newPos;

        if (keyUp) {
            newPos = new Point(head.x, head.y - 1);
        } else if (keyDown) {
            newPos = new Point(head.x, head.y + 1);
        } else if (keyRight) {
            newPos = new Point(head.x + 1, head.y);
        } else if (keyLeft) {
            newPos = new Point(head.x - 1, head.y);
        }
        // start of game, not moving in any direction
        else {
            return false;
        }

        isCollision = snake.moveSnakeAndCheckCollision(newPos, snake);
        GameMap.updateGameMap(terminal, snake);

        return isCollision;
    }


    private Key keyPress(Terminal terminal) {

        Key key = null;
        key = terminal.readInput();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return key;
    }

    private void updateDirectionStatus(Key key) {

        if (key != null) {
            switch (key.getKind()) {
                case ArrowDown:
                    keyUp = false;
                    keyDown = true;
                    keyRight = false;
                    keyLeft = false;
                    break;
                case ArrowUp:
                    keyUp = true;
                    keyDown = false;
                    keyRight = false;
                    keyLeft = false;
                    break;
                case ArrowRight:
                    keyUp = false;
                    keyDown = false;
                    keyRight = true;
                    keyLeft = false;
                    break;
                case ArrowLeft:
                    keyUp = false;
                    keyDown = false;
                    keyRight = false;
                    keyLeft = true;
                    break;
            }
        }
    }

    private boolean snakeUpdateDelay() {
        //Method for setting the automated movement speed for the snake
        if (counterForSnakeUpdate == SNAKE_UPDATE_THRESHOLD) {
            counterForSnakeUpdate = 0;
            return true;
        }
        counterForSnakeUpdate++;
        return false;
    }
}


