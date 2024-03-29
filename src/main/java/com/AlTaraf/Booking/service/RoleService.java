package com.AlTaraf.Booking.service;


import com.AlTaraf.Booking.entity.Role;
import com.AlTaraf.Booking.entity.enums.ERole;

import java.util.List;

public interface RoleService {
//    Role getRoleByName(String roleName);
//    Role createRole(RoleDto roleDto); // Add this method
//    Role updateRole(Long id, RoleDto roleDto);
    Role getRoleById(Long id);
    List<Role> getAllRoles();
//    boolean deleteRole(Long id);

    Role getRoleByName(ERole name);
}

