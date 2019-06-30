package Additional_HW;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Main {

public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ArrayList hwCheck = FileChecker.fileChecker();
        WorkChecker.workChecker(hwCheck);
    }

}
