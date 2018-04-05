public class Brick {
    private long id;
    private String material;

    public Brick(long id, String material) {
        this.id = id;
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public long getId() {
        return id;
    }
}
