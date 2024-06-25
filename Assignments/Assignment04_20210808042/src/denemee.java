import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class denemee {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        HashMap<Integer, Double> grade = new HashMap<>();
        grade.put(1, 70.0);
        HashMap<Integer, Double> grade2 = new HashMap<>();
        grade2.put(2, 80.0);
        HashMap<String, HashMap<Integer, Double>> testHashMap = new HashMap<>();
        testHashMap.put("Fizik", grade);
        testHashMap.put("Kimya", grade2);
        if (testHashMap.containsKey("Fizik")) {
            System.out.println("Fizik dersi var");
            // course.replace(1, 80.0);
            for (Map.Entry m : testHashMap.get("Fizik").entrySet()) {
                // System.out.println(" Value: " + m.getValue());
                // testHashMap.get("Fizik").remove((int)m.getKey());
            }
        } else {
            System.out.println("Fizik dersi yok");
        }
        testHashMap.get("Fizik").put(2, 90.0);

        System.out.println(testHashMap);

        // for (Map.Entry m : grade.entrySet()) {
        // System.out.println(" Value: " + m.getValue());
        // }
    }
}
