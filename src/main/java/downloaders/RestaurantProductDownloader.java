package downloaders;
import browser.Browser;
import config.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import units.Product;
import utils.StringConvertor;

import java.util.*;

class RestaurantProductDownloader {

    private static final String UNIT_ABSTRACT_URL = Configuration.getInstance().getProperty("UNIT_ABSTRACT_URL");
    private static final String UNIT_GET_OFFERS_URL = Configuration.getInstance().getProperty("UNIT_GET_OFFERS_URL");

    private List<Product> products = new ArrayList<>();
    private String  unitHtml;
    private Document doc = null;

    public List<Product> download(int id) {
        unitHtml = new Browser().getPage(
                UNIT_ABSTRACT_URL + id + "/supply").toString();

        if (unitHtml == null) {
            return products;
        }

        doc = Jsoup.parse(unitHtml);

        Elements productElements = getProductElements("tr[id^=\"product_row\"]");

        for (Element productElement : productElements) {

            String productName = productElement
                    .select("th")
                    .select("table")
                    .select("tbody")
                    .select("tr")
                    .select("td")
                    .select("a")
                    .first()
                    .attributes()
                    .get("title");

            String productIdHref = productElement
                    .select("th")
                    .select("table")
                    .select("tbody")
                    .select("tr")
                    .select("td")
                    .select("a")
                    .first()
                    .attributes()
                    .get("href");

            int productId = StringConvertor.convertToInt(productIdHref);

            Elements productDetails = productElement
                    .select("td");

            Elements suppliesPriceElements = productDetails.select("td[id^=totalPrice_]").select("tbody").select("tr");
            Elements suppliesInStockElements = productDetails.select("td[id^=quantity_]").select("tbody").select("tr");

            String html = new Browser().getPage(UNIT_GET_OFFERS_URL + id + "/step1/" + productId).toString();
            try {
                doc = Jsoup.parse(html);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return products;
            }
            Elements consPerClientElement = doc
                    .select("body.window")
                    .select("div.supply_addition_info")
                    .select("table")
                    .select("tbody")
                    .select("tr")
                    .select("th.workshop");

            int quantity = 0;
            int consPerClient = 0;
            double quality = 0;
            double primeCost = 0.00;
            quantity = StringConvertor.convertToInt(consPerClientElement.get(2).text());
            consPerClient = StringConvertor.convertToInt(consPerClientElement.get(1).text());
            quality = StringConvertor.convertToDouble(consPerClientElement.get(0).text());

            Elements inStockDataElement = productElement
                    .getAllElements();
            inStockDataElement = inStockDataElement.select("table").select("tbody").select("tr");
            for (Element e : inStockDataElement) {
                if (e.text().contains("Prime cost")) {
                    Elements ee = e.children();
                    primeCost = StringConvertor.convertToDouble(e.children().get(1).text());
                }
            }

            /*List<Order> orders = orderMap.get(productId);
            if (orders == null) {
                orders = new ArrayList<>();
            }*/
            products.add(new Product(
                    productId,
                    productName,
                    consPerClient,
                    quantity,
                    quality,
                    primeCost
            ));
        }

        return products;
    }

    private Elements getProductElements(String rowDeff) {
        return doc
                .select("form[name=\"supplyContractForm\"]")
                .select("table[class=list]")
                .select("tbody")
                .select(rowDeff);
    }

}
