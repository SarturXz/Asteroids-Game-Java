package GameObjects;

import Collisions.CollisionChecker;
import Main.Window;
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

        setRect(new Rectangle(15, 15));
        rectOffset = new Vector2D(0, 0);
    }
    @Override
    public void update() {
        super.update();
        position = position.add(velocity);

        if (position.getX() > Window.WIDTH)  { state.getDynamicObjectsList().remove(this); }
        if (position.getY() > Window.HEIGHT) { state.getDynamicObjectsList().remove(this); }
        if (position.getX() < -width)  { state.getDynamicObjectsList().remove(this); }
        if (position.getY() < -height) { state.getDynamicObjectsList().remove(this); }

        for (int i = 0; i < state.getMeteorsList().size(); i++){
            if (CollisionChecker.checkCollision(this.getRect(), state.getMeteorsList().get(i).getRect())){
                state.divideMeteor(state.getMeteorsList().get(i));
                destroy(state.getMeteorsList().get(i));
                destroy(this);
                state.getMeteorsList().remove( state.getMeteorsList().get(i));
            }
        }
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
