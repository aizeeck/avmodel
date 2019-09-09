package units;

public class EmplUnit implements EmployeeUnit {

    private Unit unit;

    public EmplUnit(Unit unit) {
        if (unit.getHasEmployee() == false) {
            throw new IllegalArgumentException("Unit has no employee.");
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
    public int getEmplQnt() {
        return unit.getEmplQnt();
    }

    @Override
    public double getEmplLevel() {
        return unit.getEmplLevel();
    }

    @Override
    public double getEmplLevelReq() {
        return unit.getEmplLevelReq();
    }

    @Override
    public double getEmplSalary() {
        return unit.getEmplSalary();
    }

}
