package com.tomspencerlondon.service;

import com.tomspencerlondon.entity.VehicleDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ParkingLotJpaService {

  String createVehicle(VehicleDto vehicleDto);

  List<String> getVehicles();

  Page<VehicleDto> getPageableVehicles(int page, int size);

  String updateVehicle(VehicleDto updateVehicle) throws Exception;

  String unpark(Integer id);

  List<VehicleDto> getBookStoresSortedByName();

  Long countNumberOfVehicles();

  Boolean vehicleExistsById(Integer id);

  List<VehicleDto> getVehiclesWithVehicleOwner(String vehicleType);

  ResponseEntity<String> deleteAllVehicles();
}
