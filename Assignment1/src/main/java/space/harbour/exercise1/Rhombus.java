package space.harbour.exercise1;

public class Rhombus extends Square {
    private double diagonal1;
    private double diagonal2;

    public Rhombus(double side, double diagonal1, double diagonal2) {
        super(side);
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }

    public double getArea() {
        return diagonal1 * diagonal2 / 2;
    }
}
