
public class Main {
    public static void main(String[] args) {

        // Integer a = 10;
        // String b = "Hello";
        // Double c = 3.14;
        // Test<Integer, String, Double> t = new Test<>(a, b, c);
        // t.showInfo();
        // String[] a = { "Java", "102", "Patika", "Dev" };
        // Integer[] b = { 1, 2, 3, 4 };
        // Character[] c = { 'J', 'a', 'v', 'a' };
        // printArray(c);
        // for (String i : a) {
        // System.out.println(i);
        // }
        // for (Integer i : b) {
        // System.out.println(i);
        // }
        // for (Character i : c) {
        // System.out.println(i);
        // }
        Student<String> strS = new Student<>();
        strS.insert("Hello");
    }

    public static <T> void printArray(T[] a) {
        for (T i : a) {
            System.out.println(i);
        }
    }

}
