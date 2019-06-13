import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Task_1 {
    public static void main(String[] args) throws IOException {
        File file = new File("files/Task_1/file1.txt");

        FileInputStream fis = new FileInputStream(file);
        byte[] arr = new byte[100];
        int x;
        while ((x = fis.read(arr)) > 0){
            System.out.println(new String(arr, 0, x, "UTF-8"));
        }

        fis.close();
    }
}
