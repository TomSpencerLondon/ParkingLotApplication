package com.tomspencerlondon.controller;

import com.tomspencerlondon.model.VehicleDto;
import com.tomspencerlondon.service.ParkingLotService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ParkingLotController {

  //	@Qualifier("parkingLotServiceImpl")
  @Autowired
  ParkingLotService parkingLotServiceImpl;
  List<VehicleDto> vehicleDtos = new ArrayList<>(10);

  @GetMapping("/")
  public String helloApi() {
    return parkingLotServiceImpl.helloApi();
  }

  @PostMapping("/park")
  public String parkVehicle(@RequestBody VehicleDto vehicleDto) {
    vehicleDtos.add(vehicleDto);

    return "Vehicle parked on parking slot: ";
//        vehicleDto.getId();
  }

  @PostMapping("/unpark")
  public ResponseEntity<?> unParkVehicle(int parkingSlot) {
    if (parkingSlot >= 10 || parkingSlot < 0) {
      return new ResponseEntity<>(new RuntimeException("Please use parking slot between 1 and 10"), HttpStatus.NOT_ACCEPTABLE);
    }

//    try {
//      System.out.println(vehicleDtos.get(parkingSlot));
//    } catch (Exception e) {
//      return "There is no vehicle with this parking slot id: " + parkingSlot;
//    }
//
//    vehicleDtos.remove(parkingSlot);

    return new ResponseEntity<>("Vehicle unparked at parking slot: " + parkingSlot, HttpStatus.OK);
  }

// Experiment
//  @PostMapping("/park/{id}/{vehicleType}/{vehicleNumber}/{vehicleOwnerName}")
//  public String parkVehicle(@PathVariable int id, @PathVariable String vehicleType, @PathVariable String vehicleNumber, @PathVariable String vehicleOwnerName) {
//    vehicleDtos.add(new VehicleDto(id, vehicleType, vehicleNumber, vehicleOwnerName));
//
//    return "Vehicle parked on parking slot: " + id;
//  }


  @GetMapping("/check")
  public Boolean parkingSlotAvailable(int parkingId) {
    try {
      VehicleDto vehicleDto = vehicleDtos.get(parkingId);
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  @PutMapping("/update")
  public String updateParkedVehicleNumber(int parkingId, String vehicleNumber) {
    try {
      VehicleDto vehicleDto = vehicleDtos.get(parkingId);
//      vehicleDto.setVehicleNumber(vehicleNumber);
      vehicleDtos.set(parkingId, vehicleDto);
    } catch (Exception e) {
      return "Failed to update";
    }
    return "updated vehicle parked at: " + parkingId + " with vehicleNumber " + vehicleNumber;
  }

  @PostMapping("/dumpVehicles")
  public VehicleDto dumpVehicles() {
    return VehicleDto.builder()
        .id(1)
        .vehicleType("mercedes")
        .vehicleNumber("123")
        .vehicleOwnerName("Tom")
        .build();
  }

}