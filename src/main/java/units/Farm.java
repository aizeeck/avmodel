package units;

import java.util.List;

public class Farm implements Suppliable {

    private Unit unit;
    private List<Product> products;

    public Farm(Unit unit) {
        if (!unit.getUnitType().equals("farm")){
            throw new  IllegalArgumentException("It is expected unit type to be restaurant");
        }
        this.unit = unit;
    }

    @Override
    public int getUnitId() {
        return unit.getUnitId();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
