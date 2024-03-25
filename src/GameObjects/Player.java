package GameObjects;

import Input.Keyboard;
import Maths.Vector2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends DynamicObject {

    private final double ACC = 0.15;
    private final double DACC = 0.05;
    private final double DELTAANGLE = 0.1;
    private Vector2D heading = new Vector2D(0, 1);
    private Vector2D acceleration = new Vector2D();

    public Player(Vector2D position, Vector2D scale, Vector2D velocity, double maxVel, BufferedImage texture) {
        super(position, scale, velocity, maxVel, texture);
    }

    @Override
    public void update() {
        if (Keyboard.getKey(KeyEvent.VK_LEFT) || Keyboard.getKey(KeyEvent.VK_A)) {
            angle -= DELTAANGLE;
        }
        if (Keyboard.getKey(KeyEvent.VK_RIGHT) || Keyboard.getKey(KeyEvent.VK_D)) {
            angle += DELTAANGLE;
        }

        if (Keyboard.getKey(KeyEvent.VK_UP) || Keyboard.getKey(KeyEvent.VK_W)) {
            acceleration = heading.scale(ACC);
        } else {
            acceleration = (velocity.scale(-1).normalize()).scale(DACC);
        }

        velocity = velocity.add(acceleration);
        velocity.limit(maxVel);

        heading = heading.setDirection(angle - (Math.PI / 2));

        position = position.add(velocity);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle,
                (texture.getWidth() * scale.getX()) / 2,
                (texture.getHeight() * scale.getY()) / 2);
        at.scale(scale.getX(), scale.getY());
        g2d.drawImage(texture, at, null);
    }
}