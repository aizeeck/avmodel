package downloaders;

import browser.Browser;
import config.Configuration;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import units.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductConsumptionDownloader {

    private static final String CONSUMPTION = Configuration.getInstance().getProperty("CONSUMPTION");
    private static final String PRODUCT_CONSUMPTION = Configuration.getInstance().getProperty("PRODUCT_CONSUMPTION");
    private Browser browser = new Browser();

    public Map<Integer, Map<Integer, Integer>> download() {
        Browser browser = new Browser();
        Map<Integer, Map<Integer, Integer>> consumption = new HashMap<>();
        for (int id : getConsumedProductIds()) {
            String supplyHtml = browser.getPage(PRODUCT_CONSUMPTION + id).toString();
            supplyHtml = Jsoup.parse(supplyHtml).getElementsByTag("body").text();
            supplyHtml = supplyHtml.substring(1,supplyHtml.length()-1);
            String[] supplyHtmlJson = supplyHtml.split("},");
            Map<Integer, Integer> unitConsumption = new HashMap<>();
            for (String s : supplyHtmlJson) {
                JSONObject jsonObject = new JSONObject(s + "}");
                unitConsumption.put(jsonObject.getInt("unit_id"), jsonObject.getInt("qty"));
                consumption.put(id, unitConsumption);
            }
        }
        return consumption;
    }

    private List<Integer> getConsumedProductIds() {
        List<Integer> consumedProductIds = new ArrayList<>();
        Document document = Jsoup.parse(browser.getPage(CONSUMPTION).toString());
        Elements elements = document.getElementsByClass("c_row_l");
        for (Element e : elements) {
            consumedProductIds.add(Integer.valueOf(e.attr("id")));
        }

        return consumedProductIds;
    }


}
