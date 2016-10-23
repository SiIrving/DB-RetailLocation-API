package uk.co.sics_ltd.dbretaillocationapi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;
import uk.co.sics_ltd.dbretaillocationapi.service.Location;
import uk.co.sics_ltd.dbretaillocationapi.service.PostcodeLocationService;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShopRepositoryImpl implements ShopRepository {

    private final PostcodeLocationService postcodeLocationService;

    private List<ShopDetail> shopDetails;

    @Autowired
    public InMemoryShopRepositoryImpl(PostcodeLocationService postcodeLocationService) {
        this.postcodeLocationService = postcodeLocationService;
        shopDetails = new ArrayList();
    }

    @Override
    public void save(ShopDetail shopDetail) {
        shopDetails.add(shopDetail);
    }

    @Override
    public ShopDetail findNearestToPostcode(String postcode) {

        Location postcodeLocation = postcodeLocationService.locatePostcode(postcode);

        return shopDetails.stream()
                .sorted((shopDetail1, shopDetail2)
                            -> Double.compare(shopDetail1.distanceTo(postcodeLocation),
                                                shopDetail2.distanceTo(postcodeLocation)))
                .findFirst()
                .get();
    }

}
