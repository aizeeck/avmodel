package units;

import downloaders.ProductDownloader;

import java.util.List;

public class SuppliableUnit implements Suppliable {

    private Unit unit;
    private List<Product> products;

    public SuppliableUnit(Unit unit) {
        if (!unit.getUnitType().equals("restaurant") && !unit.getUnitType().equals("workshop")){
            throw new  IllegalArgumentException("It is expected unit type to be suppliable unit type");
        }
        this.unit = unit;
        this.products = new ProductDownloader().download(unit.getUnitId());
    }

    public SuppliableUnit(Unit unit, List<Product> products) {
        if (!unit.getUnitType().equals("restaurant") && !unit.getUnitType().equals("workshop")){
            throw new  IllegalArgumentException("It is expected unit type to be suppliable unit type");
        }
        this.unit = unit;
        this.products = products;
    }

    @Override
    public int getUnitId() {
        return unit.getUnitId();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "\nSuppliable{" +
                "\n     {unit=" + unit.getUnitId() + "}" +
                "\n     {products=" + products + "}" +
                "\n}";
    }

}
