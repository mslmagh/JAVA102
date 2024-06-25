package eski;

public class denemeee {

}

interface Common<T> {
    boolean isEmpty(); // Checks if the collection is empty

    T peek();
    // Retrieves, but does not remove, the head of this collection
    // returns the head element of the collection, or null if the collection is
    // empty

    int size();
    // Returns the number of elements in this collection.
    // Retrieves the priority value of this node.

}

interface Node<T> {
    static final int DEFAULT_CAPACITY = 2;

    void setNext(T item);

    // Sets the next node in the data structure. item - the next node
    T getNext();

    // Retrieves the next node in the data structure.
    double getPriority();
    // Retrieves the priority value of this node.

}

interface Package<T> {
    T extract();

    // Extracts an item from the package.
    double getPriority();

    // Retrieves the priority value of this package.
    boolean isEmpty();

    // Checks if the package is empty.
    boolean pack(T item);
    // Packs an item into the package.
    // Return true if the item was successfully packed
}

interface PriorityQueue<T> extends Common<T> {
    static final int FLEET_CAPACITY = 3;

    T dequeue();

    // Retrieves and removes the head of this queue, or returns null if this queue
    // is empty
    // Returns the head of this queue, or null if this queue is empty
    boolean enqueue(T item);
    // Inserts the specified element into this priority queue.
    // Parameters: item - the element to add

    // Methods inherited from interface Common Link icon
    // isEmpty, peek, size
}

interface Sellable {
    String getName();

    double getPrice();

}

interface Stack<T> extends Common<T> {
    T pop();
    // Removes and returns the item at the top of this stack.
    // Returns the item at the top of this stack, or null if the stack is empty

    boolean push(T item);
    // Pushes an item onto the top of this stack.
    // Returns true if the item was successfully pushed

    // Methods inherited from interface Common
    // isEmpty, peek, size
}

interface Wrappable extends Sellable {
    // Methods inherited from interface Sellable Link icon
    // getName, getPrice
}

class Box<T extends Sellable> implements Package<T> {
    private int distanceToAddress; // Teslimat adresine olan mesafe, öncelik hesaplamasında kullanılır.
    private T item; // Kutuda bulunan öğe
    private boolean seal; // Kutunun mührü

    Box() {
        /*
         * Boş, hiçbir öğe bulunmayan bir kutu oluşturur.
         */
        distanceToAddress = 0;
        item = null;
        seal = false;
    }

    Box(int distanceToAddress, T item) {
        this.distanceToAddress = distanceToAddress;
        this.item = item;
        this.seal = false;
        /*
         * Belirtilen öğe ve teslimat adresine olan mesafe ile bir kutu oluşturur.
         */
    }

    @Override
    public T extract() {
        /*
         * Kutudan öğeyi çıkarır ve döndürür. Bu metod çağrıldıktan sonra kutu
         * mühürlenir
         * ve boş olur.
         */
        T extractedItem = item;
        item = null;
        seal = false;
        return extractedItem;
    }

    @Override
    public double getPriority() {
        /*
         * Teslimat için kutunun önceliğini fiyat ve adrese olan mesafe temel alarak
         * hesaplar. Öncelik, daha değerli öğeler ve daha kısa mesafeler için daha
         * yüksektir.
         */
        if (distanceToAddress == 0)
            return item.getPrice() / 1;
        else
            return item.getPrice() / distanceToAddress;
    }

    @Override
    public boolean isEmpty() {
        /*
         * Kutunun boş olup olmadığını kontrol eder, yani içinde hiçbir öğe olup
         * olmadığını
         * belirler.
         */
        return item == null;
    }

    boolean isSealBroken() {
        return !seal;
    }

    public boolean pack(T item) {
        /*
         * Bir öğeyi kutuya paketler. Kutu zaten boşsa, yeni öğe bu kutuyu içerecektir.
         */
        if (isEmpty()) {
            this.item = item;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        /*
         * Kutunun içeriği ve mührünün durumu dahil olmak üzere bir dize temsilini
         * döndürür.
         */
        String sealStatus = seal ? "intact" : "broken";
        return "Box includes: " + (item != null ? item.getName() : "Nothing") + ", Seal Status: " + sealStatus;
    }
}

class CargoCompany {
    private CargoFleet queue; // Filoyu temsil eden bir öncelik kuyruğu.
    private Container stack; // Kutuları tutmak için bir yığın.

    CargoCompany() {
        /*
         * Boş bir konteynır yığını ve boş bir kargo filosu ile yeni bir CargoCompany
         * oluşturur.
         */
        queue = new CargoFleet();
        stack = new Container();
    }

