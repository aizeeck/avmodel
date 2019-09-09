package downloaders;

import units.Product;
import units.VirtaContext;

import java.util.List;


public class ProductDownloader {

    public ProductDownloader() {

    }
    public List<Product> download (int unitId) {
        switch (VirtaContext.checkUnitType(unitId)){
            case "restaurant":
                RestaurantProductDownloader downloader = new RestaurantProductDownloader();
                return downloader.download(unitId);
            default:
                throw new IllegalArgumentException("No such unitId found");
        }


    }

}
