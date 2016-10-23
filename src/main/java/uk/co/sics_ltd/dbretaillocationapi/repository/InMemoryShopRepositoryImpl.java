package uk.co.sics_ltd.dbretaillocationapi.repository;

import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;
import uk.co.sics_ltd.dbretaillocationapi.service.Location;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShopRepositoryImpl implements ShopRepository {

    private List<ShopDetail> shopDetails;

    public InMemoryShopRepositoryImpl() {
        shopDetails = new ArrayList();
    }

    @Override
    public void save(ShopDetail shopDetail) {
        shopDetails.add(shopDetail);
    }

    @Override
    public ShopDetail findNearestToLocation(Location location) {
        return shopDetails.stream()
                .sorted((shopDetail1, shopDetail2)
                            -> Double.compare(shopDetail1.distanceTo(location),
                                                shopDetail2.distanceTo(location)))
                .findFirst()
                .get();
    }

}
