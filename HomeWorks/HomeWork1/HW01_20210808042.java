/**
 * @author Müslüm Agah
 * @since 23.02.2024
 */
public class HW01_20210808042 {
    public static void main(String[] args) {
       
    }
}

class Stock {
    String symbol;
    String name;
    double previousClosingPrice;
    double currentPrice;

    Stock(String symbol, String name) {
        symbol = symbol.toUpperCase();
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPreviousClosingPrice() {
        return previousClosingPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setPreviousClosingPrice(double previousClosingPrice) {
        if (previousClosingPrice < 0) {
            System.out.println("ERROR: Invalid previous closing price (You can not set negative values).");
        } else {
            this.previousClosingPrice = previousClosingPrice;
        }

    }

    public void setCurrentPrice(double currentPrice) {
        if (currentPrice < 0) {
            System.out.println("ERROR: Invalid current price (You can not set negative values).");
        } else {
            this.currentPrice = currentPrice;
        }
    }

    double getChangePercent() {
        double percent = 0;
        percent = (this.currentPrice - this.previousClosingPrice) / this.previousClosingPrice * 100;
        return percent;
    }

    @Override
    public String toString() {

        return "[" + this.symbol + "]" + " - [" + this.name + "]: [" + this.currentPrice + "]";
    }
}

class Fan {
    private final int SLOW = 1;
    private final int MEDIUM = 2;
    private final int FAST = 3;
    private int speed;
    private boolean on;
    private double radius;
    private String color;

    Fan() {
        this.speed = SLOW;
        this.on = false;
        this.radius = 5;
        this.color = "Blue";
    }

    Fan(double radius, String color) {
        this();
        this.radius = radius;
        this.color = color;
    }

    public int getSpeed() {
        return speed;
    }

    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public void setSpeed(int speed) {
        if (isOn())
            this.speed = speed;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOn() {
        return this.on;
    }

    void change() {
        if (this.on == true)
            this.on = false;
        else if (this.on == false)
            this.on = true;
    }

    @Override
    public String toString() {
        if (this.on == true) {
            return "" + System.out.printf("Speed: %d, Radius: %.2f, Color: %s", this.speed, this.radius, this.color);
        } else {
            return "Fan is off";
        }
    }
}