public class Lab01_20210808042 {

    public static void main(String[] args) {
        Circle c1 = new Circle(5);
        Circle c2 = new Circle(10, "Blue");
        Circle c3 = new Circle();
        Circle[] circles = new Circle[5];
        circles[0] = c1;
        circles[1] = c2;
        c1.display(circles);
        c2.display(circles);
        System.out.println(c1.toString());
        System.out.println(c1.getArea());
        System.out.printf("Area of the circle %.2f", c1.getArea());
        System.out.println();
        System.out.println(c2.toString());
        System.out.printf("Area of the circle %.2f", c2.getArea());
        System.out.printf("Number of cirles: %d", c3.count);
        Employee employee = new Employee("Muslum", 1000, 2);

    }
}

class Employee {
    static int count;
    static String employees[];
    String name;
    double salary;
    int level;
    int id;

    Employee(String name, double salary, int level) {
        this.name = name;
        this.salary = salary;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    void promote(Employee employee) {

    }

    void demote(Employee employee) {

    }

    private static int genereateId(int level) {
        String random1 = "";
        for (int i = 0; i < 3; i++) {
            int random = (int) (Math.random() * 10);
            random1 += random;
        }
        return Integer.parseInt(level + random1);
    }
    @Override
    public String toString() {
        
        return "Muslum";
    }
   void doWork(){

    }
}

class Circle {
    int count = 0;
    int radius = 1;
    String color = "Red";
    // Color color1 = new Color();

    Circle(int radius, String color) {
        this(radius);
        // this.color1.name = color;
        this.color = color;
        count++;
    }

    Circle(int radius) {
        this.radius = radius;
    }

    Circle() {
        count++;
    }

    static void display(Circle[] circles) {
        for (Circle c : circles) {
            if (c != null) {
                System.out.println(c);
            }
        }
    }

    public String toString() {

        return String.format("Circle with %d radius " + "with %s color", radius, color);
    }

    double getArea() {
        return 2 * Math.PI * radius;
    }

}

 class Color{
 String name = "Blue";
 }
