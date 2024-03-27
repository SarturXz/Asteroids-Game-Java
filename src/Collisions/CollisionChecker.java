package Collisions;

import java.awt.*;

public class CollisionChecker {
    public static boolean checkCollision(Rectangle a, Rectangle b){
        return a.intersects(b);
    }

    public static void debugCollisionMask(Rectangle rect, Graphics g){
        g.setColor(new Color(255, 0, 0, 150));
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }
}
