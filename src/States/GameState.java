package States;

import Assets.Assets;
import GameObjects.Player;
import Maths.Vector2D;

import java.awt.*;

public class GameState {
    private final Player player;

    public GameState(){
        player = new Player(new Vector2D(375, 250), new Vector2D(0.5, 0.5), new Vector2D(0, 0), Assets.player);
    }

    public void update(){
        player.update();
    }

    public void draw(Graphics g){
        player.render(g);
    }
}
