import java.lang.foreign.Arena;
import java.time.chrono.IsoChronology;

public class Lab02_20210808042 {
    public static void main(String[] args) throws Exception {
        // Rectangle rect = new Rectangle(3, 6);
        // Rectangle rect2 = new Rectangle(4, 10);
        // System.out.printf("Rectangle, area: %.2f, perimeter: %.2f", rect.getArea(),
        // rect.getPerimeter());
        // rect.getArea();
        // rect.getPerimeter();
        Car car = new Car("Black", "Mustang");
        Car car2 = new Car("Yellow", "Ferrari");
        car.Start();
        car.accelerate(5);
        car.accelerate(-3);
        car.accelerate();
        Car[] galeryCar = new Car[] { car, car2 };

    }
}

class Rectangle {

    private double length;
    private double width;

    public void setLength(double length) {
        if (length > 0)
            this.length = length;
        else
            throw new IllegalArgumentException("ERROR: illegal argument exception");
    }

    public void setWidth(double width) {
        if (width > 0)
            this.width = width;
        else
            throw new IllegalArgumentException("ERROR: illegal argument exception");
    }

    Rectangle(double length, double width) {
        setLength(length);
        setWidth(width);
    }

    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return String.format("Rectangle, length: %.2f, width: %.2f\n", length, width);
    }
}

class Car {
    private String color;
    private String model;
    private int speed;
    private boolean isOn = false;
    private int acceleration;

    public Car(String color, String model) {
        this.color = color;
        this.model = model;
        setAcceleration(acceleration);
    }

    public Car(String color, String model, int acceleration) {
        this.color = color;
        this.model = model;
        setAcceleration(5);
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public void Start() {
        if (!isOn) {
            isOn = !isOn;
            System.out.printf(" %s %s is starting...\n", color, model);
            accelerate();
        } else {
            System.out.println("Car is already running.\n");
        }
    }

    public void Stop() {
        if (isOn) {
            speed = 0;
            isOn = !isOn;
            System.out.printf(" %s %s is stopped\n", color, model);
            speed -= speed;

        } else {
            System.out.println("Car is already stopped!\n");
        }
    }

    public void accelerate(int acceleration) {
        setAcceleration(acceleration);
        accelerate();
    }

    public void accelerate() {
        if (isOn) {
            speed += acceleration;
            if (speed < 0) {
                System.out.println("The car is stopped.");
            } else
                System.out.printf("%s %s have the speed %d\n", color, model, speed);

        } else {
            System.out.println("Car is not running");
        }
    }

    @Override
    public String toString() {
        String str1 = String.format("%s %s", color, model);
        String str2 = str1 + String.format("is running has speed of %d\n ");

        return isOn ? str2 : str1;
    }

}

class Person {
    private String name;
    private Car car;
    private Wallet wallet;
    private Brain brain;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Car car, Wallet wallet) {
        this.name = name;
        this.car = car;
        this.wallet = wallet;
    }

    public String getName() {
        return name;
    }

    boolean think() {

        return true;
    }

    void drive() {

    }

    void buy(Item item) {

    }

}

class Wallet {
    private String color;
    private int amount;

    public Wallet(String color) {
        this.color = color;
    }

    public Wallet(String color, int amount) {
        this.color = color;
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }

    void addMoney(int money) {

    }

    void removeMoney(int money) {

    }

    @Override
    public String toString() {

        return super.toString();
    }
}

class Brain {
    private String size;

}

class Item {

}
