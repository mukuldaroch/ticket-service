package com.daroch.ticket.controllers;

import com.daroch.ticket.dtos.ticket.request.CreateTicketRequestDto;
import com.daroch.ticket.dtos.ticket.response.CreateTicketResponseDto;
import com.daroch.ticket.mappers.TicketMapper;
import com.daroch.ticket.mappers.TicketTypeMapper;
import com.daroch.ticket.services.TicketCommandService;
import com.daroch.ticket.services.TicketQueryService;
import com.daroch.ticket.services.commands.ticket.CreateTicketCommand;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tickets")
@RequiredArgsConstructor
public class TicketController {

  private final TicketMapper ticketMapper;
  private final TicketQueryService ticketQueryService;
  private final TicketCommandService ticketCommandService;

  private final TicketTypeMapper ticketTypeMapper;

  /**
   * POST /events Creates a new event for the authenticated organizer.
   *
   * @param createTicketRequestDto incoming event creation payload
   * @return 201 Created with event details
   */
  @PostMapping()
  public ResponseEntity<CreateTicketResponseDto> createTicket(
      @AuthenticationPrincipal Jwt jwt,
      @Valid @RequestBody CreateTicketRequestDto createTicketRequestDto) {

    UUID organizerId = UUID.fromString(jwt.getSubject());

    CreateTicketCommand ticketCommand = ticketMapper.toCommand(createTicketRequestDto);

    CreateTicketResponseDto responseDto =
        ticketCommandService.createTicket(organizerId, ticketCommand);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
  }
}
