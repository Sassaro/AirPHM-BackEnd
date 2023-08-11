package org.example.domain.lodging;

import org.example.domain.Location;
import org.example.domain.UserEntity;

import java.util.List;

public class House extends Lodging{


    public House(String name, String description, int capacity, int rooms, int bathrooms, String detail, List<String> tags, boolean cleanService, float basePrice, Location location, UserEntity creator, String imageURL) {
        super(name, description, capacity, rooms, bathrooms, detail, tags, cleanService, basePrice, location, creator, imageURL);
        this.type = "Casa";
    }

    @Override
    public Double pricePlus() {
        return this.capacity * 500.0;
    }
}
