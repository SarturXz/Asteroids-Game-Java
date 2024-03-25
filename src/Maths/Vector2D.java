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
        double yy = Math.sin(angle) * getMagnitude();
        double xx = Math.cos(angle) * getMagnitude();

        return new Vector2D(xx, yy);
    }

    public Vector2D scale(double value) {
        return new Vector2D(x * value, y * value);
    }

    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public void limit(double value) {
        if (x > value) {
            x = value;
        }
        if (x < -value) {
            x = -value;
        }
        if (y > value) {
            y = value;
        }
        if (y < -value) {
            y = -value;
        }
    }

    public Vector2D normalize() {
        if (getMagnitude() != 0) {
            return new Vector2D(x / getMagnitude(), y / getMagnitude());
        }
        return new Vector2D(x, y);
    }

    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
