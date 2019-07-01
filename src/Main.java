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

    private static void PopPush(AbstractList al){
        //Создаю временный объект и счётчик.
        Object temp;
        int count = 0;
        //Создаю коллекцию "айдишников"
        ArrayList<Integer> ids = new ArrayList<>();
        while (true){
            //Получаю хэшкод первого объекта исходной коллекции.
            int id = al.get(0).hashCode();
            //Проверяю его наличие в коллекции "айдишников". Если его нет - перемещаю первый элемент исходной коллекции
            //в конец, увеличиваю счётчик и добавляю текущий элемент в коллекцию "айдишников".
            //Если первый объект исходной коллекции уже присутствует в коллекции "айдишников" - прерываю цикл.
            if (!ids.contains(id)){
                temp = queue.get(0);
                queue.remove(0);
                queue.add(temp);
                count++;
                ids.add(temp.hashCode());
            } else break;
        }
        System.out.println("В коллекции " + count + " элемента(ов).");
    }
}
