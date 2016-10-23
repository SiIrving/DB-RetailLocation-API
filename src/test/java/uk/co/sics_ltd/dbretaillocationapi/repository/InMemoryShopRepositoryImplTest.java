package uk.co.sics_ltd.dbretaillocationapi.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;
import uk.co.sics_ltd.dbretaillocationapi.service.Location;
import uk.co.sics_ltd.dbretaillocationapi.service.PostcodeLocationService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryShopRepositoryImplTest {

    private InMemoryShopRepositoryImpl classUnderTest;

    @Mock
    private PostcodeLocationService postcodeLocationService;

    private static final ShopDetail CLOSEST_SHOP = new ShopDetail("TestShopClosest", 69, "CL053T");

    private static final String POSTCODE = "POSTCODE";

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        classUnderTest = new InMemoryShopRepositoryImpl(postcodeLocationService);

        when(postcodeLocationService.locatePostcode(any()))
                .thenReturn(new Location(-1.4635271, 53.3815505));

        CLOSEST_SHOP.setLongitude(-1.6157867);
        CLOSEST_SHOP.setLatitude(54.9677216);
        classUnderTest.save(CLOSEST_SHOP);

        ShopDetail shopDetailLondon = new ShopDetail("TestShopFurthest", 69, "FUR5T");
        shopDetailLondon.setLongitude(-0.0576107);
        shopDetailLondon.setLatitude(51.5196578);
        classUnderTest.save(shopDetailLondon);

    }

    @Test
    public void testFindClosestToPostcode() {

        ShopDetail closestShop = classUnderTest.findNearestToPostcode(POSTCODE);

        assertEquals(CLOSEST_SHOP.getShopName(), closestShop.getShopName());
        assertEquals(CLOSEST_SHOP.getShopAddress().getNumber(), closestShop.getShopAddress().getNumber());
        assertEquals(CLOSEST_SHOP.getShopAddress().getPostcode(), closestShop.getShopAddress().getPostcode());
        assertEquals(CLOSEST_SHOP.getLongitude(), closestShop.getLongitude(), 0);
        assertEquals(CLOSEST_SHOP.getLatitude(), closestShop.getLatitude(), 0);
    }

}