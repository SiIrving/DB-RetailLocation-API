package uk.co.sics_ltd.dbretaillocationapi.service;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.sics_ltd.dbretaillocationapi.domain.ShopDetail;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShopCreationServiceImplTest {

    @InjectMocks
    private ShopCreationServiceImpl classUnderTest;

    @Mock
    private PostcodeLocationService postcodeLocationService;

    @Mock
    private ShopRepository shopRepository;

    private static final String SHOP_NAME = "ShopName";

    private static final String POSTCODE = "P05TC0D3";

    private static final int SHOP_NUMBER = 69;

    private static final double LONGITUDE = -0.05807499999999999;

    private static final double LATITUDE = 51.5190259;

    @Before
    public void setUpMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shopHasValidPostcode_createShopCalled_shopCreatedWithLongitudeAndLatitudeEnriched() {

        when(postcodeLocationService.locatePostcode(any()))
                .thenReturn(new Location(LONGITUDE, LATITUDE));

        classUnderTest.createShop(new ShopDetail(SHOP_NAME, SHOP_NUMBER, POSTCODE));

        verify(shopRepository).save(argThat(matchesShopEnrichedWithLocation()));
        verify(postcodeLocationService).locatePostcode(POSTCODE);

    }

    private Matcher<ShopDetail> matchesShopEnrichedWithLocation() {
        return new BaseMatcher<ShopDetail>() {
            @Override
            public boolean matches(Object item) {
                return item instanceof ShopDetail
                        && ((ShopDetail) item).getShopName().equals(SHOP_NAME)
                        && ((ShopDetail) item).getShopAddress().getNumber() == SHOP_NUMBER
                        && ((ShopDetail) item).getShopAddress().getPostcode().equals(POSTCODE)
                        && ((ShopDetail) item).getLatitude() == LATITUDE
                        && ((ShopDetail) item).getLongitude() == LONGITUDE;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

}