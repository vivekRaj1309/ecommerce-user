package com.fakestore.user.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String username;
    private String email;
    private String password;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Name name;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    private String phone;
}
