package Additional_HW;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

public class WorkChecker {
    public static void workChecker(ArrayList<String> hwNames) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String hwName : hwNames) {
            System.out.println("Проверяем файл " + hwName + ".class");

            Class c = URLClassLoader.newInstance(new URL[]{new File("E:/Homeworks").toURL()}).loadClass(hwName);
            Class[] paramTypesOne = new Class[]{int.class, int.class, int.class, int.class};
            Class[] paramTypesTwo = new Class[]{int.class, int.class};
            Object[] argumsOne = new Object[]{10, 10, 10, 10};
            Object[] argumsTwo = new Object[]{10, 5};

            Method m1 = c.getDeclaredMethod("calculate", paramTypesOne);
            m1.setAccessible(true);
            Method m2 = c.getDeclaredMethod("checkTwoNumbers", paramTypesTwo);
            m2.setAccessible(true);
            Method m3 = c.getDeclaredMethod("printIsPositive", int.class);
            m3.setAccessible(true);
            Method m4 = c.getDeclaredMethod("isNegative", int.class);
            m4.setAccessible(true);
            Method m5 = c.getDeclaredMethod("printWelocome", String.class);
            m5.setAccessible(true);
            Method m6 = c.getDeclaredMethod("isLeapYear", int.class);
            m6.setAccessible(true);

            int res = (int) m1.invoke(c, argumsOne);
            System.out.println("Проверяем метод " + m1.getName() + " с параметрами " + Arrays.toString(argumsOne));
            System.out.println("Результат: " + res);
            boolean b = (boolean) m2.invoke(c, argumsTwo);
            System.out.println("Проверяем метод " + m2.getName() + " с параметрами " + Arrays.toString(argumsTwo));
            System.out.println("Результат: " + b);
            System.out.println("Проверяем метод " + m3.getName() + " с параметрами " + 10);
            System.out.print("Результат: ");
            m3.invoke(c, 10);
            boolean b2 = (boolean) m4.invoke(c, -10);
            System.out.println("Проверяем метод " + m4.getName() + " с параметрами " + (-10));
            System.out.println("Результат: " + b2);
            System.out.println("Проверяем метод " + m5.getName() + " с параметрами " + "Преподаватель");
            System.out.print("Результат: ");
            m5.invoke(c, "Преподаватель");
            boolean b3 = (boolean) m6.invoke(c, 2019);
            System.out.println("Проверяем метод " + m6.getName() + " с параметрами " + 2019);
            System.out.println("Результат: " + b3);
        }
    }
}
