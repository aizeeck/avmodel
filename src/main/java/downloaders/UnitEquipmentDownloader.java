package downloaders;

import browser.Browser;
import config.Configuration;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.StringConvertor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UnitEquipmentDownloader {

    private static final String EQUI_MANAGEMENT_URL = Configuration.getInstance().getProperty("EQUI_MANAGEMENT_URL");
    private static final String EQUI_MANAGEMENT_REF = Configuration.getInstance().getProperty("EQUI_MANAGEMENT_REF");


    public Map<Integer, Map<String, String>> downloadAll () {

        Document document = new Browser().getPage(EQUI_MANAGEMENT_URL
                , EQUI_MANAGEMENT_REF);

        Map<Integer, Map<String, String>> unitsGeneralProperties = parseEquipmentProperties(document);

        return unitsGeneralProperties;
    }


    private Map<Integer, Map<String, String>> parseEquipmentProperties(Document document) {
        Map<Integer, Map<String, String>> unitsGeneralProperties = new HashMap<>(400);

        Element mainContent = document.getElementsByAttributeValue("name", "unitsEquipmentRepair").first();
        Elements unitList = mainContent.getElementsByClass("list").select("[type=checkbox]");
        unitList.remove(0);
        for (Element e : unitList) {
            Elements elements = e.parent().parent().getElementsByTag("td");

            int unitId = StringConvertor.convertToInt(e.attr("id"));
            Map<String, String> map = new HashMap<>();

            map.put("equiQnt",
                    elements.get(3).text());
            map.put("equiQntMax",
                    elements.get(4).text());
            map.put("equilLevel",
                    elements.get(5).text());
            map.put("equilLevelReq",
                    elements.get(6).text());

            String wairStr = elements.get(7).childNode(1).attr("text");
            int percIndex = wairStr.indexOf("%");
            int len = wairStr.length();
            String wair = wairStr.substring(0, percIndex-1);
            String wairNum = wairStr.substring(percIndex+3, len).replace(')',' ');
            map.put("equiWairPercent",
                    wair);
            map.put("equiWairNum",
                    wairNum);
            map.put("eqiuEff",
                    elements.get(8).text());
            map.put("hasEquipment", "true");

            unitsGeneralProperties.put(unitId,  map);
        }
        return unitsGeneralProperties;
    }

}
