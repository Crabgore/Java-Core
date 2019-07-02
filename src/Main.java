import java.util.AbstractList;
import java.util.ArrayList;

public class Main {
    // Создаю саму коллекцию, чей размер нам надо вычислить.
    private static AbstractList<Object> queue = new ArrayList<>();
    private static int items = (int) (Math.random()*100);

    public static void main(String[] args) {
        // Наполняю коллекцию рандомным количеством объектов.
        for (int i = 0; i < items; i++) {
            queue.add(new Object());
        }

        PopPush(queue);
    }

    private static void PopPush(AbstractList<Object> al){
        int count = 1;
        Marker m = new Marker(al.get(0));
        al.add(m);
        al.remove(0);
        while (al.get(0).hashCode() != m.getId()) {
            al.add(al.get(0));
            al.remove(0);
            count++;
        }
        System.out.println("В коллекции " + count + " элемента(ов).");
    }
}
