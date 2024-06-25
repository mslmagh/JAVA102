import java.util.ArrayList;
import java.util.Date;

public class Lab09_20210808042 {
    public static void main(String[] args) throws Exception {
        Item<Product> item = new Item<>(new Product());

        var x = item.getItem();
        
        ArrayList<String> strList = new ArrayList<>();

        Book book = new coloringBook(null, 0, null);
    }
}

class Item<T> {
    private T item;

    Item(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

}


interface Countable {
    int getItemCount();
}

interface IInventory<T extends Countable> {
    void addItem(T item);

    void removeItem(T item);

    T getItem(int index);
}

interface Rentable {
    void rentItem(Person renter, Date startDate, Date endDate);

    boolean isAvailable();
}

class Person {

    public int price;

}

abstract class Product implements Comparable<Person> {
    private String name;
    private int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(price, o.price);
    }

    @Override
    public String toString() {
        return name + " Dollar" + price;
    }
}

class Book {
    String author;
    int pages;

    public Book(String author, int pages) {
        this.author = author;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return super.toString() + " pages: " + pages + ", author: " + author;
    }
}

class coloringBook extends Book implements Rentable {
    String color;

    public coloringBook(String author, int pages, String color) {
        super(author, pages);
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + ", color: " + color;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void rentItem(Person renter, Date startDate, Date endDate) {

    }

}

abstract class Vehicle implements Comparable {
    String name;
    int price;

    Vehicle(String name, int price) {
        this.name = name;
        this.price = price;

    }

}

class Car extends Vehicle implements Rentable {
    Car(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean isAvailable() {
        return true;

    }

    @Override
    public void rentItem(Person renter, Date startDate, Date endDate) {

    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}

class Item<T extends Comparable<? super T> & Countable> implements Countable, Comparable<Item> {
    T item;
    int quantity;

    Item(T item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Item<t> o) {
        return this.item.compareTo(o.item);
    }

    @Override
    public int getItemCount() {
        return item.getItemCount();
    }

    @Override
    public String toString() {
        return item.toString() + " x" + quantity;

    }

}

class Inventory<T extends Item<?> & Countable> implements Countable, IInventory {
    ArrayList<T> inventory = new ArrayList<>();

    @Override
    public void addItem(Countable item) {
        inventory.add(item);
    }

    @Override
    public Countable getItem(int index) {
        return inventory.get(index);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (T t : inventory)
            count += t.getItemCount();
        return count;

    }

    @Override
    public void removeItem(Countable item) {
        inventory.remove(item);
    }
}
