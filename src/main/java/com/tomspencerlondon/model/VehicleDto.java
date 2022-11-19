package com.tomspencerlondon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleDto {
  private int id;
  private String vehicleType;
  private String vehicleNumber;
  private String vehicleOwnerName;
}

