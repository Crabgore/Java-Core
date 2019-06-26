package Additional_HW_new;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeparturePort extends Swim{
    private Lock lock = new ReentrantLock();
    private String name;
    private int goods;
    private String cargoType;

    DeparturePort(String name, String cargoType, int goods) {
        this.name = name;
        this.goods = goods;
        this.cargoType = cargoType;
    }

    @Override
    public void go (Ship s) throws InterruptedException {
        if (this.goods>1 && s.getCargo() == 0) {
            if (lock.tryLock()) {
                try {
                    System.out.println(s.getName() + " подходит к " + this.name);
                    System.out.println(s.getName() + " загружается в " + this.name + " ...");
                    while (this.goods > 0 && s.getCargo() < s.getCapacity()) {
                        Thread.sleep(1000);
                        this.goods -= 100;
                        s.setCargo(s.getCargo() + 100);
                        s.cargoType = this.cargoType;
                    }
                    System.out.println("В " + this.name + " осталось " + this.goods + " товаров");
                    System.out.println(s.getName() + " принял на борт " + s.getCargo() + " товаров");
                    System.out.println(s.getName() + " имеет на борту " + s.getCargo() + " товаров");
                    System.out.println(s.getName() + " вышел из " + this.name);
                } finally {
                    lock.unlock();
                }
            }
        }
        if (this.goods == 0) s.out.add(this.name);
    }
}
