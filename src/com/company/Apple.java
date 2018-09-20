package com.company;

import java.awt.Point;
import java.util.Random;

public class Apple {
    public static Point applePos;

    public static Point spawnApple() {
        Random random = new Random();
        int x = random.nextInt(GameMap.WIDTH - 4) + 2;
        int y = random.nextInt(GameMap.HEIGHT - 2) + 1;
        applePos = new Point(x, y);

        return applePos;
    }
}