package uk.co.sics_ltd.dbretaillocationapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;
import uk.co.sics_ltd.dbretaillocationapi.service.ShopService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/shop")
@ComponentScan
public class ShopController {

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = POST)
    public void createShop(@RequestBody ShopDetail shop) {
        shopService.createShop(shop);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = GET, produces = "application/json")
    public ShopDetail getShop(@RequestParam("customerLongitude") Double customerLongitude,
                        @RequestParam("customerLatitude") Double customerLatitude) {

        return shopService.findNearestToLongitudeAndLatitude(customerLongitude, customerLatitude);

    }


}
