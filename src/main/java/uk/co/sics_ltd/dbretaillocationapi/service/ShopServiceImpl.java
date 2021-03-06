package uk.co.sics_ltd.dbretaillocationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;
import uk.co.sics_ltd.dbretaillocationapi.repository.ShopRepository;
import uk.co.sics_ltd.dbretaillocationapi.service.exception.NoShopFoundException;

import java.util.Optional;

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
        Optional<ShopDetail> result =
            shopRepository.findNearestToLongitudeAndLatitude(longitude, latitude);

        if(result.isPresent()) {
            return result.get();
        }

        throw new NoShopFoundException();
    }
}
