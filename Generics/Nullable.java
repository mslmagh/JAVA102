import javax.swing.SwingWorker.StateValue;

public class Nullable <T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public Nullable(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
    
    public boolean isNull() {
        return value == null;
    }

    public void run() {
        if (isNull() == true) {
            System.out.println("Value is null");
        } else {
            System.out.println("Value is: " + getValue());
        }
    }
}
