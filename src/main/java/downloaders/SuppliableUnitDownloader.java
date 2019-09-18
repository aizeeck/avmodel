package downloaders;

import units.*;

import java.util.ArrayList;
import java.util.List;

public class SuppliableUnitDownloader {

    public List<Suppliable> downloadAll(List<Unit> restaurantTypeUnits) {
        List<Suppliable> suppliables = new ArrayList<>();

        for (Unit u : restaurantTypeUnits) {
            suppliables.add(download(u));
            System.out.println(u);
        }
        return suppliables;

    }

    public Suppliable download(Unit unit) {
        List<Product> products;
        products = new GeneralProductDownloader().download(unit.getUnitId());
        SuppliableUnit suppliableUnit = new SuppliableUnit(unit, products);
        return suppliableUnit;
    }

}