    public <T extends Box<?>> void add(T box) {
        /*
         * Bir kutuyu konteynıra ekler. Yığın doluysa, konteynırı kuyruğa ekler. Filo
         * doluysa, filoyu gönderir.
         * 
         * T - Box türündeki kutunun anonim bir alt sınıfı
         * 
         * box - eklenmek istenen kutu
         */

        if (stack.push(box)) {
            System.out.println("Box added to the stack.");
            if (stack.size() >= Container.DEFAULT_CAPACITY) {
                queue.enqueue(stack);
                System.out.println("Stack enqueued to the fleet.");
                if (queue.size() >= CargoFleet.FLEET_CAPACITY) {
                    ship(queue);
                    System.out.println("Fleet shipped.");
                }
            }
        } else {
            System.out.println("Failed to add box to the stack.");
        }
    }

    private <T extends Box<?>> Sellable deliver(T box) {
        /*
         * Bir kutuyu teslim eder ve içeriğini çıkarır.
         * 
         * T - Box türündeki kutunun anonim bir alt sınıfı
         * 
         * box - teslim edilmek istenen kutu
         * 
         * İçeriğinizi alır.
         */
        if (!box.isEmpty()) {
            Sellable contents = box.extract();
            System.out.println("Delivered: " + contents.getName());
            return contents;
        } else {
            System.out.println("Box is empty, nothing to deliver.");
            return null;
        }
    }

    private void empty(Container container) {
        /*
         * Bir konteynırı boşaltır ve içindeki tüm kutuları teslim eder. Her kutu için,
         * onunla birlikte deliver metodunu çağırır.
         * 
         * container - boşaltılacak konteynır
         */
        while (!container.isEmpty()) {
            Box<?> box = container.pop();
            deliver(box);
        }
        System.out.println("Container emptied.");
    }

    private void ship(CargoFleet fleet) {
        /*
         * Tüm filoyu gönderir. Her konteynır için, onunla birlikte boşalt metodunu
         * çağırır.
         * 
         * fleet - gönderilecek kargo filosu
         */
        while (!fleet.isEmpty()) {
            Container container = fleet.dequeue();
            empty(container);
        }
        System.out.println("Fleet shipped.");
    }
}

class CargoFleet implements PriorityQueue<Container> {
    private Container head;
    private int size; // Filodaki konteynırların mevcut sayısı.
    static final int FLEET_CAPACITY = 3; // Bir öncelik kuyruğunun filo kapasitesi.

    CargoFleet() {
        /*
         * Boş bir CargoFleet oluşturur, hiçbir konteynır yoktur.
         */
    }

