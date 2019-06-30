import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Tester {
    static void start(Class c) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        int priority = 1;
        int beforeCount = 0;
        int afterCount = 0;
        Object o = c.newInstance();
        Method[] methods = c.getDeclaredMethods();

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                beforeCount++;
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                afterCount++;
            }
        }
        if (beforeCount>1 || afterCount>1) throw new RuntimeException("");

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                m.invoke(o);
            }
        }
        do {
            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    if (m.getAnnotation(Test.class).priority() == priority) {
                        m.invoke(o);
                        priority++;
                    }
                }
            }
        } while (priority <= (methods.length - (beforeCount + afterCount)));

        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                m.invoke(o);
            }
        }
    }
}
