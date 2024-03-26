package Assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {

    //Objects
    public static BufferedImage player;
    public static BufferedImage laserBlue;

    //--Meteors
    public static BufferedImage[] meteorsBig   =  new BufferedImage[4];
    public static BufferedImage[] meteorsMed   =  new BufferedImage[2];
    public static BufferedImage[] meteorsSmall =  new BufferedImage[2];
    public static BufferedImage[] meteorsTiny  =  new BufferedImage[2];

    //FX
    public static BufferedImage speedFX;

    public static void init() throws IOException {
        player = AssetsLoader.imageLoader("/Res/Images/PNG/playerShip1_blue.png");
        speedFX = AssetsLoader.imageLoader("/Res/Images/PNG/Effects/fire08.png");
        laserBlue = AssetsLoader.imageLoader("/Res/Images/PNG/Lasers/laserBlue01.png");

        for (int i = 0; i < meteorsBig.length; i++)
            meteorsBig[i] = AssetsLoader.imageLoader("/Res/Images/PNG/Meteors/meteorGrey_big" + (i+1) +".png");

        for (int i = 0; i < meteorsMed.length; i++)
            meteorsMed[i] = AssetsLoader.imageLoader("/Res/Images/PNG/Meteors/meteorGrey_med" + (i+1) +".png");

        for (int i = 0; i < meteorsSmall.length; i++)
            meteorsSmall[i] = AssetsLoader.imageLoader("/Res/Images/PNG/Meteors/meteorGrey_small" + (i+1) +".png");

        for (int i = 0; i < meteorsTiny.length; i++)
            meteorsTiny[i] = AssetsLoader.imageLoader("/Res/Images/PNG/Meteors/meteorGrey_tiny" + (i+1) +".png");
    }
}
