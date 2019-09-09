package downloaders;

import browser.Browser;
import config.Configuration;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import units.Unit;
import utils.StringConvertor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UnitFinancialsDownloader {

    private static final String FINANCE_REPORT_BY_UNITS_URL = Configuration.getInstance().getProperty("FINANCE_REPORT_BY_UNITS_URL");
    private static final String FINANCE_REPORT_BY_UNITS_REF = Configuration.getInstance().getProperty("FINANCE_REPORT_BY_UNITS_REF");

    public Map<Integer, Map<String, String>> downloadAll () {
        List<Unit> units = new ArrayList<>();

        Document document = new Browser().getPage(FINANCE_REPORT_BY_UNITS_URL
            , FINANCE_REPORT_BY_UNITS_REF);

        Map<Integer, Map<String, String>> unitsGeneralProperties = parseUnitsGeneralProperties(document);

        return unitsGeneralProperties;
    }

    private Map<Integer, Map<String, String>> parseUnitsGeneralProperties(Document document) {
        Map<Integer, Map<String, String>> unitsGeneralProperties = new HashMap<>(400);

        Elements unitElements = document.getElementsByClass("even");
        unitElements.addAll(document.getElementsByClass("odd"));

        Elements headerElements = unitElements.first().parent().getElementsByTag("th");
        ArrayList<String> header = new ArrayList<>();
        for (Element e : headerElements) {
            header.add(e.text());
        }

        for (Element unitElement : unitElements) {
            Map<String, String> unitData = new HashMap<>();

            String unitIdT = unitElement.select("a[href]").attr("href");
            String unitTypeT = unitElement.select("img[src]").attr("src");

            //parse unit type
            Pattern pattern = Pattern.compile("unit_types/\\D+[.gif]$");
            Matcher matcher = pattern.matcher(unitTypeT);
            matcher.find();
            String matched = matcher.group();
            String unitType = matched.substring(11, matched.length()-4);
            unitData.put("unitType", unitType);

            //parse unit id
            pattern = Pattern.compile("view/\\d+$");
            matcher = pattern.matcher(unitIdT);
            matcher.find();
            matched = matcher.group();
            int unitId = StringConvertor.convertToInt(matched.substring(5));

            //put unit general data
            Elements data = unitElement.getElementsByTag("td");
            for (int i = 0; i < header.size(); i++) {
                unitData.put(header.get(i), data.get(i).text());
            }

            unitsGeneralProperties.put(unitId, unitData);

        }
        return unitsGeneralProperties;
    }

}
