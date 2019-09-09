package units;

import java.util.List;

public class Restaurant extends EquipedUnit implements Supplieble {

    private List<Product> products;

    public Restaurant(Unit unit) {
        super(unit);
    }

    public Restaurant(Unit unit, List<Product> products) {
        super(unit);
        if (!unit.getUnitType().equals("restaurant")){
            throw new  IllegalArgumentException("It is expected unit type to be restaurant");
        }
        this.products = products;
    }

    @Override
    public void optimizeSypplies() {

    }

    @Override
    public String toString() {
        return "\nRestaurant{" +
                "\n     {unit=" + super.getUnit() + "}" +
                "\n     {products=" + products + "}" +
                "\n}";
    }

}
