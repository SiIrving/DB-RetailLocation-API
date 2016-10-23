package uk.co.sics_ltd.dbretaillocationapi.repository;

import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

public interface ShopRepository {

    void save(ShopDetail shopDetail);

    ShopDetail findNearestToPostcode(String postcode);
}
