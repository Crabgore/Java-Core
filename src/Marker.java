public class Marker {
    private Object o;
    private int id;
    private String type;

    public Marker(Object o) {
        this.o = o;
        this.id = this.hashCode();
        this.type = o.getClass().getTypeName();
    }

    public Object getO() {
        return o;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
