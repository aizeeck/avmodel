package units.departments;

import browser.Browser;
import config.Configuration;
import downloaders.EquipmentStandardDownloader;
import units.Equipment;
import units.EquipmentUnit;

import java.io.IOException;
import java.util.*;

public class EquipmentDepartment {

    private static final String EQUIPMENT_POST_URL = Configuration.getInstance().getProperty("EQUIPMENT_POST_URL");
    private static final String EQUIPMENT_POST_REF = Configuration.getInstance().getProperty("EQUIPMENT_POST_REF");


    public static void repare(List<EquipmentUnit> units) {
        for (EquipmentUnit unit : units) {
            repare(unit);
        }
    }

    public static void repare(EquipmentUnit unit) {
        repare(unit, unit.getEquiQualityReq());
    }

    public static void repare(EquipmentUnit unit, double target) {

        if (unit.getWairNum() == 0) return;

        List<Equipment> equipment = new EquipmentStandardDownloader().download(unit.getUnitId());
        equipment.sort(Equipment::compareTo);

        int unitId = unit.getUnitId();
        int quantuty = unit.getEquiQuantity();
        int wair = unit.getWairNum();
        double current = unit.getEquiQuality();

        double minReqQ = ((quantuty * target) - ((quantuty - wair) * current)) / wair;

        Iterator<Equipment> iterator = equipment.iterator();

        while (iterator.hasNext()) {
            Equipment e = iterator.next();
            if (!iterator.hasNext()) {
                post(unitId, e, wair);
                System.out.println(e);
            } else {
                if (e.getQuality() >= minReqQ && e.getAvailablle() >= wair) {
                    post(unitId, e, wair);
                    break;
                }
            }
        }
    }

    private static void post(long unitId, Equipment e, int wear) {

        Map<String, String> postParams = new HashMap<>();
        postParams.put("operation", "repair");
        postParams.put("offer", String.valueOf(e.getId()));
        postParams.put("unit", String.valueOf(unitId));
        postParams.put("supplier", String.valueOf(e.getId()));
        postParams.put("amount", String.valueOf(wear));
        System.out.println("Repairing equipment for \n" + unitId + "\n" +
                postParams);
        try {
            new Browser().sendPost(EQUIPMENT_POST_URL,
                    EQUIPMENT_POST_REF + unitId,
                    postParams);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
