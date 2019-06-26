public class DivideArray {
    public int[] getArray(int[] arr) {
        int[] temp = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 4) {
                int elementsToCopy = arr.length - i - 1;
                temp = new int[elementsToCopy];
                System.arraycopy(arr, i + 1, temp, 0, elementsToCopy);
                break;
            }
        }
        return temp;
    }
}


