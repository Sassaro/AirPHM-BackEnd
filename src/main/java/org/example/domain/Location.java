package org.example.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Location {

    String country;
    String state;
    String city;
    String address;

    public Location(String country, String state, String city, String address){
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
    }

}
