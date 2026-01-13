package com.daroch.ticket.controllers;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.dto.tickettype.request.CreateTicketTypeRequest;
import com.daroch.ticket.dto.tickettype.request.UpdateTicketTypeRequest;
import com.daroch.ticket.dto.tickettype.response.CreateTicketTypeResponse;
import com.daroch.ticket.dto.tickettype.response.TicketTypeResponse;
import com.daroch.ticket.dto.tickettype.response.UpdateTicketTypeResponse;
import com.daroch.ticket.mappers.TicketTypeMapper;
import com.daroch.ticket.services.TicketTypeService;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for managing Ticket Types.
 *
 * <p>Ticket types define the pricing and availability categories for an event (for example: VIP,
 * Regular, Early Bird). This controller exposes endpoints to create, update and delete ticket
 * types.
 *
 * <p>The controller follows a layered architecture:
 *
 * <ul>
 *   <li>HTTP request → DTO
 *   <li>DTO → Command (via {@link TicketTypeMapper})
 *   <li>Command → Business logic (via {@link TicketTypeService})
 *   <li>Business result → Response DTO
 * </ul>
 */
@RestController
@RequestMapping("/ticket-types")
@RequiredArgsConstructor
public class TicketTypeController {

  /** Maps API request/response objects to domain commands and vice versa. */
  private final TicketTypeMapper ticketTypeMapper;

  /** Handles business logic related to ticket type operations. */
  private final TicketTypeService ticketTypeService;

  /**
   * Creates a new ticket type.
   *
   * <p>This endpoint is used by event organizers to define a new ticket category for an event (for
   * example: "VIP", "Balcony", "Student").
   *
   * @param req the incoming HTTP request containing ticket type details
   * @return a {@link ResponseEntity} containing the created ticket type and HTTP status {@code 201
   *     (CREATED)}
   */
  @PostMapping
  public ResponseEntity<CreateTicketTypeResponse> createTicketType(
      @Valid @RequestBody CreateTicketTypeRequest req) {

    CreateTicketTypeCommand cmd = ticketTypeMapper.toCreateCommand(req);

    CreateTicketTypeResponse response = ticketTypeService.createTicketType(cmd);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Get a new ticket type.
   *
   * <p>This endpoint is used by event organizers to define a new ticket category for an event (for
   * example: "VIP", "Balcony", "Student").
   *
   * @param req the incoming HTTP request containing ticket type details
   * @return a {@link ResponseEntity} containing the created ticket type and HTTP status {@code 201
   *     (CREATED)}
   */
  @GetMapping(path = "/{eventId}")
  public ResponseEntity<TicketTypeResponse> getTicketType(@PathVariable UUID eventId) {

    TicketType ticketType = ticketTypeService.getTicketType(eventId);

    return ResponseEntity.ok(ticketTypeMapper.toResponse(ticketType));
  }

  /**
   * Updates an existing ticket type.
   *
   * <p>This endpoint allows modification of ticket type properties such as price, name, or
   * availability limits.
   *
   * @param req the incoming HTTP request containing updated ticket type data
   * @return a {@link ResponseEntity} containing the updated ticket type and HTTP status {@code 200
   *     (OK)} or {@code 201 (CREATED)}
   */
  @PatchMapping(path = "/{ticketTypeId}")
  public ResponseEntity<UpdateTicketTypeResponse> updateTicketTypes(
      @PathVariable UUID ticketTypeId, @Valid @RequestBody UpdateTicketTypeRequest req) {

    UpdateTicketTypeCommand cmd = ticketTypeMapper.toUpdateCommand(req);

    UpdateTicketTypeResponse response = ticketTypeService.updateTicketType(ticketTypeId, cmd);

    return ResponseEntity.ok(response);
  }

  /**
   * Deletes a ticket type by its unique identifier.
   *
   * <p>Once deleted, the ticket type can no longer be used for sales or event configuration. This
   * operation is irreversible.
   *
   * @param ticketTypeId the unique identifier of the ticket type to delete
   * @return HTTP 200 if deletion was successful
   */
  @DeleteMapping(path = "/{ticketTypeId}")
  public ResponseEntity<?> deleteTicketType(@PathVariable UUID ticketTypeId) {
    ticketTypeService.deleteTicketType(ticketTypeId);
    return ResponseEntity.ok().build();
  }
}
