package Assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    public static BufferedImage player;

    public static void init() throws IOException {
        player = AssetsLoader.imageLoader("/Res/Images/PNG/playerShip1_blue.png");
    }
}
