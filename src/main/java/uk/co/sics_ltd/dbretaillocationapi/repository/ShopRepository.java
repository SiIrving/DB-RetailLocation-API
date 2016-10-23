package uk.co.sics_ltd.dbretaillocationapi.repository;

import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;
import uk.co.sics_ltd.dbretaillocationapi.service.Location;

public interface ShopRepository {

    void save(ShopDetail shopDetail);

    ShopDetail findNearestToLocation(Location location);
}
