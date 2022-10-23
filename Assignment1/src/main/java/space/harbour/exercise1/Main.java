package space.harbour.exercise1;

import java.util.*;

public class Main {

    public static void printMenu(){
        System.out.println("Choose your option : ");
        System.out.println("1- Calculate Area and Perimeter of a rectangle");
        System.out.println("2- Calculate Area and Perimeter of a square");
        System.out.println("3- Calculate Area and Perimeter of a circle");
        System.out.println("4- Calculate Area and Perimeter of a triangle");
        System.out.println("5- Calculate Area and Perimeter of a rhombus");
        System.out.println("6- Calculate Area and Perimeter of a parallelogram");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printMenu();
        try {
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    double rectangleSide1;
                    System.out.println("Enter the length of the width: ");
                    rectangleSide1 = Double.parseDouble(scanner.nextLine());
                    double rectangleSide2;
                    System.out.println("Enter the length of the height: ");
                    rectangleSide2 = Double.parseDouble(scanner.nextLine());
                    if(rectangleSide1 < 0 || rectangleSide2 < 0) {
                        System.out.println("The lengths should be a positive number");
                        return;
                    }
                    Figure rectangle = new Rectangle(rectangleSide1,rectangleSide2);
                    System.out.println("Area of rectangle of sides " + rectangleSide1 + " and " + rectangleSide2 + ": " + rectangle.getArea());
                    System.out.println("Perimeter of rectangle of sides " + rectangleSide1 + " and " + rectangleSide2 + ": " + rectangle.getPerimeter());

                    break;
                case 2:
                    double side;
                    System.out.println("Enter the length of the side: ");
                    side = Double.parseDouble(scanner.nextLine());
                    if(side < 0) {
                        System.out.println("The length should be a positive number");
                        return;
                    }
                    Figure square = new Square(side);
                    System.out.println("Area of square of side " + side + ": " + square.getArea());
                    System.out.println("Perimeter of square of side " + side + ": " + square.getPerimeter());
                    break;
                case 3 :
                    double circleRadius;
                    System.out.println("Enter the length of the radius: ");
                    circleRadius = Double.parseDouble(scanner.nextLine());
                    if(circleRadius < 0) {
                        System.out.println("The length should be a positive number");
                        return;
                    }
                    Figure circle = new Circle(circleRadius);
                    System.out.println("Area of circle of radius " + circleRadius + ": " + circle.getArea());
                    System.out.println("Perimeter of circle of radius " + circleRadius + ": " + circle.getPerimeter());
                    break;
                case 4:
                    double side1;
                    System.out.println("Enter the length of the first side: ");
                    side1 = Double.parseDouble(scanner.nextLine());
                    double side2;
                    System.out.println("Enter the length of the second side: ");
                    side2 = Double.parseDouble(scanner.nextLine());
                    double side3;
                    System.out.println("Enter the length of the third side: ");
                    side3 = Double.parseDouble(scanner.nextLine());
                    if(side1 < 0 || side2 < 0 || side3 < 0) {
                        System.out.println("The lengths should be a positive number");
                        return;
                    }
                    List<Double> sides = new ArrayList<Double>(List.of(side1, side2, side3));
                    Collections.sort(sides);
                    if(sides.get(0) + sides.get(1) <= sides.get(2)) {
                        System.out.println("The lengths must satisfy the triangle inequality");
                        return;
                    }
                    Figure triangle = new Triangle(side1, side2, side3);
                    System.out.println("Area of triangle of sides " + side1 + ", " + side2 + " and " + side3 + ": " + triangle.getArea());
                    System.out.println("Perimeter of triangle of sides " + side1 + ", " + side2 + " and " + side3 + ": " + triangle.getPerimeter());
                    break;
                case 5 :
                    System.out.println("Enter the length of the side: ");
                    side = Double.parseDouble(scanner.nextLine());
                    double diagonal1;
                    System.out.println("Enter the length of the first diagonal: ");
                    diagonal1 = Double.parseDouble(scanner.nextLine());
                    double diagonal2;
                    System.out.println("Enter the length of the second diagonal: ");
                    diagonal2 = Double.parseDouble(scanner.nextLine());
                    if(side < 0 || diagonal1 < 0 || diagonal2 < 0) {
                        System.out.println("The lengths should be a positive number");
                        return;
                    }
                    Figure rhombus = new Rhombus(side, diagonal1, diagonal2);
                    System.out.println("Area of rhombus of side " + side + " and diagonals " + diagonal1 + " and " + diagonal2 + ": " + rhombus.getArea());
                    System.out.println("Perimeter of rhombus of side " + side + " and diagonals " + diagonal1 + " and " + diagonal2 + ": " + rhombus.getPerimeter());
                    break;
                case 6 :
                    System.out.println("Enter the length of the first side: ");
                    side1 = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter the length of the second side: ");
                    side2 = Double.parseDouble(scanner.nextLine());
                    double height;
                    System.out.println("Enter the length of the height: ");
                    height = Double.parseDouble(scanner.nextLine());
                    if(side1 < 0 || side2 < 0 || height < 0) {
                        System.out.println("The lengths should be a positive number");
                        return;
                    }
                    Figure parallelogram = new Parallelogram(side1, side2, height);
                    System.out.println("Area of parallelogram of sides " + side1 + " and " + side2 + ", and height " + height + ": " + parallelogram.getArea());
                    System.out.println("Area of parallelogram of sides " + side1 + " and " + side2 + ", and height " + height + ": " + parallelogram.getPerimeter());
                    break;
                default:
                    System.out.println("You should enter an integer from 1 to 6");
            }
        } catch (NumberFormatException e) {
            System.out.println("A number was expected");
        }




    }
}