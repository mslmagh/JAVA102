import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        ArrayList<Integer> b = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>();
        String[] strings = new String[2];
        Integer[] integers = new Integer[3];
        Scanner inp = new Scanner(System.in);
        /* for (int i = 0; i < 3; i++) {
            b.add(inp.nextInt());
        }
        System.out.println();
        for (int i = 0; i < 2; i++) {
            a.add(inp.next());
        } */
        System.out.println();
        for (int i = 0; i < 3; i++) {
            integers[i] = inp.nextInt();
        }
        for (int i = 0; i < 2; i++) {
            strings[i] = inp.next();
        }
        inp.close();
      
        printArray(strings);
        printArray(integers);
        
    }

    public static <T> void printList(ArrayList<T> arr) {
        for (T i : arr) {
            System.out.println("sddsds:" + i);
        }
    }
    public static <T> void printArray(T[] arr) {
        for (T i : arr) {
            System.out.println("sddsds:" + i);
        }
    }


}