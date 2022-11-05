package com.tomspencerlondon;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class ParkingLotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("EMS API").version(appVersion)
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}

@RestController
@CrossOrigin(origins = "*")
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
