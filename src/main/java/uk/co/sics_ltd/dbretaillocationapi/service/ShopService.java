package uk.co.sics_ltd.dbretaillocationapi.service;

import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

public interface ShopService {

    void createShop(ShopDetail shopDetail);

    ShopDetail findNearestToLongitudeAndLatitude(Double longitude, Double latitude);

}
