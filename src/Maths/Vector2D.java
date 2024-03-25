package Maths;

public class Vector2D {
    private double x, y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getX(){
        return this.x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getY(){
        return this.y;
    }

    public double getMagnitude(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D setDirection(double angle){
        double yy = Math.sin(angle) * getMagnitude();
        double xx = Math.cos(angle) * getMagnitude();

        return new Vector2D(xx, yy);
    }

    public String toString(){
        return "x: " + x + ", y: " + y;
    }
}
