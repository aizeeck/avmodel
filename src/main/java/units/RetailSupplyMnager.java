package units;

import browser.Browser;
import config.Configuration;
import downloaders.OfferStandardDownloader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetailSupplyMnager {

    private static final String SUPPLY_CREATE = Configuration.getInstance().getProperty("SUPPLY_CREATE");
    private static final String SUPPLY_CREATE_AJAX = Configuration.getInstance().getProperty("SUPPLY_CREATE_AJAX");

    public void optimizeSupplies(List<Suppliable> units) {
        for (Suppliable suppliableUnit : units) {
            optimizeSupplies(suppliableUnit);
        }
    }

    public void optimizeSupplies(Suppliable unit) {
        for (Product product : unit.getProducts()) {
            int targetSales = VirtaContext.getProductConsumptionByUnitId(unit.getUnitId(), product.getId()) * 2;;
            OfferStandardDownloader offerStandardDownloader = new OfferStandardDownloader();

            List<Offer> offers = offerStandardDownloader.download(product.getId(), unit.getUnitId());
            offers.sort(Offer::compareTo);
            for (Offer offer : offers) {
                if (offer.getOrderedAmount() > 0) {
                    postOfferOrderVolume(unit.getUnitId(), offer, 0);
                }
            }

            int accumQuantity = 0;
            int offerCounter = 0;
            int consPerClient = product.getConsPerTurn();
            int tmp = targetSales * consPerClient - product.getQuantity();
            int nextSupplies = (targetSales * consPerClient) + tmp;

            if (product.getQuantity() > targetSales) continue;

            while (accumQuantity < nextSupplies && offerCounter < offers.size()) {
                long offerOrderVolume
                        = Math.min(
                                offers.get(offerCounter).getAvailablle(),
                                (nextSupplies - accumQuantity));

                if (offerOrderVolume > 0) {
                    postOfferOrderVolume(
                            unit.getUnitId(),
                            offers.get(offerCounter),
                            offerOrderVolume);
                }

                accumQuantity += offerOrderVolume;
                offerCounter++;
            }
            System.out.println("accumQuantity: " + accumQuantity);
        }
    }

    private void postOfferOrderVolume(int unitId, Offer offer, long offerOrderVolume) {
//        if (offer.getOrderedAmount() > 0) {
            Map<String, String> postParams = new HashMap<>();
            postParams.put("offer", String.valueOf(offer.getId()));
            postParams.put("unit", String.valueOf(unitId));
            postParams.put("amount", String.valueOf(offerOrderVolume));
            postParams.put("priceConstraint", String.valueOf(offer.getSupplierPrice()));
            postParams.put("priceMarkUp", String.valueOf(0));
            postParams.put("qualityMin", String.valueOf(0));
            postParams.put("constraintPriceType", "Rel");
            postParams.put("instant", "false");
            offer.setOrderedAmount(offerOrderVolume);
            try {
                new Browser().sendPost(SUPPLY_CREATE_AJAX,
                        SUPPLY_CREATE + unitId + "/step2",
                        postParams);
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
    }

}
