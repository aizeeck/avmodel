package downloaders;

import units.Product;
import units.Restaurant;
import units.Suppliable;
import units.Unit;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDownloader {

    public List<Suppliable> downloadAll(List<Unit> restaurantTypeUnits) {
        List<Suppliable> restaurants = new ArrayList<>();

        for (Unit u : restaurantTypeUnits) {
            restaurants.add(download(u));
            System.out.println(u);
        }
        return restaurants;

    }

    public Restaurant download(Unit restaurantTypeUnit) {
        List<Product> products;
        products = new ProductDownloader().download(restaurantTypeUnit.getUnitId());
        Restaurant restaurant = new Restaurant(restaurantTypeUnit, products);
        return restaurant;
    }

}
