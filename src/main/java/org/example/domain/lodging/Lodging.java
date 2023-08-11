package org.example.domain.lodging;

import org.example.domain.Location;
import org.example.domain.UserEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Document(collection = "lodging")
public abstract class Lodging {
@Id
public
String id;
String name;
public String description;
public int capacity;
public int rooms;
public int bathrooms;
public String detail;
public List<String> tags;
public boolean cleanService;
float basePrice;
public Location location;
public UserEntity creator;
public String imageURL;
public float avgScore;
public int amtScores;
public boolean active;
public String type;

public Lodging(
        String name,
        String description,
        int capacity,
        int rooms,
        int bathrooms,
        String detail,
        List<String> tags,
        boolean cleanService,
        float basePrice,
        Location location,
        UserEntity creator,
        String imageURL){
    this.name = name;
    this.description = description;
    this.capacity = capacity;
    this.rooms = rooms;
    this.bathrooms = bathrooms;
    this.detail = detail;
    this.tags = tags;
    this.cleanService = cleanService;
    this.basePrice = basePrice;
    this.location = location;
    this.creator = creator;
    this.imageURL = imageURL;
    this.active = true;
}

    public String getName() {
        return name;
    }

    public Double priceBeforecommission(){
    return basePrice + this.pricePlus();
}

public abstract Double pricePlus();

public Double priceCalculationBetweenDates(LocalDate startDate, LocalDate endDate){
    return this.totalPrice() * Period.between(startDate,endDate).getDays();
}

public Double totalPrice() {
    return (this.priceBeforecommission() * 1.05);
}

public void setAvgScore(float avgScore) {
    this.avgScore = avgScore;
}

public void setAmtScores(int amtScores) {
    this.amtScores = amtScores;
}
}

