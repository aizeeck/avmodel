package downloaders;

import browser.Browser;
import config.Configuration;
import org.jsoup.nodes.Document;
import units.Unit;

import java.util.*;

public class UnitDownloader {

    private static final String FINANCE_REPORT_BY_UNITS_URL = Configuration.getInstance().getProperty("FINANCE_REPORT_BY_UNITS_URL");
    private static final String FINANCE_REPORT_BY_UNITS_REF = Configuration.getInstance().getProperty("FINANCE_REPORT_BY_UNITS_REF");

    public List<Unit> downloadAll () {
        List<Unit> units = new ArrayList<>();

        Document document = new Browser().getPage(FINANCE_REPORT_BY_UNITS_URL
            , FINANCE_REPORT_BY_UNITS_REF);

        Map<Integer, Map<String, String>> finData = new UnitFinancialsDownloader().downloadAll();
        Map<Integer, Map<String, String>> emplData = new UnitEmployeeDownloader().downloadAll();
        Map<Integer, Map<String, String>> equiData = new UnitEquipmentDownloader().downloadAll();

        HashSet<Integer> unitIds = new HashSet<>();
        unitIds.addAll(finData.keySet());
        unitIds.addAll(emplData.keySet());
        unitIds.addAll(equiData.keySet());

        List<Integer> ids = new ArrayList<>();
        ids.addAll(unitIds);

        Map<Integer, Map<String, String>> unitFullData = new HashMap<>();

        for (Integer id : ids) {

            Map<String, String> tmp = new HashMap<>();
            if (!(finData.get(id) == null)) {
                tmp.putAll(finData.get(id));
            }
            if (!(emplData.get(id) == null)) {
                tmp.putAll(emplData.get(id));
            }
            if (!(equiData.get(id) == null)) {
                tmp.putAll(equiData.get(id));
            }
            unitFullData.put(id, tmp);
            Unit unit = new Unit(id, tmp);
            units.add(unit);
        }

        return units;
    }

    public static Map<String, List<Unit>> organizeUnitsByType(List<Unit> units) {
        Map<String, List<Unit>> organizedUnitsByType = new HashMap<>();

        for (Unit unit : units) {
            if (!organizedUnitsByType.keySet().contains(unit.getUnitType())) {
                organizedUnitsByType.put(unit.getUnitType(), new ArrayList<>());
            }
            organizedUnitsByType.get(unit.getUnitType()).add(unit);
        }

        return organizedUnitsByType;
    }

    public static Map<Integer, String> unitsTypeMap(List<Unit> units) {
        Map<Integer, String> unitTypeMap = new HashMap<>();
        for (Unit unit : units) {
            unitTypeMap.put(unit.getUnitId(), unit.getUnitType());
        }
        return unitTypeMap;
    }
}
