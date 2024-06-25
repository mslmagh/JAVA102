public class HW06_20230808075 {

    public static void main(String[] args) {
        
    }
}
interface Sellable {
    public String getName();
    public double getPrice();
}
interface Package<T> {
    public T extract();
     /*
     * – removes the item T (assigns null) and returns item only if it is not
empty. Returns null if it is empty.
     */
    public boolean pack(T item);
    /*
     *  puts an item T in if it is empty
     */
    public boolean isEmpty();
    /*
     * checks if item T is null or not
     */

}
interface Wrappable extends Sellable {
    /*
     * child interface of Sellable. This interface is used to restrict which items
can be put inside a Matroschka instance
     */
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
    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + name + ", " + price + ")";

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
    public double getPrice() {
        return 0.5f;
        /*
         * Override getPrice to scale with area
         */
    }

    public <T> T reflect(T item) {
        System.out.println(item);
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

    public Matroschka(String name, double price, T item) {
        super("Doll", price + 5);
        this.item = item;
        
        /*
         *  Constructor that takes item of type T packs it, and sends its parrent’s
        constructor “Doll” and 5 + the price of item (item can be null, becareful)
         */
    }
   @Override
   public String getName() {
       // TODO Auto-generated method stub
       return super.getName();
   }
   @Override
   public double getPrice() {
       // TODO Auto-generated method stub
       return super.getPrice();
   }
    @Override
    public String toString() {
        return super.toString() + "{" + item + "]";
    }
    
}
// Box <T extends Sellable> – implements Package with type T
class Box<T extends Sellable> implements Package<T> {
    private T item;
    private boolean seal;

    
    public Box() {
        item = null;
        
    }
    
    public Box(T item) {
        this.item = item;
        this.seal = true;
        pack(item);
        /*
         *  Constructor that takes item of type T packs it, sets seal to true
         */


    }
    public T extract() {
        this.item = null;
        this.seal = false;
        if(isEmpty()) {
            return null;
        }
        else return item;
    }
    @Override
    public boolean pack(T item) {
        if(isEmpty()) {
            this.item = item;
            seal=true;
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean isEmpty() {
       return item == null;
    }
    public boolean isSealBroken() {
        return seal == false;
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!         
    }
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + item + "} Seal: " + seal;
    }
}