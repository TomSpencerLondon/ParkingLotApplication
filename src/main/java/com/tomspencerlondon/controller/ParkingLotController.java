package com.tomspencerlondon.controller;

import com.tomspencerlondon.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ParkingLotController {

  //	@Qualifier("parkingLotServiceImpl")
  @Autowired
  ParkingLotService parkingLotServiceImpl;

  @GetMapping("/")
  public String helloApi() {
    return parkingLotServiceImpl.helloApi();
  }
}