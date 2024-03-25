package Assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    //Objects
    public static BufferedImage player;
    public static BufferedImage laserBlue;

    //FX
    public static BufferedImage speedFX;

    public static void init() throws IOException {
        player = AssetsLoader.imageLoader("/Res/Images/PNG/playerShip1_blue.png");
        speedFX = AssetsLoader.imageLoader("/Res/Images/PNG/Effects/fire08.png");
        laserBlue = AssetsLoader.imageLoader("/Res/Images/PNG/Lasers/laserBlue01.png");
    }
}
