package GameObjects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Assets.Assets;
import Input.Keyboard;
import Maths.Vector2D;

public class Player extends DynamicObject{

    private Vector2D heading = new Vector2D();

    public Player(Vector2D position, Vector2D scale, Vector2D velocity, BufferedImage texture) {
        super(position, scale, velocity, texture);
    }

    @Override
    public void update() {
        if (Keyboard.getKey(KeyEvent.VK_LEFT)) { angle -= Math.PI / 20; }
        if (Keyboard.getKey(KeyEvent.VK_RIGHT)) { angle += Math.PI / 20; }

        heading = heading.setDirection(angle - (Math.PI / 2));
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle,
                (texture.getWidth()*scale.getX())  / 2,
                (texture.getHeight()*scale.getY()) / 2);
        at.scale(scale.getX(), scale.getY());
        g2d.drawImage(texture, at, null);
    }
}
