package Additional_HW;

public class Main {
    private static final int ships_count = 10;
    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel();
        Path path = new Path(tunnel, new DeparturePort("Порт отправления одежды", 2700),
                new DeparturePort("Порт отправления еды", 5900), new DeparturePort("Порт отправления топлива", 8500),
                tunnel, new ArrivalPort("Порт прибытия одежды", 2700), new ArrivalPort("Порт прибытия еды", 5900),
                new ArrivalPort("Порт прибытия топлива", 8500));

        Ship[] ships = new Ship[ships_count];
        for (int i = 0; i < ships_count; i++) {
            ships[i] = new Ship(path,500, (path.getSwims().size()-2)/2);
        }

        for (Ship s : ships) {
            new Thread(s).start();
        }
    }
}
