package Task_1AndTask_2;

import java.util.Arrays;

public class Task_1 {
    public static void main(String[] args) {
        Integer[] integers = {1,2,3,4,5,6,7,8,9,10};
        Double[] doubles = {1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0};
        String[] strings = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

        replaceElements(integers, 2, 6);
        replaceElements(doubles, 2, 6);
        replaceElements(strings, 2, 6);

        System.out.println(Arrays.toString(integers));
        System.out.println(Arrays.toString(doubles));
        System.out.println(Arrays.toString(strings));
    }

    public static void replaceElements(Object[] array, int elem1, int elem2) {
        Object temp = array[elem1-1];
        array[elem1-1] = array[elem2-1];
        array[elem2-1] = temp;
    }
}
