package space.harbour.exercise1;

public class Parallelogram extends Rectangle {
    private double height;

    public Parallelogram(double side1, double side2, double height) {
        super(side1, side2);
        this.height = height;
    }

    public double getArea() {
        return side1 * height;
    }
}
