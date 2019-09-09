package units;

public class EquipedUnit implements EquipmentUnit {

    private Unit unit;

    public EquipedUnit(Unit unit) {
        if (unit.getHasEquipment() == false) {
            throw new IllegalArgumentException("Unit has no equipment.");
        }
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    @Override
    public int getUnitId() {
        return unit.getUnitId();
    }

    @Override
    public int getEquiQuantity() {
        return unit.getEquiQnt();
    }

    @Override
    public double getEquiQuality() {
        return unit.getEquilLevel();
    }

    @Override
    public double getEquiQualityReq() {
        return unit.getEquilLevelReq();
    }

    @Override
    public int getWairNum() {
        return unit.getEquiWairNum();
    }
}
