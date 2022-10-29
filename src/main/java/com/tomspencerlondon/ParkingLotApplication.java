package com.tomspencerlondon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ParkingLotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);
	}

}

@RestController
class ParkingLotController {

//	@Qualifier("parkingLotServiceImpl")
	@Autowired
	ParkingLotService parkingLotServiceImpl;

	@GetMapping("/")
	public String helloApi() {
		return parkingLotServiceImpl.helloApi();
	}
}

//@Primary
@Service
class ParkingLotServiceImpl implements ParkingLotService {
	@Override
	public String helloApi() {
		return "hello1";
	}
}

@Service
class ParkingLotServiceImpl2 implements ParkingLotService {
	@Override
	public String helloApi() {
		return "hello2";
	}
}
