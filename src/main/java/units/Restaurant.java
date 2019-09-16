package units;

import downloaders.ProductDownloader;

import java.util.List;

public class Restaurant extends EquipedUnit implements Suppliable {

    private List<Product> products;

    public Restaurant(Unit unit) {
        super(unit);
        if (!unit.getUnitType().equals("restaurant")){
            throw new  IllegalArgumentException("It is expected unit type to be restaurant");
        }
        products = new ProductDownloader().download(unit.getUnitId());
    }

    public Restaurant(Unit unit, List<Product> products) {
        super(unit);
        if (!unit.getUnitType().equals("restaurant")){
            throw new  IllegalArgumentException("It is expected unit type to be restaurant");
        }
        this.products = products;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "\nRestaurant{" +
                "\n     {unit=" + super.getUnit() + "}" +
                "\n     {products=" + products + "}" +
                "\n}";
    }

}
