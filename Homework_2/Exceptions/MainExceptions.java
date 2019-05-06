package Exceptions;

public class MainExceptions{
    public static void main(String[] args) {
        String[][] str = new String[][] {{"12","16","4","9"}, {"16", "18", "20", "25"}, {"12","8","4","8"}, {"16", "18", "20", "25"}};

        try {
            System.out.println(MyArray.getArray(str));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }
}





