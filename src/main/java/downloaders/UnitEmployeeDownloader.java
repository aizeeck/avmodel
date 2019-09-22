package downloaders;

import browser.Browser;
import config.Configuration;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import utils.StringConvertor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UnitEmployeeDownloader {

    private static final String EMPL_MANAGEMENT_URL = Configuration.getInstance().getProperty("EMPL_MANAGEMENT_URL");
    private static final String EMPL_MANAGEMENT_REF = Configuration.getInstance().getProperty("EMPL_MANAGEMENT_REF");
    private static final String EMPL_EDU_URL = Configuration.getInstance().getProperty("EMPL_EDU_URL");

    public Map<Integer, Map<String, String>> downloadAll () {

        Document document = new Browser().getPage(EMPL_MANAGEMENT_URL
                , EMPL_MANAGEMENT_REF);

        Map<Integer, Map<String, String>> unitsGeneralProperties = parseEmplProperties(document);

        return unitsGeneralProperties;
    }


    private Map<Integer, Map<String, String>> parseEmplProperties(Document document) {
        Map<Integer, Map<String, String>> unitsGeneralProperties = new HashMap<>(400);

        Element mainContent = document.getElementById("mainContent");
        Elements unitList = mainContent.getElementsByTag("input");
        Elements baseSalary = unitList.select("[name^=base_salary]");
        Elements qnt = unitList.select("[id~=(qnt_)\\d]");
        Elements qntMax = unitList.select("[name^=qnt_max]");
        Elements emplLevelReq = unitList.select("[id^=employee_level_required]");
        Elements emplLevel = mainContent.select("[title=Employee training]");
        Elements unitEff = mainContent.select("[name=unit_holiday]");

        for (Element e : baseSalary) {
            int unitId = StringConvertor.convertToInt(e.attr("id"));
            Map<String, String> map = new HashMap<>();

            String emplSalaryString = String.valueOf(StringConvertor.convertToDouble(
                    baseSalary.select("[id=base_salary_" + unitId + "]").first().parent().parent().getAllElements().get(17).textNodes().get(0).text()));
            map.put("emplSalary", emplSalaryString);

            map.put("emplBaseSalary",
                    baseSalary.select("[id=base_salary_" + unitId + "]").first().attr("value"));
            map.put("emplBaseSalary",
                    baseSalary.select("[id=base_salary_" + unitId + "]").first().attr("value"));
            map.put("emplQnt",
                    qnt.select("[id=qnt_" + unitId + "]").first().attr("value"));
            map.put("emplQntMax",
                    qntMax.select("[id=qnt_max_" + unitId + "]").first().attr("value"));
            map.put("emplLevelReq",
                    emplLevelReq.select("[id=employee_level_required_" + unitId + "]").first().attr("value"));
            map.put("emplLevel",
                    emplLevel.select("[href=" + EMPL_EDU_URL + unitId + "]").first().parent().text());
            map.put("emplEff",
                    unitEff.select("[id=unit_holiday_" + unitId + "]").first().parent().text());
            map.put("hasEmployee", "true");
            unitsGeneralProperties.put(unitId,  map);
        }
        return unitsGeneralProperties;
    }

}
