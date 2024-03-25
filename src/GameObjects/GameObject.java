package GameObjects;

import Maths.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected BufferedImage texture;
    protected Vector2D position;
    protected Vector2D scale;

    public GameObject(Vector2D position, Vector2D scale, BufferedImage texture) {
        this.position = position;
        this.texture = texture;
        this.scale = scale;
    }

    public GameObject(Vector2D position, BufferedImage texture) {
        this.texture = texture;
        this.position = position;
        this.scale = new Vector2D(1, 1);
    }

    public GameObject(Vector2D position) {
        this.position = position;
        this.texture = null;
    }


    public abstract void update();

    public abstract void render(Graphics g);


    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getScale() {
        return scale;
    }

    public void setScale(Vector2D scale) {
        this.scale = scale;
    }
}
