package com.example.modelmapper.demo;

public class Address {

    private int streetNumber;

    private String street;

    private String city;

    private String country;

    public Address(int streetNumber, String street, String city, String country) {
        this.streetNumber = streetNumber;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
