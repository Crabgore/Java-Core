package Lesson_6.Additional_HW;

public class Main {
    public static void main(String[] args) {
        int[][] arr = new int[5][5];
        int min = 0;
        int max = arr.length-1;
        int count = 1;

        while (count <= arr.length*arr.length) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[min][i] == 0) {
                    arr[min][i] = count;
                    count++;
                }
            }
            for (int i = 0; i < arr.length ; i++) {
                if (arr[i][max] == 0){
                    arr[i][max] = count;
                    count++;
                }
            }
            for (int i = arr.length-1; i >= 0 ; i--) {
                if (arr[max][i] == 0){
                    arr[max][i] = count;
                    count++;
                }
            }
            for (int i = arr.length-1; i >= 0 ; i--) {
                if (arr[i][min] == 0 ){
                    arr[i][min] = count;
                    count++;
                }
            }
            min++;
            max--;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j<arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
