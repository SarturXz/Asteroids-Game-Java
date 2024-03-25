package Assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class AssetsLoader {
    public static BufferedImage imageLoader(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(AssetsLoader.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
