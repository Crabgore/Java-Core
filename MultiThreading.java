public class MultiThreading {

    static final int size = 10000000;
    static final int h = size / 2;
    static long res1;
    static long res2;

    public static void multiThreading1() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
           arr[i] = 1;
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long end = System.currentTimeMillis();

        res1 = end - start;

        System.out.println("У первого метода процесс занял " + res1 + " миллисекунд.");

    }

    public static void multiThreading2() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        long start = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        long end = System.currentTimeMillis();

        res2 = end - start;

        System.out.println("У второго метода процесс занял " + res2 + " миллисекунд.");
    }

    public static void main(String[] args) {
        multiThreading1();
        multiThreading2();

        System.out.println("Метод с двумя потоками справился с задачей в " + (res1/res2) + " раз быстрее.");
    }
}
