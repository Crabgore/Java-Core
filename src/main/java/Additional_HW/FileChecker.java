package Additional_HW;

import java.io.File;
import java.util.ArrayList;

public class FileChecker {
    private static ArrayList<String> hwNames = new ArrayList<>();

    public static ArrayList fileChecker(){
        File file = new File("E:/Homeworks");
        String[] strings = file.list();
        for (String s: strings) {
            String[] mass = s.split("\\.");
            if (mass[1].equalsIgnoreCase("class")){
                hwNames.add(mass[0]);
            }
        }
        return hwNames;
    }
}
