package org.example.domain.lodging;

import org.example.domain.Location;
import org.example.domain.UserEntity;

import java.util.List;

public class Apartment extends Lodging{


    public Apartment(String name, String description, int capacity, int rooms, int bathrooms, String detail, List<String> tags, boolean cleanService, float basePrice, Location location, UserEntity creator, String imageURL) {
        super(name, description, capacity, rooms, bathrooms, detail, tags, cleanService, basePrice, location, creator, imageURL);
        this.type = "Departamento";
    }

    @Override
    public Double pricePlus() {
        if (this.rooms < 3) return 2000.0;
        else return rooms * 1000.0;
    }
}
