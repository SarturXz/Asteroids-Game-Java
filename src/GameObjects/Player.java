package GameObjects;

import Assets.Assets;
import Collisions.CollisionChecker;
import Input.Keyboard;
import Maths.Vector2D;
import States.GameState;
import Utils.Timer;

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
    private boolean accelerating = false;

    private Timer fireRate;

    public Player(Vector2D position, Vector2D scale, Vector2D velocity, double maxVel, BufferedImage texture, GameState state) {
        super(position, scale, velocity, maxVel, state, texture);
        fireRate = new Timer();

        this.setRect(new Rectangle(20, 20));
        this.rectOffset = new Vector2D((double) width / 2 - (double) getRect().width / 2, 10);
    }

    @Override
    public void update() {
        if (Keyboard.getKey(KeyEvent.VK_SPACE) && !fireRate.isRunning()){
            Vector2D v = heading.scale(width);
            state.getDynamicObjectsList().addFirst(new Laser(
                    getCenter().add(v),
                    new Vector2D(1, 0.75),
                    heading,
                    10,
                    angle,
                    state,
                    Assets.laserBlue
            ));

            fireRate.run(150);
        }

        if (Keyboard.getKey(KeyEvent.VK_LEFT) || Keyboard.getKey(KeyEvent.VK_A)) {
            angle -= DELTAANGLE;
        }
        if (Keyboard.getKey(KeyEvent.VK_RIGHT) || Keyboard.getKey(KeyEvent.VK_D)) {
            angle += DELTAANGLE;
        }

        if (Keyboard.getKey(KeyEvent.VK_UP) || Keyboard.getKey(KeyEvent.VK_W)) {
            acceleration = heading.scale(ACC);
            accelerating = true;
        } else {
            acceleration = (velocity.scale(-1).normalize()).scale(DACC);
            accelerating = false;
        }

        velocity = velocity.add(acceleration);
        velocity = velocity.limit(maxVel);

        heading = heading.setDirection(angle - (Math.PI / 2));

        position = position.add(velocity);

        if (position.getX() > Main.Window.WIDTH)  { position.setX(0); }
        if (position.getY() > Main.Window.HEIGHT) { position.setY(0); }
        if (position.getX() < -width)  { position.setX(Main.Window.WIDTH  - ((double) width / 2));  }
        if (position.getY() < -height) { position.setY(Main.Window.HEIGHT - ((double) height / 2)); }

        fireRate.update();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX() + width - Assets.speedFX.getWidth(),
                position.getY() + (double) height / 2);
        at1.rotate(angle, (double) -width / 6, 0);
        AffineTransform at2 = AffineTransform.getTranslateInstance(position.getX(),
                position.getY() + (double) height / 2);
        at2.rotate(angle, (double) width / 2, 0);

        if (accelerating){
            g2d.drawImage(Assets.speedFX, at1, null);
            g2d.drawImage(Assets.speedFX, at2, null);
        }

        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, (double) width / 2, (double) height / 2);
        at.scale(scale.getX(), scale.getY());
        g2d.drawImage(texture, at, null);
    }

    public Vector2D getCenter(){
        return new Vector2D(position.getX() + (double) width /2, position.getY() + (double) height /2);
    }
}