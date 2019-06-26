package Additional_HW;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeparturePort extends Swim{
    private Lock lock = new ReentrantLock();
    CyclicBarrier cb = new CyclicBarrier(2);
    private String name;
    private int goods;

    DeparturePort(String name, int goods) {
        this.name = name;
        this.goods = goods;
    }

    @Override
    public synchronized void go (Ship s) throws InterruptedException {
        if (this.goods>1 && s.getCargo() == 0) {
            lock.lock();
            System.out.println(s.getName() + " подходит к " + this.name);
            System.out.println(s.getName() + " загружается в " + this.name + " ...");
            while (this.goods > 0 && s.getCargo() < s.getCapacity()) {
                Thread.sleep(1000);
                this.goods -= 100;
                s.setCargo(s.getCargo() + 100);
            }
            System.out.println("В порту " + this.name + " осталось " + this.goods + " товаров");
            System.out.println(s.getName()+ " принял на борт " + s.getCargo() + " товаров");
            System.out.println(s.getName()+ " имеет на борту " + s.getCargo() + " товаров");
            System.out.println(s.getName() + " вышел из " + this.name);
            lock.unlock();
        }
        if (this.goods == 0) s.out.add(this.name);
    }
}
