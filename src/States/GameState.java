package States;

import Assets.Assets;
import Collisions.CollisionChecker;
import GameObjects.DynamicObject;
import GameObjects.Meteor;
import GameObjects.Player;
import GameObjects.Size;
import Main.Window;
import Maths.RNG;
import Maths.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameState {
    private final Player player;
    private final ArrayList<DynamicObject> dynamicObjects = new ArrayList<>();
    private int meteors = 1;
    private ArrayList<Meteor> meteorsList = new ArrayList<>();

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
            Meteor meteor = new Meteor(
                    new Vector2D(x, y),
                    new Vector2D(1, 1),
                    new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
                    speed * Math.random() + 1,
                    this,
                    texture,
                    Size.BIG
            );
            dynamicObjects.addFirst(meteor);
            meteorsList.add(meteor);
        }

        meteors++;
    }

    public void update() {
        for (int i = 0; i < dynamicObjects.size(); i++) {
            dynamicObjects.get(i).update();
        }

        for (int i = 0; i < dynamicObjects.size(); i++) {
            if (dynamicObjects.get(i) instanceof Meteor){
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
            //CollisionChecker.debugCollisionMask(dynamicObject.getRect(), g);
        }
    }

    public ArrayList<DynamicObject> getDynamicObjectsList() {
        return dynamicObjects;
    }

    public ArrayList<Meteor> getMeteorsList() {
        return meteorsList;
    }

    public void divideMeteor(Meteor meteor){
        Size size = meteor.getSize();
        Size newSize;

        switch (size){
            case BIG:
                newSize = Size.MED;
                break;

            case MED:
                newSize = Size.SMALL;
                break;

            case SMALL:
                newSize = Size.TINY;
                break;

            default:
                return;
        }

        int speed = 2;
        for (int i = 0; i < size.quantity; i++) {
            int index = RNG.randRange(0, newSize.texture.length - 1);

            Meteor m = new Meteor(
                    meteor.getPosition(),
                    new Vector2D(1, 1),
                    new Vector2D(0, 1).setDirection(Math.random()*Math.PI*2),
                    speed * RNG.randRange(1, 3),
                    this,
                    newSize.texture[index],
                    newSize
            );
            dynamicObjects.addFirst(m);
            meteorsList.add(m);
        }
    }
}