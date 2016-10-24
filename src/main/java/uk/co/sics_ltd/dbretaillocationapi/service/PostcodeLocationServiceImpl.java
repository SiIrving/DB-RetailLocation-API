package uk.co.sics_ltd.dbretaillocationapi.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.sics_ltd.dbretaillocationapi.service.exception.UnknownPostcodeException;

@Service
public class PostcodeLocationServiceImpl implements PostcodeLocationService {

    @Override
    public Location locatePostcode(String postcode) {
        RestTemplate restTemplate = getRestTemplate();
        GoogleGeocodeResponse googleGeoCode
                = restTemplate.getForObject(
                    String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",
                            postcode),
                GoogleGeocodeResponse.class
        );

        if (!googleGeoCode.status.equals("OK")) {
            throw new UnknownPostcodeException(postcode);
        }

        GoogleGeocodeLocation googleGeocodeLocation = googleGeoCode.results[0].geometry.location;

        return new Location(Double.valueOf(googleGeocodeLocation.lng),
                Double.valueOf(googleGeocodeLocation.lat));

    }

    private RestTemplate getRestTemplate() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD,
                JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setPrettyPrint(false);
        messageConverter.setObjectMapper(objectMapper);
        restTemplate.getMessageConverters().removeIf(m -> m.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
        restTemplate.getMessageConverters().add(messageConverter);

        return restTemplate;
    }


    // Classes needed to deserialise GeoCode response
    private static class GoogleGeocodeResponse {
        private String status;
        private GoogleGeocodeResult[] results;
    }

    private static class GoogleGeocodeResult {
        private GoogleGeocodeGeometry geometry;
    }

    private static class GoogleGeocodeGeometry {
        private GoogleGeocodeLocation location;
    }

    private static class GoogleGeocodeLocation {
        private String lat;
        private String lng;
    }
}
