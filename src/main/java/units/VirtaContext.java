package units;

import downloaders.UnitDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VirtaContext {
    private static List<Unit> units;
    private static Map<String, List<Unit>> organizedUnitsByType;
    private static Map<Integer, String> unitTypeMap;
    private static List<EquipmentUnit> equipedUnits = new ArrayList<>();
    private static List<EmployeeUnit> employeeUnits = new ArrayList<>();

    static {
        UnitDownloader unitDownloader = new UnitDownloader();
        units = unitDownloader.downloadAll();
        organizedUnitsByType = unitDownloader.organizeUnitsByType(units);
        unitTypeMap = unitDownloader.unitsTypeMap(units);
        setEquipedUnits();
    }

    public static String checkUnitType(int unitId) {
        String unitType = unitTypeMap.get(unitId);
        if (unitType == null) {
            throw new IllegalArgumentException("No such unitId found");
        } else {
            return unitType;
        }
    }

    public static List<Unit> getUnitListByType(String unitType) {
        return organizedUnitsByType.get(unitType);
    }

    public static Unit getUnitById(int unitId) {
        for (Unit unit : units) {
            if (unit.getUnitId() == unitId) {
                return unit;
            }
        }
        return null;
    }

    private static void setEquipedUnits() {
        for (Unit unit : units) {
            if (unit.getHasEquipment()) {
                equipedUnits.add(new EquipedUnit(unit));
            }
            if (unit.getHasEmployee()) {
                employeeUnits.add(new EmplUnit(unit));
            }
        }
    }

    public static List<EquipmentUnit> getEquipedUnits() {
        return equipedUnits;
    }

    public static List<EmployeeUnit> getEmployeeUnits() {
        return employeeUnits;
    }
}
