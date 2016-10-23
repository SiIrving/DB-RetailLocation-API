package uk.co.sics_ltd.dbretaillocationapi.domain;

public class ShopDetail {

    private String shopName;

    private Address shopAddress;

    public ShopDetail() {

    }

    public ShopDetail(String shopName, int shopAddressNumber, String shopAddressPostcode) {

        this.shopName = shopName;
        this.shopAddress = new Address(shopAddressNumber, shopAddressPostcode);

    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Address getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(Address shopAddress) {
        this.shopAddress = shopAddress;
    }

    public static class Address {

        private int number;

        private String postcode;

        public Address() {

        }

        public Address(int number, String postcode) {
            this.number = number;
            this.postcode = postcode;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostCode(String postcode) {
            this.postcode = postcode;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

    }

}
