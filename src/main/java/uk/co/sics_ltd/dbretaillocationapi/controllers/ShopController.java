package uk.co.sics_ltd.dbretaillocationapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/shop")
public class ShopController {


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = POST)
    public void createShop(@RequestBody ShopDetail shop) {

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = GET, produces = "application/json")
    public ShopDetail getShop(@RequestParam("customerLongitude") Float customerLongitude,
                        @RequestParam("customerLatitude") Float customerLatitude) {

        return new ShopDetail("TestShopNewcastle", 69, "NE11AD");

    }


}
