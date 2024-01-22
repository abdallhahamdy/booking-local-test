package com.AlTaraf.Booking.mapper;


import com.AlTaraf.Booking.entity.ERole;
import com.AlTaraf.Booking.entity.Role;
import com.AlTaraf.Booking.entity.User;
import com.AlTaraf.Booking.dto.UserRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "city.cityName", target = "city.cityName")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRoleSet")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "name", target = "username")
    @Mapping(source = "email", target = "email")
    User userRegisterDtoToUser(UserRegisterDto userRegisterDto);

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "city.cityName", target = "city.cityName")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleToRoleDtoList")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "username", target = "name")
    @Mapping(source = "email", target = "email")
    UserRegisterDto userToUserRegisterDto(User user);

    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "city.cityName", target = "city.cityName")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRoleSet")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "name", target = "username")
    @Mapping(source = "email", target = "email")
    List<User> userRegisterDtoListToUserList(List<UserRegisterDto> userRegisterDtoList);

    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "city.cityName", target = "city.cityName")
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleToRoleDtoList")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "username", target = "name")
    @Mapping(source = "email", target = "email")
    List<UserRegisterDto> userListToUserRegisterDtoList(List<User> userList);

    @Named("roleToRoleDtoList")
    static Set<String> roleToRoleDtoList(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
    }

    @Named("rolesToRoleSet")
    static Set<Role> rolesToRoleSet(Set<String> roles) {
        return roles.stream()
                .map(roleName -> {
                    Role role = new Role();
                    role.setName(ERole.valueOf(roleName));
                    return role;
                })
                .collect(Collectors.toSet());
    }
}