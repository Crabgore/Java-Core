package Additional_HW_new;

import java.util.concurrent.Semaphore;

public class Tunnel extends Swim {
    private Semaphore smp = new Semaphore(2);

    @Override
    public void go(Ship s) {
        try {
            smp.acquire();
            System.out.println(s.getName() + " готовится зайти в пролив");
            System.out.println(s.getName() + " зашёл в пролив");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(s.getName() + " вышел из пролива");
            smp.release();
        }
    }
}
