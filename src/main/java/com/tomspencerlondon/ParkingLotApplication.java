package com.tomspencerlondon;

import com.tomspencerlondon.entity.VehicleDto;
import com.tomspencerlondon.repository.VehicleRepository;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class ParkingLotApplication implements CommandLineRunner {

	@Autowired
	VehicleRepository vehicleRepository;

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

	@Override
	public void run(String... args) throws Exception {
		VehicleDto mercedesCar = VehicleDto
				.builder()
				.id(1).vehicleType("Mercedes")
				.vehicleNumber("1231")
				.vehicleOwnerName("Tom Spencer")
				.build();

		VehicleDto ferrariCar = VehicleDto
				.builder()
				.id(2)
				.vehicleType("Ferrari")
				.vehicleNumber("123")
				.vehicleOwnerName("Samarth Narula")
				.build();

		vehicleRepository.saveAndFlush(mercedesCar);
		vehicleRepository.saveAndFlush(ferrariCar);
	}
}
