package org.example.domain.lodging;

import org.example.domain.Location;
import org.example.domain.UserEntity;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "lodging")
@TypeAlias("Cabin")
public class Cabin extends Lodging {


    public Cabin(String name, String description, int capacity, int rooms, int bathrooms, String detail, List<String> tags, boolean cleanService, Float basePrice, Location location, UserEntity creator, String imageURL) {
        super(name, description, capacity, rooms, bathrooms, detail, tags, cleanService, basePrice, location, creator, imageURL);
        this.type = "Cabania";
    }

    @Override
    public Double pricePlus() {
        if (this.cleanService) return 10000.0;
        else return 0.0;
    }
}
