package com.daroch.ticket.services.impl.tickettype;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.dto.tickettype.response.CreateTicketTypeResponse;
import com.daroch.ticket.dto.tickettype.response.UpdateTicketTypeResponse;
import com.daroch.ticket.exceptions.BusinessException;
import com.daroch.ticket.exceptions.TicketTypeNotFoundException;
import com.daroch.ticket.mappers.TicketTypeMapper;
import com.daroch.ticket.repositories.TicketTypeRepository;
import com.daroch.ticket.services.TicketTypeCommandService;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation responsible for managing {@link TicketType} entities.
 *
 * <p>This service handles the lifecycle of ticket types associated with events, including creation,
 * updates, deletion, and retrieval operations.
 *
 * <p>The service enforces business rules such as:
 *
 * <ul>
 *   <li>Ticket types must belong to a specific event.
 *   <li>Updates are allowed only if the ticket type belongs to the provided event.
 *   <li>Batch operations are supported for creating and updating ticket types.
 * </ul>
 *
 * <p>All persistence operations are delegated to {@link TicketTypeRepository}, and mapping between
 * entities and response DTOs is handled by {@link TicketTypeMapper}.
 */
@Service
@RequiredArgsConstructor
public class TicketTypeCommandServiceImpl implements TicketTypeCommandService {

  //  ----------------------------------------------------------- dependencies

  /** Repository used for performing database operations on {@link TicketType} entities. */
  private final TicketTypeRepository ticketTypeRepository;

  /** Mapper responsible for converting TicketType entities to response DTOs. */
  private final TicketTypeMapper ticketTypeMapper;

  //  ----------------------------------------------------------- commands

  /**
   * Creates a new {@link TicketType} for a specific event.
   *
   * <p>This method constructs a new TicketType entity using the provided command object, persists
   * it in the database, and returns a response DTO representing the created ticket type.
   *
   * @param cmd command object containing ticket type creation details
   * @return response DTO representing the created ticket type
   * @throws IllegalArgumentException if the provided command is null
   */
  public CreateTicketTypeResponse createTicketType(CreateTicketTypeCommand cmd) {

    if (cmd == null) {
      throw new IllegalArgumentException("Ticket types list cannot be empty");
    }

    TicketType ticketType = new TicketType();
    ticketType.setEventId(cmd.getEventId());
    ticketType.setName(cmd.getName());
    ticketType.setPrice(cmd.getPrice());
    ticketType.setDescription(cmd.getDescription());
    ticketType.setTotalAvailable(cmd.getTotalAvailable());
    ticketType.setTicketTypeStatus(cmd.getTicketTypeStatus());

    // Persist the ticket type
    ticketTypeRepository.save(ticketType);

    return ticketTypeMapper.toCreateResponse(ticketType);
  }

  /**
   * Creates multiple ticket types in a single transaction.
   *
   * <p>This method iterates through the provided list of commands and delegates creation to {@link
   * #createTicketType(CreateTicketTypeCommand)} for each item.
   *
   * @param cmds list of ticket type creation commands
   * @return list of response DTOs representing the created ticket types
   * @throws IllegalArgumentException if the provided list is null or empty
   */
  @Override
  @Transactional
  public List<CreateTicketTypeResponse> createTicketTypes(List<CreateTicketTypeCommand> cmds) {

    if (cmds == null || cmds.isEmpty()) {
      throw new IllegalArgumentException("Ticket types list cannot be empty");
    }

    return cmds.stream().map(this::createTicketType).toList();
  }

  /**
   * Updates an existing ticket type.
   *
   * <p>This method retrieves the ticket type by its ID, validates that it belongs to the specified
   * event, and applies partial updates based on the provided command fields.
   *
   * <p>Only non-null fields in the command object will be updated.
   *
   * @param ticketTypeId unique identifier of the ticket type
   * @param cmd command object containing updated ticket type fields
   * @return response DTO representing the updated ticket type
   * @throws TicketTypeNotFoundException if no ticket type exists for the given ID
   * @throws BusinessException if the ticket type does not belong to the specified event
   */
  @Override
  public UpdateTicketTypeResponse updateTicketType(UUID ticketTypeId, UpdateTicketTypeCommand cmd) {

    TicketType ticketType =
        ticketTypeRepository
            .findById(ticketTypeId)
            .orElseThrow(
                () ->
                    new TicketTypeNotFoundException("TicketType not found for ID" + ticketTypeId));

    // Validate ticket type belongs to the provided event
    if (!ticketType.getEventId().equals(cmd.getEventId())) {
      throw new BusinessException("Ticket type does not belong to this event");
    }

    // Apply partial updates
    if (cmd.getName() != null) {
      ticketType.setName(cmd.getName());
    }

    if (cmd.getPrice() != null) {
      ticketType.setPrice(cmd.getPrice());
    }

    if (cmd.getDescription() != null) {
      ticketType.setDescription(cmd.getDescription());
    }

    if (cmd.getTotalAvailable() != null) {
      ticketType.setTotalAvailable(cmd.getTotalAvailable());
    }

    TicketType saved = ticketTypeRepository.save(ticketType);

    return ticketTypeMapper.toUpdateResponse(saved);
  }

  /**
   * Updates multiple ticket types in a single transaction.
   *
   * <p>This method is intended to process a batch of update commands and return the corresponding
   * responses. Currently not implemented.
   *
   * @param cmd list of ticket type update commands
   * @return list of updated ticket type response DTOs
   */
  @Override
  @Transactional
  public List<UpdateTicketTypeResponse> updateTicketTypes(List<UpdateTicketTypeCommand> cmd) {
    return null;
  }

  /**
   * Deletes a ticket type by its identifier.
   *
   * <p>This method first verifies that the ticket type exists before performing the deletion.
   *
   * @param ticketTypeId identifier of the ticket type to delete
   * @throws BusinessException if the provided ID is null
   * @throws TicketTypeNotFoundException if no ticket type exists with the given ID
   */
  @Override
  public void deleteTicketType(UUID ticketTypeId) {

    if (ticketTypeId == null) {
      throw new BusinessException("No ticket types provided for deletion");
    }

    TicketType ticketType =
        ticketTypeRepository
            .findById(ticketTypeId)
            .orElseThrow(
                () ->
                    new TicketTypeNotFoundException(
                        "ticket types not found for Id : " + ticketTypeId));

    ticketTypeRepository.delete(ticketType);
  }
}
