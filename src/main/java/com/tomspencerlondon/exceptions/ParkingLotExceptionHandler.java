package com.tomspencerlondon.exceptions;

import java.util.Collections;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ParkingLotExceptionHandler {

  @ExceptionHandler(value = VehicleNotFoundException.class)
  public ResponseEntity<?> handleVehicleNotFoundException(VehicleNotFoundException exception) {
    ResponseErrorDto errorDto = ResponseErrorDto.builder()
        .timeStamp(new Date())
        .errorMessages(Collections.singletonList(exception.getMessage()))
        .build();

    log.info("Error Response is: {}", errorDto);

    return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = VehicleNotFoundRuntimeException.class)
  public ResponseEntity<?> handleVehicleNotFoundRuntimeException(VehicleNotFoundRuntimeException exception) {
    ResponseErrorDto errorDto = ResponseErrorDto.builder()
        .timeStamp(new Date())
        .errorMessages(Collections.singletonList(exception.getMessage()))
        .build();

    log.info("Error Response is: {}", errorDto);

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<?> handleRunTimeException(RuntimeException exception) {
    ResponseErrorDto errorDto = ResponseErrorDto.builder()
        .timeStamp(new Date())
        .errorMessages(Collections.singletonList(exception.getMessage()))
        .build();
    log.info("Error Response is: {}", errorDto);

    return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<?> handleException(Exception exception) {
    ResponseErrorDto errorDto = ResponseErrorDto.builder()
        .timeStamp(new Date())
        .errorMessages(Collections.singletonList(exception.getMessage()))
        .build();
    log.info("Error Response is: {}", errorDto);

    return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
  }


}
