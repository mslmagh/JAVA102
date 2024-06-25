public class NullableInteger {

    private final Integer value;

    NullableInteger(Integer k) {
        this.value = k;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isNull() {
        return value == null;
    }

    public void run() {
        if (value == null) {
            System.out.println("Value is null");
        } else {
            System.out.println("Value is: " + getValue());
        }
    }
}
