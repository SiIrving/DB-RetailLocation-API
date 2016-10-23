package uk.co.sics_ltd.dbretaillocationapi.service.exception;

public class UnknownPostcodeException extends RuntimeException {

    private String postcode;

    public UnknownPostcodeException(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

}
