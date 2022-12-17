package com.tomspencerlondon.serviceimpl;

import com.tomspencerlondon.exceptions.VehicleNotFoundRuntimeException;
import com.tomspencerlondon.entity.VehicleDto;
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
import org.springframework.stereotype.Service;

@Service
public class ParkingLotJpaServiceImpl implements com.tomspencerlondon.service.ParkingLotJpaService {

  @Autowired
  VehicleRepository vehicleRepository;

  @Override
  public String createVehicle(VehicleDto vehicleDto) {
    vehicleRepository.saveAndFlush(vehicleDto);
    return vehicleDto.toString();
  }

  @Override
  public List<String> getVehicles() {
    PageRequest pageable = PageRequest.of(0, 2);
    return vehicleRepository
        .findAll(pageable)
        .map(VehicleDto::toString)
        .stream()
        .collect(Collectors.toList());
  }

  @Override
  public Page<VehicleDto> getPageableVehicles(int page, int size) {
    PageRequest pageable = PageRequest.of(page, size);
    return vehicleRepository
        .findAll(pageable);
  }

  @Override
  public String updateVehicle(VehicleDto updateVehicle) throws Exception {
    Optional<VehicleDto> vehicleDto = vehicleRepository.findById(updateVehicle.getId());
    return vehicleDto.map(v -> {
          v.setVehicleType(updateVehicle.getVehicleType());
          v.setVehicleOwnerName(updateVehicle.getVehicleOwnerName());
          v.setVehicleNumber(updateVehicle.getVehicleNumber());
          vehicleRepository.saveAndFlush(v);
          return v;
        }).map(VehicleDto::toString)
        .orElseThrow(() -> new VehicleNotFoundRuntimeException("Vehicle not found"));
  }

  @Override
  public String unpark(Integer id) {
    Optional<VehicleDto> vehicleDto = vehicleRepository.findById(id);
    return vehicleDto.map(v -> {
      vehicleRepository.deleteById(v.getId());
      return v;
    }).map(VehicleDto::toString).orElse("Not found");
  }

  @Override
  public List<VehicleDto> getBookStoresSortedByName() {
    return vehicleRepository.findAll(Sort.by(Direction.ASC, "vehicleOwnerName"));
  }

  @Override
  public Long countNumberOfVehicles() {
    return vehicleRepository.count();
  }

  @Override
  public Boolean vehicleExistsById(Integer id) {
    return vehicleRepository.existsById(id);
  }

  @Override
  public List<VehicleDto> getVehiclesWithVehicleOwner(String vehicleType) {
    ExampleMatcher exampleMatcher = ExampleMatcher.matching()
        .withMatcher("vehicleType",
            ExampleMatcher.GenericPropertyMatchers.contains())
        .withIgnorePaths("id", "vehicleOwnerName", "vehicleNumber");
    VehicleDto vehicleExample = VehicleDto.builder().vehicleType(vehicleType).build();
    Example<VehicleDto> example = Example.of(vehicleExample, exampleMatcher);
    return vehicleRepository.findAll(example);
  }

  @Override
  public ResponseEntity<String> deleteAllVehicles() {
    vehicleRepository.deleteAll();
    return new ResponseEntity<>("Successful deletion", HttpStatus.OK);
  }

}
