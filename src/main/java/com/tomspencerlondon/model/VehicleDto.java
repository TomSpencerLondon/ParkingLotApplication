package com.tomspencerlondon.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDto {
  @Id
  private int id;
  private String vehicleType;
  private String vehicleNumber;
  private String vehicleOwnerName;
}

