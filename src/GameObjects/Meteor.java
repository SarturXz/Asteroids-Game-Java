package GameObjects;

import Maths.Vector2D;
import States.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Meteor extends DynamicObject{

    private Size size;
    public Meteor(Vector2D position, Vector2D scale, Vector2D velocity, double maxVel, GameState state, BufferedImage texture, Size size) {
        super(position, scale, velocity, maxVel, state, texture);
        this.size = size;
        this.setRect(new Rectangle(width, height));
    }

    @Override
    public void update() {
        position = position.add(velocity);

        if (position.getX() > Main.Window.WIDTH)  { position.setX(-width); }
        if (position.getY() > Main.Window.HEIGHT) { position.setY(-height); }
        if (position.getX() < -width)  { position.setX(Main.Window.WIDTH  - ((double) width / 2));  }
        if (position.getY() < -height) { position.setY(Main.Window.HEIGHT - ((double) height / 2)); }

        angle += 0.01;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, (double) width / 2, (double) height / 2);
        at.scale(scale.getX(), scale.getY());
        g2d.drawImage(texture, at, null);
    }

    public Size getSize() {
        return size;
    }
}
