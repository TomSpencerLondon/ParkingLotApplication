package com.tomspencerlondon.controller;

import com.tomspencerlondon.entity.VehicleDto;
import com.tomspencerlondon.repository.VehicleRepository;
import com.tomspencerlondon.service.ParkingLotJpaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dbApis")
public class ParkingLotJpaController {

  @Autowired
  VehicleRepository vehicleRepository;

  @Autowired
  ParkingLotJpaService jpaService;

  @PostMapping("/createVehicle")
  public String createVehicle(VehicleDto vehicleDto) {
    return jpaService.createVehicle(vehicleDto);
  }

  @GetMapping("/getVehicles")
  public List<String> getVehicles() {
    return jpaService.getVehicles();
  }

  @GetMapping("/pageableVehicles")
  public Page<VehicleDto> getPageableVehicles(int page, int size) {
    return jpaService.getPageableVehicles(page, size);
  }

  @PutMapping("/updateVehicle")
  public String updateVehicle(VehicleDto updateVehicle) throws Exception {
    return jpaService.updateVehicle(updateVehicle);
  }

  @DeleteMapping("/unpark")
  public String unpark(Integer id) {
    return jpaService.unpark(id);
  }

  @GetMapping("/vehiclesSortedByName")
  public List<VehicleDto> getBookStoresSortedByName() {
    return jpaService.getBookStoresSortedByName();
  }

  @GetMapping("/countNumberOfVehicles")
  public Long countNumberOfVehicles() {
    return jpaService.countNumberOfVehicles();
  }

  @GetMapping("/vehicleIdExists")
  public Boolean vehicleExistsById(Integer id) {
    return jpaService.vehicleExistsById(id);
  }

  @GetMapping("/vehiclesByType")
  public List<VehicleDto> getVehiclesWithVehicleOwner(String vehicleType) {
    return jpaService.getVehiclesWithVehicleOwner(vehicleType);
  }

  @DeleteMapping("/deleteAllVehicles")
  ResponseEntity<String> deleteAllVehicles() {
    return jpaService.deleteAllVehicles();
  }
}
