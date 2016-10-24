package uk.co.sics_ltd.dbretaillocationapi.repository;

import org.springframework.stereotype.Repository;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
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
    public Optional<ShopDetail> findNearestToLongitudeAndLatitude(Double longitude, Double latitude) {
        return shopDetails.stream()
                .sorted((shopDetail1, shopDetail2)
                            -> Double.compare(shopDetail1.distanceTo(longitude, latitude),
                                                shopDetail2.distanceTo(longitude, latitude)))
                .findFirst();
    }

}
