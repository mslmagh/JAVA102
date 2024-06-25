/**
 * @author Müslüm Agah
 * @since 02.05.2024
 */

public class HW06_20210808042 {
    public static void main(String[] args) throws Exception {

    }
}

interface Sellable {
    String getName();

    Double getPrice();
}

interface Package<T> {
    T extract(); // removes the item T (assigns null) and returns item only if it is not empty.
                 // Returns null if it is empty.

    boolean pack(T item); // puts an item T in if it is empty

    boolean isEmpty(); // checks if item T is null or not

}

interface Wrappable extends Sellable {
    // child interface of Sellable. This interface is used to restrict which items
    // can be put inside a Matroschka instance
}

abstract class Product implements Sellable {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName() + "(" + name + ", " + price + ")";
    }
}

class Mirror extends Product {
    private int width;
    private int height;

    public Mirror(int width, int height) {
        super("mirror", 2);
        this.width = width;
        this.height = height;

    }

    public int getArea() {
        return width * height;
    }

    @Override
    public Double getPrice() {
        return super.getPrice() * getArea();
    }

    public <T> T reflect(T item) {
        System.out.println("Reflected item: " + item);
        return item;
    }

}

class Paper extends Product implements Wrappable {

    private String note;

    public Paper() {
        super("Paper", 0.5);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

class Matroschka<T extends Wrappable> extends Product implements Wrappable, Package<T> {
    private T item;

    public Matroschka(T item) {
        super("Doll", 5 + (item != null ? item.getPrice() : 0));
        this.item = null;
        pack(item);

    }

    @Override
    public String toString() {
        return super.toString() + "{" + item + "}";
    }

    @Override
    public T extract() {
        T extracted = this.item;
        this.item = null;
        if (isEmpty()) {
            return null;
        } else
            return extracted;
    }

    @Override
    public boolean pack(T item) {
        if (this.item == null) {
            this.item = item;
            return true;
        } else
            return false;
    }

    @Override
    public boolean isEmpty() {
        return item == null;
    }
}

class Box<T extends Sellable> implements Package<T> {
    private T item;
    private boolean seal;

    public Box() {
        item = null;
    }

    public Box(T item) {
        this.item = item;
        seal = true;
        pack(item);
    }

    @Override
    public T extract() {
        T extracted = this.item;
        this.item = null;
        this.seal = false;
        if (isEmpty()) {
            return null;
        } else
            return extracted;

    }

    public boolean isSealBroken() {
        return seal == false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + item + "} Seal: " + seal;
    }

    @Override
    public boolean pack(T item) {
        if (isEmpty()) {
            this.item = item;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        return item == null;
    }

}