    public Container dequeue() {
        /*
         * Filodaki en yüksek önceliğe sahip konteynırı kaldırır ve döndürür.
         */
        if (!isEmpty()) {
            Container container = head;
            head = head.getNext();
            size--;
            return container;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        /*
         * Filonun boş olup olmadığını kontrol eder.
         */
        return size == 0;
    }

    public Container peek() {
        /*
         * En yüksek önceliğe sahip konteynırı alır, ancak kaldırmaz.
         */
        return head;
    }

    public int size() {
        /*
         * Filodaki konteynırların sayısını döndürür.
         */
        return size;
    }

    @Override
    public boolean enqueue(Container item) {
        /*
         * Bir konteynırı filoya öncelik sırasına göre ekler.
         */
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
    private Box<?>[] boxes; // Kutu yığınıyı temsil eden Box nesnelerinin bir dizisi.
    private Container next; // Bağlı bir yapıdaki bir sonraki konteynır.
    private double priority; // Konteynırın öncelik değeri, sıralama için kullanılır.
    // Her öğe konteynıra eklendiğinde, konteynırın önceliği öğenin önceliğiyle
    // artar.
    private int size; // Yığındaki mevcut kutu sayısı.
    private int top; // Yığındaki üst öğenin dizin numarası.
    static final int DEFAULT_CAPACITY = 2;

    Container() {
        /*
         * Varsayılan kapasiteye sahip boş bir Container oluşturur.
         */
        boxes = new Box[DEFAULT_CAPACITY];
    }

    public int compareTo(Container o) {
        /*
         * Diğer konteynır ile karşılaştırıldığında önceliğini belirtir.
         */
        return Double.compare(this.priority, o.priority);
    }

    public Container getNext() {
        /*
         * Bağlı yapıdaki bir sonraki konteynırı alır.
         */
        return next;
    }

    public double getPriority() {
        /*
         * Konteynırın öncelik değerini alır.
         */
        return priority;
    }

    public boolean isEmpty() {
        /*
         * Konteynırın boş olup olmadığını kontrol eder
         * 
         * (hiç kutu yoksa).
         */
        return size == 0;
    }

    public Box<?> peek() {
        /*
         * Yığının en üstündeki kutuyu alır, ancak kaldırmaz.
         */
        return boxes[top];
    }

    public Box<?> pop() {
        /*
         * Yığının en üstündeki kutuyu kaldırır ve döndürür.
         */
        if (!isEmpty()) {
            Box<?> poppedBox = boxes[top];
            boxes[top] = null;
            top--;
            size--;
            return poppedBox;
        } else {
            return null;
        }
    }

    @Override
    public boolean push(Box<?> item) {
        /*
         * Bir kutuyu yığının en üstüne iter.
         */
        if (top < DEFAULT_CAPACITY - 1) {
            top++;
            boxes[top] = item;
            priority += item.getPriority();
            size++;
            return true;
        } else {
            return false;
        }
    }

    public void setNext(Container item) {
        /*
         * Bağlı yapıdaki bir sonraki konteynırı belirler.
         */
        next = item;
    }

    public int size() {
        /*
         * Yığındaki kutuların sayısını döndürür.
         */
        return size;
    }

    @Override
    public String toString() {
        /*
         * Konteynırın önceliği ile birlikte bir dize temsili döndürür.
         */
        return "Container [priority=" + priority + "]";
    }
}

class Matroschka<T extends Wrappable> extends Product implements Wrappable, Package<T> {
    private T item; // Matruşka içindeki öğe

    public Matroschka(T item) {
        super("Matroschka", 5 + (item == null ? 0 : item.getPrice()));
        this.item = item;
        /*
         * Belirtilen öğeyi içeren yeni bir Matroschka oluşturur. Matroschka'nın fiyatı
         * 5 birimdir ve içerdiği öğenin fiyatına bağlı olarak hesaplanır.
         */
    }

    @Override
    public T extract() {
        /*
         * Matroschka içindeki öğeyi çıkarır ve döndürür. Bu metod çağrıldıktan sonra
         * Matroschka boş olur.
         */
        T extractedItem = item;
        item = null;
        return extractedItem;
    }

    @Override
    public double getPriority() {
        /*
         * Matroschka'nın öncelik değerini alır. Bu metod desteklenmediği için
         * UnsupportedOperationException fırlatır.
         */
        throw new UnsupportedOperationException("Getting the priority is not implemented");
    }

    @Override
    public boolean isEmpty() {
        /*
         * Matroschka'nın boş olup olmadığını kontrol eder, yani içinde hiçbir öğe olup
         * olmadığını belirler.
         */
        return item == null;
    }

    @Override
    public boolean pack(T item) {
        /*
         * Bir öğeyi Matroschka'ya paketler. Matroschka zaten boşsa, yeni öğe bu
         * Matroschka'yı içerecektir.
         */
        if (isEmpty()) {
            this.item = item;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        /*
         * Matroschka'nın içeriği ile birlikte bir dize temsilini döndürür.
         */
        return "Matroschka includes: " + (item != null ? item.getName() : "Nothing");
    }
}

class Paper extends Product implements Wrappable {
    private String note; // Kağıda yazılan not

    Paper() {
        // 0.5 birimlik bir başlangıç fiyatına sahip yeni bir Kağıt oluşturur.
        super("Paper", 0.5);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

class Mirror extends Product {
    private int height;
    private int width;

    public Mirror(int height, int width) {
        super("Mirror", 2);
        this.height = height;
        this.width = width;
        /*
         * Belirtilen yükseklik ve genişlikte yeni bir Ayna oluşturur. Aynanın fiyatı,
         * alanına bağlı olarak hesaplanır.
         */
    }

    public int getArea() {
        /*
         * Aynanın alanını hesaplar.
         */
        return width * height;
    }

    @Override
    public double getPrice() {
        /*
         * Aynanın alanına bağlı olarak fiyatını hesaplar. Ürün sınıfındaki getPrice
         * metodunu geçersiz kılar.
         */
        return getArea() * super.getPrice();
    }

    public <T> T reflect(T item) {
        /*
         * Verilen öğeyi yansıtarak ekrana basar. Herhangi bir türdeki öğeyi
         * işleyebilen genel bir metod.
         */
        System.out.println("Reflection: " + item);
        return item;
    }
}

abstract class Product implements Sellable {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        /*
         * Belirtilen ad ve fiyatla yeni bir Ürün oluşturur.
         */
    }

    @Override
    public String getName() {
        /*
         * Ürünün adını alır.
         */
        return name;
    }

    @Override
    public double getPrice() {
        /*
         * Ürünün fiyatını alır.
         */
        return price;
    }

    @Override
    public String toString() {
        /*
         * Ürünün dize temsilini döndürür. Dize, ürünün adını ve fiyatını içerir.
         */
        return "Product [name=" + name + ", price=" + price + "]";
    }
}
