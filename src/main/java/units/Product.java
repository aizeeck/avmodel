package units;

public class Product {

    private int id;
    private String name;
    private int consPerTurn;
    private int quantity;
    private double quality;
    private double primeCost;

    public Product(int id, String name, int consPerTurn, int quantity, double quality, double primeCost) {
        this.id = id;
        this.name = name;
        this.consPerTurn = consPerTurn;
        this.quantity = quantity;
        this.quality = quality;
        this.primeCost = primeCost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getConsPerTurn() {
        return consPerTurn;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getQuality() {
        return quality;
    }

    public double getPrimeCost() {
        return primeCost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", consPerTurn=" + consPerTurn +
                ", quantity=" + quantity +
                ", quality=" + quality +
                ", primeCost=" + primeCost +
                '}' + "\n";
    }
}
