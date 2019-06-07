package Task_1AndTask_2;

import java.util.ArrayList;
import java.util.Arrays;

public class Task_2<T> {

    private T[] obj;

    public Task_2(T[] obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        Integer[] integers = {1,2,3,4,5,6,7,8,9,10};
        Double[] doubles = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0};
        String[] strings = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

        Task_2<Integer> integerTask2 = new Task_2<>(integers);
        Task_2<Double> doubleTask2 = new Task_2<>(doubles);
        Task_2<String> stringTask2 = new Task_2<>(strings);

        ArrayList integersArrayList = arrayToArrayList(integerTask2);
        ArrayList doublesArrayList = arrayToArrayList(doubleTask2);
        ArrayList stringsArrayList = arrayToArrayList(stringTask2);

    }

    private static ArrayList arrayToArrayList(Task_2<?> array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
