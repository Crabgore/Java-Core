package Additional_HW_new;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrivalPort extends Swim{
    private Lock lock = new ReentrantLock();
    private String name;
    private int goods;
    private int capacity;
    private String cargoType;

    ArrivalPort(String name, String cargoType, int goods) {
        this.name = name;
        this.goods = goods;
        this.cargoType = cargoType;
        this.capacity = 0;
    }

    @Override
    public void go (Ship s) throws InterruptedException {
        if (this.capacity < this.goods && s.getCargo() != 0 && s.cargoType.equals(this.cargoType)) {
            if (lock.tryLock()) {
                try {
                    System.out.println(s.getName() + " подходит к " + this.name);
                    System.out.println(s.getName() + " разгружается в " + this.name + " ...");
                    while (this.capacity < this.goods && s.getCargo() > 0) {
                        Thread.sleep(1000);
                        this.capacity += 100;
                        s.setCargo(s.getCargo() - 100);

                    }
                    System.out.println("В " + this.name + " стало " + this.capacity + " товаров");
                    System.out.println(s.getName() + " имеет на борту " + s.getCargo() + " товаров");
                    System.out.println(s.getName() + " вышел из " + this.name);
                } finally {
                    lock.unlock();
                }
            }
        }
        if (this.capacity == this.goods) s.in.add(this.name);
    }
}