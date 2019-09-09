package units;

import java.util.List;

public class Farm implements Supplieble {

    private Unit unit;
    private List<Product> products;

    public Farm(Unit unit) {
        if (!unit.getUnitType().equals("farm")){
            throw new  IllegalArgumentException("It is expected unit type to be restaurant");
        }
        this.unit = unit;
    }

    @Override
    public void optimizeSypplies() {

    }
}
