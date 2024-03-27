package Maths;

public class Lerp {
    public static double Interpolate(double from, double to, double value){
        return from + value * (to - from);
    }
}
