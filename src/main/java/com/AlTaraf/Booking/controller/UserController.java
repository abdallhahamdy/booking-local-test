package com.AlTaraf.Booking.controller;

import com.AlTaraf.Booking.dto.UserRegisterDto;
import com.AlTaraf.Booking.entity.User;
import com.AlTaraf.Booking.entity.enums.ERole;
import com.AlTaraf.Booking.payload.request.LoginRequest;
import com.AlTaraf.Booking.payload.request.PasswordResetDto;
import com.AlTaraf.Booking.payload.response.ApiResponse;
import com.AlTaraf.Booking.payload.response.AuthenticationResponse;
import com.AlTaraf.Booking.payload.response.CheckApiResponse;
import com.AlTaraf.Booking.payload.response.JwtResponse;
import com.AlTaraf.Booking.security.jwt.JwtUtils;
import com.AlTaraf.Booking.security.service.UserDetailsImpl;
import com.AlTaraf.Booking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/Send-OTP")
    public ResponseEntity<?> sendOTP() {

        // Generate and send OTP (you need to implement this logic)
        String otp = userService.generateOtpForUser();
        if (otp != null ) {
            AuthenticationResponse response = new AuthenticationResponse(200, "OTP Sent successfully!", otp);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(404, "Not Found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/check-availability")
    public ResponseEntity<?> checkAvailability(@RequestParam(value = "email") String email,
                                               @RequestParam(value = "phone") String phone,
                                               @RequestParam(value = "roleNames") Set<String> roleNames) {

        Set<ERole> roleNamesSet = roleNames.stream().map(ERole::valueOf).collect(Collectors.toSet());

        boolean existsByEmailAndRolesOrPhoneNumberAndRoles = userService.existsByEmailAndRolesOrPhoneNumberAndRoles(email, phone, roleNamesSet);        boolean isEmailAvailable = userService.existsByEmail(email);
        boolean isPhoneAvailable = userService.existsByPhone(phone);


        if (existsByEmailAndRolesOrPhoneNumberAndRoles) {
            CheckApiResponse response = new CheckApiResponse(204, "User with the same email, phone number, and role already exists.", false);

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(response);
        } else if (isPhoneAvailable){

            CheckApiResponse response = new CheckApiResponse(204, "Phone is already taken.", false);

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(response);
        } else if (isEmailAvailable){
            CheckApiResponse response = new CheckApiResponse(204, "Email is already taken.", false);

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(response);
        }


        CheckApiResponse response = new CheckApiResponse(200, "", true);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/Register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {

        // Perform user registration
        userService.registerUser(userRegisterDto);

        ApiResponse response = new ApiResponse(200, "User Registered Successfully!");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                userDetails.getCity(),
                roles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            ApiResponse response = new ApiResponse(404, "Not Found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/forget-password/{phone}")
    public ResponseEntity<?> resetPassword(
            @PathVariable String phone,
            @RequestBody PasswordResetDto passwordResetDto) {

        // Perform password reset
        try {
            userService.resetPasswordByPhone(phone, passwordResetDto);
            return ResponseEntity.ok(new ApiResponse(200, "Password reset successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, "Failed to reset password: " + e.getMessage()));
        }

    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRegisterDto userRegisterDto) {
//        try {
//            userService.updateUser(id, userRegisterDto);
//            return ResponseEntity.ok("User updated successfully!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error updating user: " + e.getMessage());
//        }
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserRegisterDto> getUserById(@PathVariable Long id) {
//        UserRegisterDto userDto = userService.getUserById(id);
//        return userDto != null
//                ? ResponseEntity.ok(userDto)
//                : ResponseEntity.notFound().build();
//    }

//    @GetMapping("/all")
//    public ResponseEntity<?> getAllUsers() {
//        List<UserRegisterDto> users = userService.getAllUsers();
//        if (!users.isEmpty()) {
//            return ResponseEntity.ok(users);
//        } else {
//            ApiResponse response = new ApiResponse(204, "No Content for Roles!");
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
//        }
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//        try {
//            userService.deleteUser(id);
//            ApiResponse response = new ApiResponse(200, "Role deleted successfully!");
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } catch (Exception e) {
//            ApiResponse response = new ApiResponse(404, "Not Found!");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//        }

    }