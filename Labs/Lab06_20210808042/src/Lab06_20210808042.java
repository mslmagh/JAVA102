import java.util.ArrayList;
import java.util.Collections;

public class Lab06_20210808042 {
    public static void main(String[] args) throws Exception {
        
    }
}

interface colorable {
    public static final int RED = 255;
    public static final int GREEN = 255;
    int BLUE = 255;

    int getRed();

}

interface Electric {
    int getCurrentBattery();

    void chargeBattery(int a);

}

interface Combustion {
    void refuel();
}

interface Rentable {
    Rentable rentOut(Gallery gallery);

    Rentable returnVehicle(Gallery gallery);
}

interface Diesel extends Combustion, Rentable {
    void cleanDieselFilter();
}

abstract class Vehicle {
    private String model;
    private int year;

    public Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public abstract void startEngine();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " model: " + model;
    }
}

abstract class Aircraft extends Vehicle {
    public Aircraft(String model, int year) {
        super(model, year);
    }

    public abstract void fly();
}

abstract class Ship extends Vehicle {
    public Ship(String model, int year) {
        super(model, year);
    }

    public abstract void sail();
}

abstract class Car extends Vehicle implements Comparable<Car> {
    private int horsepower;

    public Car(String model, int year, int horsepower) {
        super(model, year);
        this.horsepower = horsepower;
    }

    public void drive() {
        System.out.println("Driving " + this.toString() + " with horsePower" + horsepower);
    }

    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public int compareTo(Car other) {
        return this.getHorsepower() - other.getHorsepower();
    }
}

class Tesla extends Car implements Electric, Rentable {
    private int currentBattery;

    public Tesla(String model, int year, int horsepower) {
        super(model, year, horsepower);
    }

    @Override
    public void chargeBattery(int amount) {

    }

    @Override
    public int getCurrentBattery() {
        return currentBattery;
    }

    @Override
    public Rentable rentOut(Gallery gallery) {

        return this;
    }

    @Override
    public Rentable returnVehicle(Gallery gallery) {
        return null;
    }

    @Override
    public void drive() {
        System.out.print("Electrical ");
        super.drive();
    }

    @Override
    public void startEngine() {
        System.out.println("Started the engine of " + this.getClass());
    }
}

class Ford extends Car implements Combustion {
    public Ford(String model, int year, int horsepower) {
        super(model, year, horsepower);
    }

    @Override
    public void refuel() {
        System.out.println("refueling");
    }

    @Override
    public void drive() {
        System.out.print("Combustion ");
        super.drive();
    }

    @Override
    public void startEngine() {
        System.out.println("Started the engine of " + this.getClass());
    }
}

class Mercedes extends Car implements Electric, Diesel {
    int batteryCharge;

    public Mercedes(String model, int year, int horsepower) {
        super(model, year, horsepower);
    }

    @Override
    public void chargeBattery(int amount) {
        System.out.println("Charging " + this.toString());
        batteryCharge += amount;
    }

    @Override
    public int getCurrentBattery() {
        return batteryCharge;
    }

    @Override
    public void refuel() {
        System.out.println("refueling " + this);
    }

    @Override
    public Rentable rentOut(Gallery gallery) {
        gallery.addElectricCar(this);
        return this;
    }

    @Override
    public Rentable returnVehicle(Gallery gallery) {
        return null;
    }

    @Override
    public void cleanDieselFilter() {
        System.out.println("Uses clean diesel");
    }

    @Override
    public void startEngine() {
        System.out.println("Started the engine of " + this.getClass());
    }

}

class Gallery {
    private ArrayList<Combustion> combustionCars;
    private ArrayList<Electric> electricCars;

    public Gallery() {
        combustionCars = new ArrayList<>();
        electricCars = new ArrayList<>();
    }

    public void addCar(Car car) {
        if (car instanceof Combustion) {
            addCombustionCar((Combustion) car);
        }
        if (car instanceof Electric) {
            addElectricCar((Electric) car);
        }
    }

    public void addCombustionCar(Combustion car) {
        combustionCars.add(car);
    }

    public void addElectricCar(Electric car) {
        electricCars.add(car);
    }

    public void displayRentableCars() {
        ArrayList<Car> rentableCars = new ArrayList<>();
        // rentableCars.addAll((Collection<? extends Rentable>) electricCars);
        // rentableCars.addAll((Collection<? extends Rentable>) combustionCars);
        // Collections.sort(rentableCars, (car1, car2) -> car1.getModel().compareTo(car2.getModel()));
        Collections.sort(rentableCars);

        
        for (Combustion car : combustionCars) {
           if(car instanceof Rentable){
            rentableCars.add((Car)car);
           }
        }
        for (Electric car : electricCars) {
           if(car instanceof Electric){
            rentableCars.add((Car)car);
           }
        }

        for (Car car : rentableCars) {
            System.out.println(car);
        }
    }
}