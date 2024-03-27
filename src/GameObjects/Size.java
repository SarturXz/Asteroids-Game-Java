package GameObjects;

import  Assets.Assets;

import java.awt.image.BufferedImage;

public enum Size {
    BIG(2, Assets.meteorsBig),
    MED(2, Assets.meteorsMed),
    SMALL(2, Assets.meteorsSmall),
    TINY(0, Assets.meteorsTiny);

    public int quantity;
    public BufferedImage[] texture;

    private Size(int quantity, BufferedImage[] texture){
        this.quantity = quantity;
        this.texture = texture;
    }

    //Meteor propierties
    public static final double METEOR_VEL = 2.0;
}
