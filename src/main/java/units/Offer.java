package units;

/**
 * Created by aizeeck on 05.08.17.
 */
public class Offer implements Comparable {
    private int id;
    private int supplierId;
    private long availablle;
    private long inStock;
    private long orderedAmount;
    private double supplierPrice;
    private double price;
    private double quality;
    private double brand;


    public Offer(int id, int supplierId, long availablle, long inStock, double supplierPrice, double price, double quality, int orderedAmount) {
        this.id = id;
        this.supplierId = supplierId;
        this.availablle = availablle;
        this.inStock = inStock;
        this.supplierPrice = supplierPrice;
        this.price = price;
        this.quality = quality;
        this.orderedAmount = orderedAmount;
    }



    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", availablle=" + availablle +
                ", inStock=" + inStock +
                ", supplierPrice=" + supplierPrice +
                ", price=" + price +
                ", quality=" + quality +
                ", brand=" + brand +
                ", orderedAmount=" + orderedAmount+
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != this.getClass()) {
            throw new IllegalArgumentException();
        }
        Offer that = (Offer) o;
        return (int) (this.price - that.price);
    }

    public int getId() {
        return id;
    }

    public double getSupplierPrice() {
        return supplierPrice;
    }

    public long getAvailablle() {
        return availablle;
    }

    public long getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(long orderedAmount) {
        this.orderedAmount = orderedAmount;
    }
}
