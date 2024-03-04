package com.fakestore.user.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String email;
    private String hashedPassword;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Name name;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;
    private String phone;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Role> roleList;
    private boolean isEmailVerified;
}
