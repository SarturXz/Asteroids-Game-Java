package States;

import Assets.Assets;
import GameObjects.DynamicObject;
import GameObjects.Player;
import Main.Window;
import Maths.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class GameState {
    private final ArrayList<DynamicObject> dynamicObjects = new ArrayList<>();

    public GameState() {
        Player player = new Player(new Vector2D(), new Vector2D(0.5, 0.5), new Vector2D(0, 0), 8, Assets.player, this);
        player.setPosition(new Vector2D(((double) Window.WIDTH / 2) - ((double) player.getWidth() / 2), ((double) Window.HEIGHT / 2) - (double) (player.getHeight()) / 2));
        dynamicObjects.add(player);
    }

    public void update() {
        for (int i = 0; i < dynamicObjects.size(); i++) {
            dynamicObjects.get(i).update();
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for (DynamicObject dynamicObject : dynamicObjects) {
            dynamicObject.render(g);
        }
    }

    public ArrayList<DynamicObject> getDynamicObjectsList() {
        return dynamicObjects;
    }
}