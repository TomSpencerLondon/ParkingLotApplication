package com.tomspencerlondon.serviceimpl;

import com.tomspencerlondon.service.ParkingLotService;
import org.springframework.stereotype.Service;

//@Primary
@Service
public class ParkingLotServiceImpl implements ParkingLotService {
  @Override
  public String helloApi() {
    return "hello1";
  }
}