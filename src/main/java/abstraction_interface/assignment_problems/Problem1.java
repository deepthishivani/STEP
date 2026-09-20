package abstraction_interface.assignment_problems;

abstract class Shape {
    private static int count = 0;
    private final String shapeId;

    Shape() {
        shapeId = "SH-" + (++count);
    }

    public abstract double calculateArea();

    void scale(double factor) {
    }

    void scale(double xFactor, double yFactor) {
        scale(xFactor);
        scale(yFactor);
    }

    String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    void scale(double factor) {
        radius *= factor;
    }

    void scale(double xFactor, double yFactor) {
        radius *= Math.sqrt(xFactor * yFactor);
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    public double calculateArea() {
        return side * side;
    }

    void scale(double factor) {
        side *= factor;
    }

    void scale(double xFactor, double yFactor) {
        side *= Math.sqrt(xFactor * yFactor);
    }
}

public class Problem1 {
    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        System.out.printf("%.2f%n", c.calculateArea());
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);
    }
}
