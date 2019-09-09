package downloaders;

import units.Product;
import units.Farm;
import units.Unit;

import java.util.ArrayList;
import java.util.List;

class FarmDownloader {

    public List<Farm> downloadAll(List<Unit> farmTypeUnits) {
        List<Farm> farms = new ArrayList<>();

        for (Unit u : farmTypeUnits) {
            farms.add(download(u));
        }
        return farms;

    }

    public Farm download(Unit farmTypeUnit) {
        Farm farm = new Farm(farmTypeUnit);
        return farm;
    }

}
