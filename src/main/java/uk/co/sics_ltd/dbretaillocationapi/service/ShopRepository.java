package uk.co.sics_ltd.dbretaillocationapi.service;

import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

public interface ShopRepository {
    void save(ShopDetail shopDetail);
}
