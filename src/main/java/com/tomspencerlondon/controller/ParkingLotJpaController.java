package com.tomspencerlondon.controller;

import com.tomspencerlondon.model.VehicleDto;
import com.tomspencerlondon.repository.VehicleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dbApis")
public class ParkingLotJpaController {

  @Autowired
  VehicleRepository vehicleRepository;

  @PostMapping("/createVehicle")
  public String createVehicle(VehicleDto vehicleDto) {
    vehicleRepository.saveAndFlush(vehicleDto);
    return vehicleDto.toString();
  }

//  Homework (26/11/22)
// 1. Create api to check records in vehicle dto [done]
// 2. Create api to update objects that exist in the database
// 3. Study about:
// - composite primary keys in database
// - what is indexing in the database?
// - How does indexing work?
// - Internal working of database server
// - How database store data in disk
//  - stores data as blocks on hard disk
//  + reads and loads data from ram to ui
//  - What is snapshotting
// - Isolation levels - database [very advance]
// -

  @GetMapping("/getVehicles")
  public List<String> getVehicles() {
    PageRequest pageable = PageRequest.of(0, 2);
    return vehicleRepository
        .findAll(pageable)
        .map(VehicleDto::toString)
        .stream()
        .collect(Collectors.toList());
  }

  @GetMapping("/pageableVehicles")
  public Page<VehicleDto> getPageableVehicles(int page, int size) {
    PageRequest pageable = PageRequest.of(page, size);
    return vehicleRepository
        .findAll(pageable);
  }

  @PostMapping("/updateVehicle")
  public String updateVehicle(VehicleDto updateVehicle) {
    Optional<VehicleDto> vehicleDto = vehicleRepository.findById(updateVehicle.getId());
    return vehicleDto.map(v -> {
      v.setVehicleType(updateVehicle.getVehicleType());
      v.setVehicleOwnerName(updateVehicle.getVehicleOwnerName());
      v.setVehicleNumber(updateVehicle.getVehicleNumber());
      vehicleRepository.saveAndFlush(v);
      return v;
    }).map(VehicleDto::toString).orElse("Not found");
  }

  @DeleteMapping("/unpark")
  public String unpark(Integer id) {
    Optional<VehicleDto> vehicleDto = vehicleRepository.findById(id);
    return vehicleDto.map(v -> {
      vehicleRepository.deleteById(v.getId());
      return v;
    }).map(VehicleDto::toString).orElse("Not found");
  }

  @GetMapping("/vehiclesSortedByName")
  public List<VehicleDto> getBookStoresSortedByName() {
    return vehicleRepository.findAll(Sort.by(Direction.ASC, "vehicleOwnerName"));
  }

  @GetMapping("/countNumberOfVehicles")
  public Long countNumberOfVehicles() {
    return vehicleRepository.count();
  }

  @GetMapping("/vehicleIdExists")
  public Boolean vehicleExistsById(Integer id) {
    return vehicleRepository.existsById(id);
  }

//  Homework: 4/12/22
//  1. Get Vehicles by vehicle type
//  2. Truncate deleteAll endpoint

  @GetMapping("/vehiclesByType")
  public List<VehicleDto> getVehiclesWithVehicleOwner(String vehicleType) {
    ExampleMatcher exampleMatcher = ExampleMatcher.matching()
        .withMatcher("vehicleType",
            ExampleMatcher.GenericPropertyMatchers.contains())
        .withIgnorePaths("id", "vehicleOwnerName", "vehicleNumber");
    VehicleDto vehicleExample = VehicleDto.builder().vehicleType(vehicleType).build();
    Example<VehicleDto> example = Example.of(vehicleExample, exampleMatcher);
    return vehicleRepository.findAll(example);
  }

  @DeleteMapping("/deleteAllVehicles")
  ResponseEntity<String> deleteAllVehicles() {
    vehicleRepository.deleteAll();
    return new ResponseEntity<>("Successful deletion", HttpStatus.OK);
  }
}
