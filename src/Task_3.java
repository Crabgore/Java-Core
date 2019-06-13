import java.io.*;
import java.util.Scanner;

public class Task_3 {
    public static void main(String[] args) throws IOException {
        long load = System.currentTimeMillis();

        FileReader fr = new FileReader("files/Task_3/IT.txt");
        RandomAccessFile raf = new RandomAccessFile("files/Task_3/IT.txt", "r");
        int pageSize = 1800;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Время загрузки программы составило: " + (System.currentTimeMillis() - load) + " миллисекунд");
        System.out.println("Введите номер страницы");
        int pageNumber = scanner.nextInt()-1;

        int count = 0;
        while (fr.read() != -1){
            count++;
        }

        while (true){
            if (pageNumber*pageSize>count) {
                System.out.println("В этой книге меньше страниц");
                pageNumber = scanner.nextInt() - 1;
            } else break;
        }

        long read = System.currentTimeMillis();

        raf.seek(pageNumber*pageSize);
        for (int i = 0; i < pageSize; i++) {
            System.out.print((char)raf.read());
        }

        System.out.println();
        System.out.println("Время чтения составило: " + (System.currentTimeMillis() - read) + " миллисекунд");

        fr.close();
        raf.close();
        scanner.close();
    }
}
