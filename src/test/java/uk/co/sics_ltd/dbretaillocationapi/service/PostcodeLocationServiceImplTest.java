package uk.co.sics_ltd.dbretaillocationapi.service;

import org.junit.Test;
import uk.co.sics_ltd.dbretaillocationapi.service.exception.UnknownPostcodeException;

import static org.junit.Assert.assertEquals;

public class PostcodeLocationServiceImplTest {

    private PostcodeLocationServiceImpl postcodeLocationServiceImpl
            = new PostcodeLocationServiceImpl("AIzaSyDlL-VfBWt2C7hG_LwKqFTREJOXnSamVg0");

    @Test
    public void testLocateValidPostcode() {
        Location location = postcodeLocationServiceImpl
                    .locatePostcode("E11AA");

        assertEquals(-0.05807499999999999,location.getLongitude(), 0);
        assertEquals(51.5190259,location.getLatitude(), 0);
    }

    @Test(expected = UnknownPostcodeException.class)
    public void testLocateUnknownPostcode() {
        postcodeLocationServiceImpl
                .locatePostcode("NE11AA");

    }

}