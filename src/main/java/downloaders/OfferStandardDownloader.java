package downloaders;

import browser.Browser;
import config.Configuration;
import units.Offer;
import utils.StringConvertor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aizeeck on 08.08.17.
 */
public class OfferStandardDownloader {

    private static final String SUPPLY_CREATE = Configuration.getInstance().getProperty("SUPPLY_CREATE");

    public List<Offer> download(int productId, int unitId) {
        Browser browser = new Browser();
        List<Offer> offers = new ArrayList<>();
        String url = SUPPLY_CREATE + unitId + "/step1/" + productId;
        String supplyHtml = browser.getPage(url).toString();

        System.out.println("Getting offers for \n" + url);

        Document doc = Jsoup.parse(supplyHtml);
        Elements offersElements = doc.select("tr[id^=r]");
        for (Element offerElement : offersElements) {
            int offerId = StringConvertor.convertToInt(offerElement.attr("id"));
            double supplierPrice = StringConvertor.convertToDouble(offerElement.select("[class=\"start_price\"]").text());
            double price = StringConvertor.convertToDouble(offerElement.select("[class=\"price_w_tooltip\"]").get(1).textNodes().get(0).text());
            long inStock = 0;
            try {
                inStock = StringConvertor.convertToLong(offerElement.select("[class=\"price_w_tooltip\"]").get(0).select("i").get(0).text());
            } catch (IndexOutOfBoundsException e) {
                inStock = Integer.MAX_VALUE;
            }
            Elements availableElements = offerElement.select("[class=\"price_w_tooltip\"]").get(0).select("u");
            long available = 0;
            if (availableElements.size() != 0) {
                available = StringConvertor.convertToInt(offerElement.select("[class=\"price_w_tooltip\"]").get(0).select("u").get(0).text());
            } else {
                TextNode zero = offerElement.select("[class=\"price_w_tooltip\"]").get(0).textNodes().get(0);
                if (zero.toString().trim().equals("0")) {
                    available = 0;
                } else if (zero.toString().trim().contains("Unlim")) {
                    available = Integer.MAX_VALUE;
                } else if(zero.toString().trim().length() > 0) {
                    available = StringConvertor.convertToLong(zero.toString().trim());
                } else {
                    available = inStock;
                }
            }

            Elements orderedOffers = doc.getElementsByClass("orderedAmount");

            double quality = StringConvertor.convertToDouble(offerElement.select("[class=\"supply_data\"]").get(0).text());

            int orderedAmount = 0;
            Offer offer = new Offer(offerId, 0, available, inStock, supplierPrice, price, quality, orderedAmount);
            offers.add(offer);
        }

        int start = supplyHtml.indexOf("addContract");
        int end = supplyHtml.indexOf(',', start);
        Pattern p = Pattern.compile("addContract\\(\\d+[,]\\W+\\d+");
        Matcher m = p.matcher(supplyHtml);
        while(m.find()) {
            String[] res = supplyHtml.substring(m.start(), m.end()).split(",");
            int offerId = StringConvertor.convertToInt(res[0]);
            int orderedVolume = StringConvertor.convertToInt(res[1]);
            for (Offer offer : offers) {
                if (offer.getId() == offerId) {
                    offer.setOrderedAmount(orderedVolume);
                    break;
                }
            }
        }

        return offers;
    }
}
