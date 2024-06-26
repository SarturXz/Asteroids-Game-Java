package GameObjects;

import Maths.Vector2D;
import States.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class DynamicObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxVel;
    protected int width;
    protected int height;
    protected GameState state;

    public DynamicObject(Vector2D position, Vector2D scale, Vector2D velocity, double maxVel, GameState state, BufferedImage texture) {
        super(position, scale, texture);

        this.velocity = velocity;
        this.maxVel = maxVel;
        this.angle = 0;
        this.width = (int) (texture.getWidth() * this.scale.getX());
        this.height = (int) (texture.getHeight() * this.scale.getY());
        this.state = state;
    }

    @Override
    public void update() {
        rect.x = (int) position.getX() + (int) rectOffset.getX();
        rect.y = (int) position.getY() + (int) rectOffset.getY();
    }

    @Override
    public void render(Graphics g) {

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void destroy(DynamicObject object){
        state.getDynamicObjectsList().remove(object);
        System.out.println("Destroy " + this);
    }

    protected Vector2D getCenter(){
        return new Vector2D(position.getX() + (double) width /2, position.getY() + (double) height /2);
    }
}