import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Lab03_20210808042 {
    public static void main(String[] args) {
        Vehicle car = new Car("BMW", "M4", 2005, false, 1, true);
        Vehicle truck = new Truck("BMC", "SUPER1000", 1999, false, 0, true);
        Vehicle[] parkingLot = new Vehicle[] { car, truck };
        for (Vehicle vehicle : parkingLot) {
            vehicle.run();
        }
        Date date1 = new Date(1234567890000L);
        Date date2 = new Date(1234567890034234L);
        Customer customer = new Customer("muslum", "agah");
        RentalContrat rentalContract = new RentalContrat(customer, truck, date1, date2);
        System.out.println(rentalContract.calculateRentalPeriod());

    }
}

abstract class Vehicle {
    protected String brand;
    private String model;
    private int year;
    private boolean isRented;

    public Vehicle(String brand, String model, int year, boolean isRented) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.isRented = isRented;
    }

    public abstract void run();

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
    }

}

class Car extends Vehicle {
    private int passengerCapacity;
    private boolean autoTransmission;

    public Car(String brand, String model, int year, boolean isRented, int passengerCapacity,
            boolean autoTransmission) {
        super(brand, model, year, isRented);
        this.passengerCapacity = passengerCapacity;
        this.autoTransmission = autoTransmission;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public boolean isAutoTransmission() {
        return autoTransmission;
    }

    public void setAutoTransmission(boolean autoTransmission) {
        this.autoTransmission = autoTransmission;
    }

    @Override
    public void run() {
        System.out.println("Car is running");
    }
}

class Truck extends Vehicle {
    private int loadCapacity;
    private boolean fourWheelDrive;

    public Truck(String brand, String model, int year, boolean isRented, int loadCapacity, boolean fourWheelDrive) {
        super(brand, model, year, isRented);
        this.loadCapacity = loadCapacity;
        this.fourWheelDrive = fourWheelDrive;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public boolean isFourWheelDrive() {
        return fourWheelDrive;
    }

    public void setFourWheelDrive(boolean fourWheelDrive) {
        this.fourWheelDrive = fourWheelDrive;
    }

    @Override
    public void run() {
        System.out.println("Truck is running");
    }

}

class Motorcycle extends Vehicle {
    private int engineVolume;
    private boolean hasABS;

    public Motorcycle(String brand, String model, int year, boolean isRented, int engineVolume, boolean hasABS) {
        super(brand, model, year, isRented);
        this.engineVolume = engineVolume;
        this.hasABS = hasABS;
    }

    public int getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(int engineVolume) {
        this.engineVolume = engineVolume;
    }

    public boolean isHasABS() {
        return hasABS;
    }

    public void setHasABS(boolean hasABS) {
        this.hasABS = hasABS;
    }

    @Override
    public void run() {
        System.out.println("Motorcycle is running");
    }

}

class Customer {

    private String firstName;
    private String lastName;
    private int idNumber;
    private ArrayList<Vehicle> rentedVehicles;
    private ArrayList<RentalContrat> rentalContrats;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        setIdNumber(idNumber);
        rentedVehicles = new ArrayList<>();
        rentalContrats = new ArrayList<>();

    }

    public RentalContrat rent(Vehicle v, Date startDate, Date endDate) {

        rentedVehicles.add(v);
        rentalContrats.add(new RentalContrat(this, v, startDate, endDate));
        return rentalContrats.get(rentalContrats.size() - 1);
    }

    public Vehicle turnIn(RentalContrat contrat) {
        Vehicle v = contrat.getRentedVehicle();
        rentedVehicles.remove(v);
        Customer c = contrat.getCustomer();
        rentalContrats.remove(contrat);
        return v;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        Random rndm = new Random();
        idNumber = rndm.nextInt(1000, 9999);
        this.idNumber = idNumber;
    }

    public ArrayList<Vehicle> getRentedVehicles() {
        return rentedVehicles;
    }

    public void setRentedVehicles(ArrayList<Vehicle> rentedVehicles) {
        this.rentedVehicles = rentedVehicles;
    }

    public ArrayList<RentalContrat> getRentalContrat() {
        return rentalContrats;
    }

    public void setRentalContrat(ArrayList<RentalContrat> rentalContrat) {
        this.rentalContrats = rentalContrat;
    }

}

class RentalContrat {
    private Customer customer;
    private Vehicle rentedVehicle;
    private Date rentalStartDate;
    private Date rentalEntDate;

    public RentalContrat(Customer customer, Vehicle rentedVehicle, Date rentalStartDate, Date rentalEntDate) {
        this.customer = customer;
        this.rentedVehicle = rentedVehicle;
        this.rentalStartDate = rentalStartDate;
        this.rentalEntDate = rentalEntDate;
    }

    public long calculateRentalPeriod() {
        long diffInMillies = Math.abs(rentalEntDate.getTime() - rentalStartDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    public void setRentedVehicle(Vehicle rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public Date getRentalEntDate() {
        return rentalEntDate;
    }

    public void setRentalEntDate(Date rentalEntDate) {
        this.rentalEntDate = rentalEntDate;
    }

}