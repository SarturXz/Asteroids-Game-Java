package GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Maths.Vector2D;

public abstract class DynamicObject extends GameObject {

    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;

    public DynamicObject(Vector2D position, Vector2D scale, Vector2D velocity, BufferedImage texture) {
        super(position, scale, texture);

        this.velocity = velocity;
        angle = 0;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }
}
