package org.example.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.example.BusinessException;
import org.example.domain.lodging.Lodging;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
public class UserEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
public
Long id;
public String name;
public String surname;
String password;
public String username;
public LocalDate birthday;
public String country;
public Double money;
@ManyToMany(fetch = FetchType.LAZY)
public
List<UserEntity> friendList;
public String imageURL;

public UserEntity(){}

public UserEntity(
        String name,
        String surname,
        String password,
        String username,
        LocalDate birthday,
        String country,
        Double money,
        List<UserEntity> friendList,
        String imageURL
){
    this.name = name;
    this.surname = surname;
    this.password = password;
    this.username = username;
    this.birthday = birthday;
    this.country = country;
    this.money = money;
    this.friendList = friendList;
    this.imageURL = imageURL;
}

public int getUserAge () {
    return Period.between(this.birthday, LocalDate.now()).getYears();
}

public void payLodging (Lodging lodging, LocalDate startDate,LocalDate endDate ){

    double amountToPay = lodging.priceCalculationBetweenDates(startDate,endDate);

    if(this.hasEnoughMoney(amountToPay)){
        this.money = this.money - amountToPay;
    }else{
        throw new BusinessException("No hay dinero suficiente para reservar el alojamiento");
    }
}

public boolean hasEnoughMoney(Double amountToPay){
    return this.money >= amountToPay;
}

public void addMoney(Double money){
    this.money += money;
}

public void changeBirthday(LocalDate newBirthday){
    this.birthday = newBirthday;
}

public void addFriend(UserEntity friend){
    this.friendList.add(friend);
}

public void removeFriend(UserEntity friend){
    if(!this.friendList.contains(friend)){
        throw new BusinessException("El usuario con ID: ${this.id} no es amigo del usuario con ID: " + friend.id);
    }
    this.friendList.remove(friend);
}

}
