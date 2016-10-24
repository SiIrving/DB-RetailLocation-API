package uk.co.sics_ltd.dbretaillocationapi.repository;

import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

import java.util.Optional;

public interface ShopRepository {

    void save(ShopDetail shopDetail);

    Optional<ShopDetail> findNearestToLongitudeAndLatitude(Double longitude, Double latitude);
}
