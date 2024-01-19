package com.fakestore.user.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel{
    private String city;
    private String street;
    private String number;
    private String zipcode;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private Geolocation geolocation;
}
