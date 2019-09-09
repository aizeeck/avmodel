package downloaders;

import units.Product;
import units.Restaurant;
import units.Unit;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDownloader {

    public List<Restaurant> downloadAll(List<Unit> restaurantTypeUnits) {
        List<Restaurant> restaurants = new ArrayList<>();

        for (Unit u : restaurantTypeUnits) {
            restaurants.add(download(u));
        }
        return restaurants;

    }

    public Restaurant download(Unit restaurantTypeUnit) {
        List<Product> products = new ArrayList<>();
        products = new ProductDownloader().download(restaurantTypeUnit.getUnitId());
        Restaurant restaurant = new Restaurant(restaurantTypeUnit, products);
        return restaurant;
    }

}
