package com.example.ecommerce.address;

public class Address {
    private Integer user;
    private String street;
    private String city;
    private String province;
    private String postcode;
    private String country;

    public Address() {}

    public Address(Integer user, String street, String city, String province, String postcode, String country) {
        this.user = user;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postcode = postcode;
        this.country = country;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
