package Additional_HW_new;

public class Main {
    private static final int ships_count = 10;
    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel();
        Path tunnelPath = new Path(tunnel);
        Path departurePath = new Path(new DeparturePort("Порт отправления одежды", "cloth", 2700),
                new DeparturePort("Порт отправления еды", "food", 5900),
                new DeparturePort("Порт отправления топлива", "fuel", 8500));
        Path arrivalPath = new Path(new ArrivalPort("Порт поступления одежды", "cloth", 2700),
                new ArrivalPort("Порт поступления еды", "food", 5900),
                new ArrivalPort("Порт поступления топлива", "fuel", 8500));

        Ship[] ships = new Ship[ships_count];
        for (int i = 0; i < ships_count; i++) {
            ships[i] = new Ship(tunnelPath, departurePath, arrivalPath, 500, departurePath.getSwims().size());
        }

        for (Ship s : ships) {
            new Thread(s).start();
        }
    }
}
