package com.tomspencerlondon.serviceimpl;

import com.tomspencerlondon.service.ParkingLotService;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl2 implements ParkingLotService {
  @Override
  public String helloApi() {
    return "hello2";
  }
}