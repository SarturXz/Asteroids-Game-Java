package Collisions;

import GameObjects.GameObject;

import java.awt.*;

public class CollisionChecker {
    private static int[] getRectCoordinates(GameObject object){
        int rectX = (int) (object.getPosition().getX() + object.getRect().x + object.getRectOffset().getX());
        int rectY = (int) (object.getPosition().getY() + object.getRect().y + object.getRectOffset().getY());
        int rectW = (int) (object.getPosition().getX() + object.getRect().x + object.getRect().width  + object.getRectOffset().getX());
        int rectH = (int) (object.getPosition().getY() + object.getRect().y + object.getRect().height + object.getRectOffset().getX());

        return new int[]{rectX, rectY, rectW, rectH };
    }
    public static boolean checkCollision(GameObject a, GameObject b){
        int[] aRect = getRectCoordinates(a);
        int[] bRect = getRectCoordinates(b);

        return aRect[0] < bRect[0] + b.getRect().width
                && aRect[0] > bRect[0]
                && aRect[1] + a.getRect().height > bRect[1]
                && aRect[1] + a.getRect().height < bRect[1] + b.getRect().height
                || aRect[0] < bRect[0] + b.getRect().width
                && aRect[0] > bRect[0]
                && aRect[1] < bRect[1] + a.getRect().height
                && aRect[1] > bRect[1]
                || aRect[0] + a.getRect().width > bRect[0]
                && aRect[0] + a.getRect().width < bRect[0] + b.getRect().width
                && aRect[1] + a.getRect().height > bRect[1]
                && aRect[1] + a.getRect().height < bRect[1] + b.getRect().height
                || aRect[0] + a.getRect().width > bRect[0]
                && aRect[0] + a.getRect().width < bRect[0] + b.getRect().width
                && aRect[1] < bRect[1] + a.getRect().height
                && aRect[1] > bRect[1];
    }

    public static void debugCollisionMask(GameObject object, Graphics g){
        int[] rect = getRectCoordinates(object);

        g.setColor(new Color(255, 0, 0, 150));
        g.fillRect(rect[0], rect[1], object.getRect().width, object.getRect().height);
    }
}
