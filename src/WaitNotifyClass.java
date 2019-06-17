public class WaitNotifyClass {
    private final Object object = new Object();
    private char currentLetter = 'A';

    public static void main(String[] args) {
        WaitNotifyClass waitNotifyClass = new WaitNotifyClass();

        Thread t1 = new Thread(() -> {
            try {
                waitNotifyClass.PrintA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                waitNotifyClass.PrintB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                waitNotifyClass.PrintC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private void PrintA() throws InterruptedException {
        synchronized (object){
            for (int i = 0; i < 6; i++) {
                while (currentLetter != 'A'){
                    object.wait();
                }
                System.out.print("A");
                currentLetter = 'B';
//                object.notify();
//                object.notify();
                object.notifyAll();
            }
        }
    }

    private void PrintB() throws InterruptedException {
        synchronized (object){
            for (int i = 0; i < 6; i++) {
                while (currentLetter != 'B'){
                    object.wait();
                }
                System.out.print("B");
                currentLetter = 'C';
//                object.notify();
//                object.notify();
                object.notifyAll();
            }
        }
    }

    private void PrintC() throws InterruptedException {
        synchronized (object){
            for (int i = 0; i < 6; i++) {
                while (currentLetter != 'C'){
                    object.wait();
                }
                System.out.print("C");
                currentLetter = 'A';
//                object.notify();
//                object.notify();
                object.notifyAll();
            }
        }
    }
}
