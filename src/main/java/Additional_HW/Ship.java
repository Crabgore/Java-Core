package Additional_HW;

import java.util.HashSet;

public class Ship implements Runnable{
    private static int ships_count = 0;
    private Path path;
    private String name;
    private int capacity;
    private int cargo;
    HashSet<String> in;
    HashSet<String> out;
    private int portsCount;

    Ship(Path path, int capacity, int portsCount) {
        this.cargo = 0;
        this.path = path;
        ships_count++;
        this.name = "Корабль №" + ships_count;
        this.capacity = capacity;
        this.in = new HashSet<>();
        this.out = new HashSet<>();
        this.portsCount = portsCount;
    }

    int getCargo() {
        return cargo;
    }

    int getCapacity() {
        return capacity;
    }

    void setCargo(int cargo) {
        this.cargo = cargo;
    }

    String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            while (in.size()<portsCount || out.size()<portsCount)
            for (int i = 0; i < path.getSwims().size(); i++) {
                path.getSwims().get(i).go(this);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
