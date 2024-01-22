package com.AlTaraf.Booking.controller;

import com.AlTaraf.Booking.dto.RoleDto;
import com.AlTaraf.Booking.entity.Role;
import com.AlTaraf.Booking.mapper.RoleMapper;
import com.AlTaraf.Booking.payload.response.ApiResponse;
import com.AlTaraf.Booking.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RoleService roleService;


    // Get a role by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        Role role = roleService.getRoleById(id);
        if (role != null) {
            RoleDto roleDto = RoleMapper.INSTANCE.roleToRoleDto(role);
            return ResponseEntity.ok(roleDto);
        } else {
            ApiResponse response = new ApiResponse(404, "Not Found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Get all roles
    @GetMapping("/all")
    public ResponseEntity<?> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        if (!roles.isEmpty()) {
            List<RoleDto> roleDtos = RoleMapper.INSTANCE.rolesToRoleDtos(roles);
            return ResponseEntity.ok(roleDtos);
        } else {
            ApiResponse response = new ApiResponse(204, "No Content for Roles!");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
    }

}

