package com.AlTaraf.Booking.service;

import com.AlTaraf.Booking.dto.RoleDto;
import com.AlTaraf.Booking.entity.ERole;
import com.AlTaraf.Booking.entity.Role;
import com.AlTaraf.Booking.exception.RoleNotFoundException;
import com.AlTaraf.Booking.mapper.RoleMapper;
import com.AlTaraf.Booking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role getRoleByName(ERole name) {
//        return roleRepository.findByRole(roleName).orElse(null);
        return roleRepository.findByName(name).orElse(null);
    }

//    @Override
//    public Role createRole(RoleDto roleDto) {
//        Role role = roleMapper.roleDtoToRole(roleDto);
//        return roleRepository.save(role);
//    }

//    @Override
//    public Role updateRole(Long id, RoleDto roleDto) {
//        Role existingRole = roleRepository.findById(id).orElse(null);
//
//        if (existingRole != null) {
//            // Update the existing role
//            existingRole.setRole(roleDto.getRoleNameDto()); // Assuming roleName property in RoleDto
//            return roleRepository.save(existingRole);
//        } else {
//            // Handle case where the role with the given id is not found
//            throw new RoleNotFoundException("Role with id " + id + " not found");
//        }
//    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

//    @Override
//    public boolean deleteRole(Long id) {
//        if (roleRepository.existsById(id)) {
//            roleRepository.deleteById(id);
//            return true;
//        } else {
//            // Handle case where the role with the given id is not found
//            return false;
//        }
//    }
}

