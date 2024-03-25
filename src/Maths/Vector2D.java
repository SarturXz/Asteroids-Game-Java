package Maths;

public class Vector2D {
    private double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D setDirection(double angle) {
        double magnitude = getMagnitude();
        double yy = Math.sin(angle) * magnitude;
        double xx = Math.cos(angle) * magnitude;

        return new Vector2D(xx, yy);
    }

    public Vector2D scale(double value) {
        return new Vector2D(x * value, y * value);
    }

    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public Vector2D limit(double value) {
        if (getMagnitude() > value){
            return this.normalize().scale(value);
        }

        return this;
    }

    public Vector2D normalize() {
        double magnitude = getMagnitude();

        if (magnitude != 0) {
            return new Vector2D(x / magnitude, y / magnitude);
        }
        return new Vector2D(x, y);
    }

    public String toString() {
        return "x:" + x + ", y:" + y;
    }
}