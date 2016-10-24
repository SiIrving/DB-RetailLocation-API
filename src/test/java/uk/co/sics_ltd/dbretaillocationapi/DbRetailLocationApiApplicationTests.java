package uk.co.sics_ltd.dbretaillocationapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DbRetailLocationApiApplicationTests {

    @LocalServerPort
    int port;

    @Before
    public void setUpShopDetail() {

        ShopDetail shopDetailNewcastle = new ShopDetail("TestShopNewcastle", 69, "NE11AD");
        ShopDetail shopDetailLondon = new ShopDetail("TestShopLondon", 69, "E11AA");


        given()
                .port(port)
                .contentType("application/json")
                .body(shopDetailNewcastle)
       .when()
                .post("/shop")
       .then()
                .statusCode(201);

        given()
                .port(port)
                .contentType("application/json")
                .body(shopDetailLondon)
       .when()
                .post("/shop")
       .then()
                .statusCode(201);

    }

    @Test
    public void testRetrieveShopDetail() {
        given()
                .port(port)
       .when()
                .get("/shop?customerLongitude=-1.4635271&customerLatitude=53.3815505")
       .then()
                .statusCode(200)
                .body("shopName", equalTo("TestShopNewcastle"),
                        "shopAddress.number", equalTo(69),
                        "shopAddress.postcode", equalTo("NE11AD")
                );

    }

    @Test
    public void testSaveShopDetailForBadPostcode() {

        String badPostcode = "NE11AA";

        ShopDetail shopDetailPostcodeNotFound = new ShopDetail("TestShopNewcastle", 69, badPostcode);

        given()
                .port(port)
                .contentType("application/json")
                .body(shopDetailPostcodeNotFound)
       .when()
                .post("/shop")
       .then()
                .statusCode(400)
                .body("errorMessage", equalTo(String.format(
                        "Postcode %s not found", "NE11AA"
                    ))
                );

    }

}


