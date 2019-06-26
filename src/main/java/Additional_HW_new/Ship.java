package Additional_HW_new;

import java.util.HashSet;

public class Ship implements Runnable{
    private static int ships_count = 0;
    private Path path;
    private Path path1;
    private Path path2;
    private String name;
    private int capacity;
    private int cargo;
    HashSet<String> in;
    HashSet<String> out;
    private int portsCount;
    String cargoType;

    Ship(Path path, Path path1, Path path2, int capacity, int portsCount) {
        this.cargo = 0;
        this.path = path;
        this.path1 = path1;
        this.path2 = path2;
        ships_count++;
        this.name = "Корабль №" + ships_count;
        this.capacity = capacity;
        this.in = new HashSet<>();
        this.out = new HashSet<>();
        this.portsCount = portsCount;
        this.cargoType = "";
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
            while (true) {
                path.getSwims().get(0).go(this);
                while (this.cargo == 0 && out.size()<portsCount) {
                    for (int i = 0; i < path1.getSwims().size(); i++) {
                        path1.getSwims().get(i).go(this);
                    }
                }
                path.getSwims().get(0).go(this);
                while (this.cargo != 0 && in.size()<portsCount){
                    for (int i = 0; i < path2.getSwims().size(); i++) {
                        path2.getSwims().get(i).go(this);
                    }
                }
                if (in.size()==portsCount || out.size()==portsCount) break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
