package GameObjects;

import Maths.Vector2D;
import States.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Laser extends DynamicObject{
    public Laser(Vector2D position, Vector2D scale, Vector2D velocity, double maxVel, double angle, GameState state, BufferedImage texture) {
        super(position, scale, velocity, maxVel, state, texture);
        this.angle = angle;
        this.velocity = velocity.scale(maxVel);
        this.position.setX(position.getX() - (double) width / 2);
    }

    @Override
    public void update() {
        position = position.add(velocity);

        if (position.getX() > Main.Window.WIDTH)  { state.getDynamicObjectsList().remove(this); }
        if (position.getY() > Main.Window.HEIGHT) { state.getDynamicObjectsList().remove(this); }
        if (position.getX() < -width)  { state.getDynamicObjectsList().remove(this); }
        if (position.getY() < -height) { state.getDynamicObjectsList().remove(this); }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(getPosition().getX(), getPosition().getY());
        at.rotate(angle,  (double) width / 2, 0);
        at.scale(scale.getX(), scale.getY());
        g2d.drawImage(texture, at, null);
    }
}
