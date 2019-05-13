package Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Words {
    public static void main(String[] args) {
        String[] words = {"Deer", "Banana", "Juice", "Soldier", "Snow", "Banana", "Sheep", "Owl", "Deer", "Car"};
        Check(words);

    }

    public static void Check (String[] words){
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer res = hm.getOrDefault(words[i], 0);
            hm.put(words[i], res == 0 ? 1 : res + 1);
        }
        Set<Map.Entry<String, Integer>> set = hm.entrySet();

        System.out.println("Массив состоит из уникальных слов:");
        for (Map.Entry<String, Integer> me: set) {
            System.out.println(me.getKey());
        }

        System.out.println();

        for (Map.Entry<String, Integer> me: set) {
            System.out.println("Слово " + me.getKey() + " встречается " + me.getValue() + " раз(а).");
        }
    }
}
