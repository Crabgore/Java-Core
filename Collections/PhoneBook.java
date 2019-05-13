package Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "+79211234567");
        phoneBook.add("Петров", "+79219874561");
        phoneBook.add("Сидоров", "+79214567812");
        phoneBook.add("Петров", "+79217891245");

        phoneBook.get("Петров");
    }
}

public class PhoneBook {
    HashMap<String, String> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add (String name, String number){
        phoneBook.put(number, name);
    }

    public void get (String name){
        Set<Map.Entry<String, String>> set = phoneBook.entrySet();
        for (Map.Entry<String, String> me: set) {
            if (me.getValue().equals(name)){
                System.out.println(me.getValue() +" " + me.getKey());
            }
        }
    }
}
