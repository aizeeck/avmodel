package units;

public class Equipment implements Comparable {

    private long id;
    private long supplierId;
    private long availablle;
    private double price;
    private double quality;

    public Equipment(long id, long supplierId, long availablle, double price, double quality) {
        this.id = id;
        this.supplierId = supplierId;
        this.availablle = availablle;
        this.price = price;
        this.quality = quality;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != this.getClass()) {
            throw new IllegalArgumentException();
        }
        Equipment that = (Equipment) o;
        return (int) (this.quality - that.quality);
    }

    public long getId() {
        return id;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public long getAvailablle() {
        return availablle;
    }

    public double getPrice() {
        return price;
    }

    public double getQuality() {
        return quality;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", availablle=" + availablle +
                ", price=" + price +
                ", quality=" + quality +
                '}';
    }
}
