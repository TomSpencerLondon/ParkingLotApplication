package com.tomspencerlondon.repository;

import com.tomspencerlondon.entity.VehicleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleDto, Integer> {

}