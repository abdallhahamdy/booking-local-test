package com.AlTaraf.Booking.repository;

import com.AlTaraf.Booking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String roleName);
    Optional<Role> findByArabicRoleName(String arabicRoleName);

}