
/**
 * @author Müslüm Agah
 * @since 13.05.2024
 */

public class HW07_20210808042 {
    public static void main(String[] args) {
        
    }
}

interface Common<T> {
    boolean isEmpty();

    T peek();

    int size();
}

interface Node<T> {
    static final int DEFAULT_CAPACITY = 2;

    void setNext(T item);

    T getNext();

    double getPriority();

}

interface Package<T> {
    T extract();

    double getPriority();

    boolean isEmpty();

    boolean pack(T item);
}

interface PriorityQueue<T> extends Common<T> {
    static final int FLEET_CAPACITY = 3;

    T dequeue();

    boolean enqueue(T item);
}

interface Sellable {
    String getName();

    double getPrice();

}

interface Stack<T> extends Common<T> {
    T pop();

    boolean push(T item);
}

interface Wrappable extends Sellable {

}

class Box<T extends Sellable> implements Package<T> {
    private int distanceToAddress;
    private T item;
    private boolean seal;

    Box() {

    }

    Box(int distanceToAddress, T item) {
        this.distanceToAddress = distanceToAddress;
        this.item = item;
        this.seal = false;
    }

    @Override
    public T extract() {
        T extractedItem = item;
        item = null;
        seal = false;
        return extractedItem;
    }

    @Override
    public double getPriority() {
        if (item == null)
            return 0;
        return item.getPrice() / (distanceToAddress + 1);
    }

    @Override
    public boolean isEmpty() {
        return item == null;
    }

    boolean isSealBroken() {
        return !seal;
    }

    @Override
    public boolean pack(T newItem) {
        if (item == null) {
            item = newItem;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String sealStatus = seal ? "intact" : "broken";
        return (item != null ? item.getName() : "Nothing") + ", Seal Status: " + sealStatus;
    }
}

class CargoCompany {
    private CargoFleet queue;
    private Container stack;

    public CargoCompany() {
        queue = new CargoFleet();
        stack = new Container();
    }

    public <T extends Box<?>> void add(T box) {
        if (!stack.push(box)) {
            if (!queue.enqueue(stack)) {
                ship(queue);
                queue = new CargoFleet();
                queue.enqueue(stack);
            }
            stack = new Container();
            stack.push(box);
        }
    }

    private <T extends Box<?>> Sellable deliver(T box) {
        if (!box.isEmpty()) {
            Sellable contents = box.extract();
            return contents;
        } else {
            return null;
        }
    }

    private void empty(Container container) {
        while (!container.isEmpty()) {
            deliver(container.pop());
        }
    }

    private void ship(CargoFleet fleet) {
        while (!fleet.isEmpty()) {
            empty(fleet.dequeue());
        }
    }
}

class CargoFleet implements PriorityQueue<Container> {
    private Container head;
    private int size;
    static final int FLEET_CAPACITY = 3;

    CargoFleet() {

    }

    public Container dequeue() {
        if (isEmpty())
            return null;
        Container temp = head;
        head = head.getNext();
        size--;
        return temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Container peek() {
        return head;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean enqueue(Container item) {
        if (size < FLEET_CAPACITY) {
            if (isEmpty() || item.getPriority() > head.getPriority()) {
                item.setNext(head);
                head = item;
            } else {
                Container current = head;
                while (current.getNext() != null && item.getPriority() <= current.getNext().getPriority()) {
                    current = current.getNext();
                }
                item.setNext(current.getNext());
                current.setNext(item);
            }
            size++;
            return true;
        } else {
            return false;
        }
    }
}

class Container implements Stack<Box<?>>, Node<Container>, Comparable<Container> {
    private Box<?>[] boxes;
    private Container next;
    private double priority;
    private int size;
    private int top;
    static final int DEFAULT_CAPACITY = 2;

    Container() {
        boxes = new Box<?>[DEFAULT_CAPACITY];
        top = -1;
    }

    public int compareTo(Container o) {
        return Double.compare(priority, o.priority);
    }

    public Container getNext() {
        return next;
    }

    public double getPriority() {
        return priority;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Box<?> peek() {
        if (isEmpty())
            return null;
        return boxes[top];
    }

    public Box<?> pop() {
        if (isEmpty())
            return null;
        Box<?> temp = boxes[top];
        boxes[top--] = null;
        size--;
        return temp;
    }

    @Override
    public boolean push(Box<?> item) {
        if (size == boxes.length)
            return false;
        boxes[++top] = item;
        size++;
        priority += item.getPriority();
        return true;
    }

    public void setNext(Container item) {
        next = item;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "priority=" + priority + '}';
    }
}

class Matroschka<T extends Wrappable> extends Product implements Wrappable, Package<T> {
    private T item;

    public Matroschka(T item) {
        super("Matroschka", 5 + (item == null ? 0 : item.getPrice()));
        this.item = item;
    }

    @Override
    public T extract() {
        T extractedItem = item;
        item = null;
        return extractedItem;
    }

    @Override
    public double getPriority() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return item == null;
    }

    @Override
    public boolean pack(T newItem) {
        if (item == null) {
            item = newItem;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "item=" + item + '}';
    }
}

class Mirror extends Product {
    private int height;
    private int width;

    public Mirror(int height, int width) {
        super("Mirror", 2);
        this.height = height;
        this.width = width;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public double getPrice() {
        return getArea() * 0.5;
    }

    public <T> T reflect(T item) {
        System.out.println(item);
        return item;
    }
}

class Paper extends Product implements Wrappable {
    private String note;

    Paper() {
        super("Paper", 0.5);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

abstract class Product implements Sellable {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "name= " + name + ", price= " + price;
    }
}