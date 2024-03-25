package GameObjects;

import Maths.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class DynamicObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxVel;

    public DynamicObject(Vector2D position, Vector2D scale, Vector2D velocity, double maxVel, BufferedImage texture) {
        super(position, scale, texture);

        this.velocity = velocity;
        this.maxVel = maxVel;
        angle = 0;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}