package uk.co.sics_ltd.dbretaillocationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;
import uk.co.sics_ltd.dbretaillocationapi.repository.ShopRepository;

@Service
public class ShopServiceImpl implements ShopService {

    private PostcodeLocationService postcodeLocationService;

    private ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(PostcodeLocationService postcodeLocatorService,
                           ShopRepository shopRepository) {
        this.postcodeLocationService = postcodeLocatorService;
        this.shopRepository = shopRepository;
    }

    @Override
    public void createShop(ShopDetail shopDetail) {

        Location location = postcodeLocationService.locatePostcode(
                shopDetail
                    .getShopAddress()
                        .getPostcode()
        );

        shopDetail.setLongitude(location.getLongitude());
        shopDetail.setLatitude(location.getLatitude());


        shopRepository.save(shopDetail);

    }

    @Override
    public ShopDetail findNearestToLongitudeAndLatitude(Double longitude, Double latitude) {
        return shopRepository.findNearestToLongitudeAndLatitude(longitude, latitude);
    }
}
