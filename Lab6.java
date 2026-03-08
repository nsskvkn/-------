import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Arrays;

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Triangle {
    Point p1, p2, p3;
    double sideA, sideB, sideC;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        
        this.sideA = calcDist(p1, p2);
        this.sideB = calcDist(p2, p3);
        this.sideC = calcDist(p3, p1);
    }

    private double calcDist(Point a, Point b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(p1, triangle.p1) &&
               Objects.equals(p2, triangle.p2) &&
               Objects.equals(p3, triangle.p3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2, p3);
    }

    public String getTriangleType() {
        double eps = 0.0001; 

        boolean aEqB = Math.abs(sideA - sideB) < eps;
        boolean bEqC = Math.abs(sideB - sideC) < eps;
        boolean aEqC = Math.abs(sideA - sideC) < eps;

        if (aEqB && bEqC) return "Рівносторонній";

        double[] sides = {sideA, sideB, sideC};
        Arrays.sort(sides); 
        if (Math.abs(Math.pow(sides[2], 2) - (Math.pow(sides[0], 2) + Math.pow(sides[1], 2))) < eps) {
            if (aEqB || bEqC || aEqC) return "Прямокутний та рівнобедрений";
            return "Прямокутний";
        }

        if (aEqB || bEqC || aEqC) return "Рівнобедрений";

        return "Різносторонній";
    }

    @Override
    public String toString() {
        return "Трикутник: " + p1 + ", " + p2 + ", " + p3;
    }
}

public class Lab6 {
    public static void main(String[] args) {
        Set<Triangle> triangles = new HashSet<>();

        triangles.add(new Triangle(new Point(0, 0), new Point(0, 3), new Point(4, 0)));
        triangles.add(new Triangle(new Point(0, 0), new Point(2, 0), new Point(1, Math.sqrt(3))));
        triangles.add(new Triangle(new Point(0, 0), new Point(4, 0), new Point(2, 5)));
        triangles.add(new Triangle(new Point(0, 0), new Point(5, 1), new Point(2, 6)));

        for (Triangle t : triangles) {
            System.out.println(t.toString() + " -> Тип: " + t.getTriangleType());
        }
    }
}