package Maths;

import java.util.Random;

public class RNG {
    public static int randRange(int min, int max){
        Random r = new Random();
        return min + r.nextInt(max - min + 1);
    }
}
