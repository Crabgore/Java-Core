package Exceptions;

public class MyArray {

    public static int getArray(String[][] str) throws MyArraySizeException, MyArrayDataException {
        int rows = str.length;
        int cols = str[0].length;
        if (rows != 4 || cols != 4) throw new MyArraySizeException("Размер массива должен быть строго 4х4!");

        int count = 0;
        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str.length; j++) {
                if (!str[i][j].matches("\\d+")) throw new MyArrayDataException("Символ или текст вместо числа в ячейке ", i, j);
                count += Integer.valueOf(str[i][j]);
            }
        }
        return count;
    }
}

class MyArraySizeException extends Exception {

    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {

    public MyArrayDataException (String message, int i, int j) {
        super(message + i + ", " + j);
    }
}
