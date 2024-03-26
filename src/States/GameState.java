package States;

import Assets.Assets;
import Collisions.CollisionChecker;
import GameObjects.DynamicObject;
import GameObjects.Meteor;
import GameObjects.Player;
import GameObjects.Size;
import Main.Window;
import Maths.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameState {
    private final Player player;
    private final ArrayList<DynamicObject> dynamicObjects = new ArrayList<>();
    private int meteors = 1;

    public GameState() {
        player = new Player(new Vector2D(), new Vector2D(0.5, 0.5), new Vector2D(0, 0), 8, Assets.player, this);
        player.setPosition(new Vector2D(((double) Window.WIDTH / 2) - ((double) player.getWidth() / 2), ((double) Window.HEIGHT / 2) - (double) (player.getHeight()) / 2));
        dynamicObjects.add(player);

        startWave();
    }

    private void startWave(){
        double x, y;

        for (int i = 0; i < meteors; i++) {
            x = i % 2 == 0 ? Math.random() * Window.WIDTH  : 0;
            y = i % 2 == 0 ? 0 : Math.random() * Window.HEIGHT;

            BufferedImage texture = Assets.meteorsBig[(int) (Math.random() * Assets.meteorsBig.length)];
            int speed = 2;
            dynamicObjects.add(new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(1, 1),
                    new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
                    speed * Math.random() + 1,
                    this,
                    texture,
                    Size.BIG
            ));
        }

        meteors++;
    }

    public void update() {
        for (int i = 0; i < dynamicObjects.size(); i++) {
            dynamicObjects.get(i).update();
        }

        for (int i = 0; i < dynamicObjects.size(); i++) {
            if (dynamicObjects.get(i) instanceof Meteor){
                if (CollisionChecker.checkCollision(player, dynamicObjects.get(i)))
                    System.out.println("Collision");
                return;
            }
        }
        startWave();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for (DynamicObject dynamicObject : dynamicObjects) {
            dynamicObject.render(g);
            CollisionChecker.debugCollisionMask(dynamicObject, g);
        }
    }

    public ArrayList<DynamicObject> getDynamicObjectsList() {
        return dynamicObjects;
    }
}