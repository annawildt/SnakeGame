package com.company;

import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;
import java.awt.Point;

public class GameMap {
    public static final int WIDTH = 60;
    public static final int HEIGHT = 25;

    public static int appleEatenCounter = 0;

    public static void createGameMap(Terminal terminal) {

        int[] borderVertical = new int[]{0, WIDTH};
        int[] borderVertical2 = new int[]{1, WIDTH-1};
        int[] borderHorizontal = new int[]{0, HEIGHT};

        terminal.applyBackgroundColor(Terminal.Color.WHITE);

        for (int v : borderVertical) {
            for (int i = 0; i <= HEIGHT; i++) {
                terminal.moveCursor(v, i);
                terminal.putCharacter(' ');
            }
        }
        for (int v : borderVertical2) {
            for (int i = 0; i <= HEIGHT; i++) {
                terminal.moveCursor(v, i);
                terminal.putCharacter(' ');
            }
        }
        for (int h : borderHorizontal) {
            for (int i = 0; i <= WIDTH; i++) {
                terminal.moveCursor(i, h);
                terminal.putCharacter(' ');
            }
        }

        printMessage(terminal, 45, 25, "Score: " + (appleEatenCounter * 10));

        terminal.setCursorVisible(false);
    }

    public static void updateGameMap(Terminal terminal, Snake snake) {
        terminal.clearScreen();
        createGameMap(terminal);
        drawSnake(terminal, snake.getSnakeBody());
        drawApple(terminal, Apple.applePos);
    }

    public static void drawSnake(Terminal terminal, List<SnakePart> snakeList) {
        Point head = snakeList.get(0).point;
        terminal.moveCursor(head.x, head.y);
        terminal.applyForegroundColor(Terminal.Color.BLUE);
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
        terminal.putCharacter('\u2182');

        for (int i = 1; i < snakeList.size(); i++) {
            Point point = snakeList.get(i).point;
            terminal.moveCursor(point.x, point.y);
            terminal.applyForegroundColor(Terminal.Color.GREEN);
            terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
            terminal.putCharacter('\u2182');
        }
    }

    public static void printMessage(Terminal terminal, int x, int y, String message) {
        for (int i = 0; i < message.length(); i++) {
            terminal.applyBackgroundColor(Terminal.Color.WHITE);
            terminal.applyForegroundColor(Terminal.Color.BLACK);
            terminal.moveCursor(x, y);
            terminal.putCharacter(message.charAt(i));
            x++;
        }
    }

    public static void drawApple(Terminal terminal, Point applePos) {
        terminal.moveCursor(applePos.x, applePos.y);
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
        terminal.applyForegroundColor(Terminal.Color.RED);
        terminal.putCharacter('\u26E3');
    }

}
