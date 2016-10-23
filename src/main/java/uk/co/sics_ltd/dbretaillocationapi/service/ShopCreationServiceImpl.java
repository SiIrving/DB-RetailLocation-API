package uk.co.sics_ltd.dbretaillocationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

public class ShopCreationServiceImpl implements ShopCreationService {

    private PostcodeLocationService postcodeLocationService;

    private ShopRepository shopRepository;

    @Autowired
    public ShopCreationServiceImpl(PostcodeLocationService postcodeLocatorService,
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

}
