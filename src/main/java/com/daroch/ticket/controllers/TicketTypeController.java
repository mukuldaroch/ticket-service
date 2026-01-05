package com.daroch.ticket.controllers;

import com.daroch.ticket.dto.tickettype.request.CreateTicketTypeRequest;
import com.daroch.ticket.dto.tickettype.response.CreateTicketTypeResponse;
import com.daroch.ticket.mappers.TicketTypeMapper;
import com.daroch.ticket.services.TicketTypeService;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket-type")
@RequiredArgsConstructor
public class TicketTypeController {

  private final TicketTypeMapper ticketTypeMapper;
  private final TicketTypeService ticketTypeService;

  @PostMapping
  public ResponseEntity<CreateTicketTypeResponse> createTicket(
      @Valid @RequestBody CreateTicketTypeRequest ticketTypeRequest) {

    CreateTicketTypeCommand ticketTypeCommand = ticketTypeMapper.toCommand(ticketTypeRequest);

    CreateTicketTypeResponse response = ticketTypeService.createTicketType(ticketTypeCommand);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
