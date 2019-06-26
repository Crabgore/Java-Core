public class CheckArray {
    public static boolean checkArray(int[] arr) {
        boolean res = true;
        int ones = 0, fours = 0;
        for (int x : arr) {
            if (x == 1) ones++;
            else if (x == 4) fours++;
        }
        if (ones == 0 || fours == 0) res = false;

        return res;
    }
}
