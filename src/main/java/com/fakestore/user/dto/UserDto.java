package com.fakestore.user.dto;

import com.fakestore.user.models.Role;
import com.fakestore.user.models.User;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    @ManyToMany
    private List<Role> roleList;
    private boolean isEmailVerified;

    public static UserDto from(User user){
        if(user == null) return null;
        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.name = user.getName().getFirstName() + " " + user.getName().getLastName();
        userDto.roleList = user.getRoleList();
        userDto.isEmailVerified = user.isEmailVerified();
        return userDto;
    }
}
