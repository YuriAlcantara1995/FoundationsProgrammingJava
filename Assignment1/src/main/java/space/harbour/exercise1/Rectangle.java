package space.harbour.exercise1;

public class Rectangle implements Figure{
    protected double side1;
    protected double side2;

    public Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public double getPerimeter() {
        return 2 * (side1 + side2);
    }

    public double getArea() {
        return side1 * side2;
    }
}
