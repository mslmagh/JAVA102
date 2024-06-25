import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.jar.Attributes.Name;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] arr = { 5, 4, 8, 2, 1, -10, 3, 5, 2 };
        // insertSorting(arr);
        bubbleSorting(arr);
        System.out.println("Insertion Sort: " + Arrays.toString(arr));
        Map<String, Integer> products = new HashMap<>();
        if(products.containsKey("products")){
            products.put("product", products.get("product") +1);
        }
        for(Entry<String, Integer> entry: products.entrySet())){
            String a = entry.getKey();
            Integer b = entry.getValue();
            // double total += a.getPrice * b;
        }
    }

    public static void bubbleSorting(int[] arr) {
        boolean swapSomething = true;
        while (swapSomething) {
            swapSomething = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapSomething = true;
                }
            }
        }

    }

    public static void insertSorting(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int a = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > a) {
                int temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

}

class Generics {

    public void print(ArrayList<?> list) {
        for (Object x : list)
            System.out.print(x + " ");
    }
}

class Generic2<T extends Generics> {
    public void print(ArrayList<T> list) {
        for (T x : list)
            System.out.print(x + " ");
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Food {
    private double calories;

    public Food(double calories, String name) {
        this.calories = calories;
    }

}

interface Ibatus {

    public abstract void breathing();

    public default void bre2(){
        System.out.println("");
    };
    public static void bre22(){
        System.out.println("efwwf");
    };

}
