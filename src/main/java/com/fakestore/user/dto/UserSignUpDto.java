package com.fakestore.user.dto;

import com.fakestore.user.models.Address;
import com.fakestore.user.models.Name;
import com.fakestore.user.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSignUpDto {
    private String email;
    private String hashedPassword;
    private Name name;
    private Address address;
    private String phone;
    private List<Role> roleList;
    private boolean isEmailVerified;
}
