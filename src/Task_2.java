import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Task_2 {
    public static void main(String[] args) throws IOException {
        ArrayList<InputStream> al = new ArrayList<>();
        al.add(new FileInputStream("files/Task_2/file1.txt"));
        al.add(new FileInputStream("files/Task_2/file2.txt"));
        al.add(new FileInputStream("files/Task_2/file3.txt"));
        al.add(new FileInputStream("files/Task_2/file4.txt"));
        al.add(new FileInputStream("files/Task_2/file5.txt"));
        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(al));

        byte[] arr = new byte[100];
        int x;
        while ((x = sis.read(arr)) > 0) {
            System.out.print(new String(arr, 0, x, "UTF-8"));
        }

        sis.close();
    }
}
