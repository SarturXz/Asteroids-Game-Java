package GameObjects;

import  Assets.Assets;

import java.awt.image.BufferedImage;

public enum Size {
    BIG(2, Assets.meteorsMed),
    MED(2, Assets.meteorsSmall),
    SMALL(2, Assets.meteorsTiny),
    TINY(0, null);

    public int quantity;
    public BufferedImage[] texture;

    private Size(int quantity, BufferedImage[] texture){
        this.quantity = quantity;
        this.texture = texture;
    }

    //Meteor propierties
    public static final double METEOR_VEL = 2.0;
}
