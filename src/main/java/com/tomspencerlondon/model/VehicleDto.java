package com.tomspencerlondon.model;

public class VehicleDto {
  private int id;
  private String vehicleType;
  private String vehicleNumber;
  private String vehicleOwnerName;

  public VehicleDto(int id, String vehicleType, String vehicleNumber, String vehicleOwnerName) {
    this.id = id;
    this.vehicleType = vehicleType;
    this.vehicleNumber = vehicleNumber;
    this.vehicleOwnerName = vehicleOwnerName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getVehicleOwnerName() {
    return vehicleOwnerName;
  }

  public void setVehicleOwnerName(String vehicleOwnerName) {
    this.vehicleOwnerName = vehicleOwnerName;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
  }


  public String getVehicleNumber() {
    return vehicleNumber;
  }

  public void setVehicleNumber(String vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
  }


  @Override
  public String toString() {
    return "VehicleDto{" + "id=" + id + ", vehicleType='" + vehicleType + '\'' + ", vehicleNumber='" + vehicleNumber + '\'' + ", vehicleOwnerName='" + vehicleOwnerName + '\'' + '}';
  }
}

