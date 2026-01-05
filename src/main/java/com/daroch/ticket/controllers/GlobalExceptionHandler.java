package com.daroch.ticket.controllers;

import com.daroch.ticket.dto.ErrorDto;
import com.daroch.ticket.exceptions.BusinessException;
import com.daroch.ticket.exceptions.TicketNotFoundException;
import com.daroch.ticket.exceptions.TicketTypeNotFoundException;
import com.daroch.ticket.exceptions.TicketsSoldOutException;
import com.daroch.ticket.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
// Tells Spring this class will globally handle exceptions for REST controllers
@Slf4j // Adds a logger named 'log' using Lombok
public class GlobalExceptionHandler {
  /**
   * Handles cases where a TicketNotFoundException cannot be found.
   *
   * @param ex the exception indicating the QR code is missing
   * @return an error response with HTTP 404
   */
  @ExceptionHandler(TicketNotFoundException.class)
  public ResponseEntity<ErrorDto> handleTicketNotFoundException(TicketNotFoundException ex) {
    log.error("Caught TicketNotFoundException", ex);

    ErrorDto errorDto = new ErrorDto();
    errorDto.setError("Ticket not found not found");

    return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles situations where all tickets for an event have been sold.
   *
   * @param ex the exception indicating no tickets remain
   * @return an error response with HTTP 400
   */
  @ExceptionHandler(TicketsSoldOutException.class)
  public ResponseEntity<ErrorDto> handleTicketsSoldOutException(TicketsSoldOutException ex) {
    log.error("Caught TicketsSoldOutException", ex);

    ErrorDto errorDto = new ErrorDto();
    errorDto.setError("All tickets are sold out");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles failures that occur during Business logic fails.
   *
   * @param ex the exception indicating QR code generation has failed
   * @return an error response with HTTP 500
   */
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorDto> handleBusinessException(BusinessException ex) {
    log.error("Caught BusinessException", ex);

    ErrorDto errorDto = new ErrorDto();
    errorDto.setError("Business exception caught");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles cases where a requested ticket type does not exist.
   *
   * @param ex the exception indicating the ticket type was not found
   * @return an error response with HTTP 400
   */
  @ExceptionHandler(TicketTypeNotFoundException.class)
  public ResponseEntity<ErrorDto> handleTicketTypeNotFoundException(
      TicketTypeNotFoundException ex) {
    log.error("Caught TicketTypeNotFoundException", ex);

    ErrorDto errorDto = new ErrorDto();
    errorDto.setError("Ticket type not found");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles cases where a requested user does not exist.
   *
   * @param ex the exception indicating the user was not found
   * @return an error response with HTTP 400
   */
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
    log.error("Caught UserNotFoundException", ex);

    ErrorDto errorDto = new ErrorDto();
    errorDto.setError("User not found");

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles any unexpected or unhandled exceptions.
   *
   * @param ex the exception that was not caught by other handlers
   * @return an error response with HTTP 500
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception ex) {
    log.error("Caught exception", ex);

    ErrorDto errorDto = new ErrorDto();
    errorDto.setError("An unknown error occurred");

    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
