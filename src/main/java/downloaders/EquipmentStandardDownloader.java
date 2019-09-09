package downloaders;

import browser.Browser;
import config.Configuration;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import units.Equipment;
import utils.StringConvertor;

import java.util.ArrayList;
import java.util.List;

public class EquipmentStandardDownloader {

    private static final String EQUIPMENT_OFFERS_URL = Configuration.getInstance().getProperty("EQUIPMENT_OFFERS_URL");
    private static final String EQUIPMENT_OFFERS_REF = Configuration.getInstance().getProperty("EQUIPMENT_OFFERS_REF");


    public List<Equipment> download(int unitId) {
        Browser browser = new Browser();
        List<Equipment> equipment = new ArrayList<>();

        String url = EQUIPMENT_OFFERS_URL;
        String ref = EQUIPMENT_OFFERS_REF + unitId;
        System.out.println("Getting offers for \n" + url);
        String equipmentHtml = String.valueOf(browser.getPage(url, ref));
        Document doc = Jsoup.parse(equipmentHtml);
        Elements equipmentElements = doc.select("tr[id^=r]");


        for (Element element : equipmentElements) {

            String data = element.data();
            data = data.substring(data.indexOf("{"), data.length()-1);
            JSONObject jsonObject = new JSONObject(data);
            long supplierId =  (Integer) jsonObject.get("unit");

            Double quality;
            Object qualityObj = jsonObject.get("quality");
            if (qualityObj instanceof String) {
                quality =  StringConvertor.convertToDouble((String) jsonObject.get("quality"));
            } else if (qualityObj instanceof Integer) {
                quality =  Double.valueOf((Integer) jsonObject.get("quality"));
            } else if (qualityObj instanceof Double) {
                quality =  (Double) jsonObject.get("quality");
            } else {
                throw new ClassCastException("Unknown class found parsing quality");
            }

            Double price;
            Object priceObj = jsonObject.get("price");
            if (priceObj instanceof Integer) {
                price =  Double.valueOf((Integer) jsonObject.get("price"));
            } else if (priceObj instanceof Double) {
                price =  (Double) jsonObject.get("price");
            } else {
                throw new ClassCastException("Unknown class found parsing price");
            }

            long quantity =  (Integer) jsonObject.get("free4buy");

            long id = StringConvertor.convertToLong(element.attr("id"));
            Equipment tmp = new Equipment(id, id, quantity, price, quality);
            equipment.add(tmp);
        }
        return equipment;
    }
}
