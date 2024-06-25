import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lab11_20210808042 {
    private static final int LinkedHashMap = 0;

    public static void main(String[] args) throws Exception {
        Set<String> fruits = new HashSet<>();

        // fruits.add("Apple");
        // fruits.add("Banana");
        // fruits.add("Orange");

        // System.out.println(fruits);
        // fruits.remove("Apple");
        // System.out.println(fruits);
        // fruits.add("Apple");
        // System.out.println(fruits);

        // Set<String> otherFruits = new HashSet<>();
        // otherFruits.add("kiwi");
        // otherFruits.add("mango");
        // otherFruits.add("banana");
        // otherFruits.add("mango");
        // System.out.println(otherFruits);
        // fruits.addAll(otherFruits);
        // System.out.println(fruits);
        // fruits.removeAll(otherFruits);
        // System.out.println(fruits);
        // System.out.println(otherFruits);
        // otherFruits.retainAll(fruits);
        // System.out.println(otherFruits);
        // System.out.println(fruits);
        fruits = new TreeSet<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        System.out.println(fruits);

        Map<String, String> map = new HashMap<>();
        map.put("Elma", "Apple");
        map.put("Muz", "Banana");
        map.put("Portakal", "Orange");
        System.out.println(map.get("Elma"));
        System.out.println(map.get("Muz"));
        map.remove("Elma");
        System.out.println(map.keySet());
        System.out.println(map.values());
        for (var e : map.entrySet()) {
            System.out.println(e + " -> " + map.get(e));
            var key = e.getKey();
            var value = e.getValue();
            System.out.println(key + " -> " + value);
        }

        for (var key : map.keySet()) {
            System.out.println(key + " -> " + map.get(key));
        }
        map.putIfAbsent("4", "four");
        System.out.println(map);
        map.putIfAbsent("4", "four");
        System.out.println(map);

        map = new LinkedHashMap<>();
        map = new TreeMap<>();
        map.put("2", "two");
        TreeMap<Student, Course> treeMap = new TreeMap<>();
        Student s1 = new Student(5);
        Student s2 = new Student(6);
        Course c1 = new Course("a");
        Course c2 = new Course("b");
        Course c3 = new Course("c");
        Course c4 = new Course("d");
        treeMap.put(s1, c1);
        TreeSet<Course> treeSet = new TreeSet<>();
        treeSet.add(c2);
        treeSet.add(c4);
        treeMap.put(s1, c1);
        System.out.println(treeSet);

        TreeMap<String, Integer> wordFrequencies = new TreeMap<>();

        try (
                BufferedReader reader = new BufferedReader(new FileReader("src\\shakespeare.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

                for (String word : words) {
                    wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
            System.out.print("[" + entry.getKey() + "]" + ": " + entry.getValue() + ", ");
        }
        System.out.println("***************************************");
        // TreeMap<Integer, String> map2 = new TreeMap<>();
        // map2.put(1, "a");
        // map2.put(2, "b");
        // map2.put(3, "c");
        // System.out.println(map2);
        // reverseMap(map2);
        // System.out.println(map2);
        reverseMap(wordFrequencies);
        System.out.println(wordFrequencies);
    }

    public static <K, V> TreeMap<V, TreeSet<K>> reverseMap(TreeMap<K, V> map) {
        TreeMap<V, TreeSet<K>> reversedMap = new TreeMap<>();

        for (var entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            TreeSet<K> keys = reversedMap.getOrDefault(value, new TreeSet<K>());
            keys.add(key);
            reversedMap.put(value, keys);
        }
        return reversedMap;
    }

}

class Student implements Comparable<Student> {
    int id;

    public Student(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Student o) {
        return id;
    }

}

class Course implements Comparable<Course> {
    String name;

    public Course(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Course o) {
        return name.compareTo(o.name);
    }

}
