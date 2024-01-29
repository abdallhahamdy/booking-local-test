package com.AlTaraf.Booking.controller;

import com.AlTaraf.Booking.dto.CityDto;
import com.AlTaraf.Booking.mapper.CityMapper;
import com.AlTaraf.Booking.payload.response.ApiResponse;
import com.AlTaraf.Booking.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    CityMapper cityMapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCities() {
        List<CityDto> cities = cityService.getAllCities();
        if (cities.isEmpty()) {
            ApiResponse response = new ApiResponse(204, "No Content for Cities!");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            return ResponseEntity.ok(cities);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
//        CityDto cityDto = cityService.getCityById(id);
//        if (cityDto != null) {
//            return ResponseEntity.ok(cityDto);
//        } else {
//            ApiResponse response = new ApiResponse(404, "Not Found!");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }

//    @PostMapping("/create")
//    public ResponseEntity<?> createCity(@RequestBody CityDto cityDto) {
//        cityService.saveCity(cityDto);
//        ApiResponse response = new ApiResponse(200, "Added City successfully!");
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody CityDto cityDto) {
//        CityDto existingCity = cityService.getCityById(id);
//
//        if (existingCity != null) {
//            existingCity.setCityName(cityDto.getCityName());  // Assuming cityName property in CityDto
//            existingCity.setArabicCityName(cityDto.getArabicCityName());
//            cityService.saveCity(existingCity);
//            ApiResponse response = new ApiResponse(205, "Reset Content successfully!");
//
//            return ResponseEntity.ok(response);
//        } else {
//            ApiResponse response = new ApiResponse(404, "Not Found!");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteCity(@PathVariable Long id) {
//        CityDto existingCity = cityService.getCityById(id);
//
//        if (existingCity != null) {
//            cityService.deleteCity(id);
//            ApiResponse response = new ApiResponse(200, "Role deleted successfully!");
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } else {
//            ApiResponse response = new ApiResponse(404, "Not Found!");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }
}
