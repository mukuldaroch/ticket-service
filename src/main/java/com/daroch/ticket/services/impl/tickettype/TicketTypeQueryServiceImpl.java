package com.daroch.ticket.services.impl.tickettype;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import com.daroch.ticket.exceptions.TicketTypeNotFoundException;
import com.daroch.ticket.mappers.TicketTypeMapper;
import com.daroch.ticket.repositories.TicketTypeRepository;
import com.daroch.ticket.services.TicketTypeQueryService;
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
public class TicketTypeQueryServiceImpl implements TicketTypeQueryService {

  //  ----------------------------------------------------------- dependencies

  /** Repository used for performing database operations on {@link TicketType} entities. */
  private final TicketTypeRepository ticketTypeRepository;

  /** Mapper responsible for converting TicketType entities to response DTOs. */
  private final TicketTypeMapper ticketTypeMapper;

  //  ----------------------------------------------------------- queries

  /**
   * Retrieves a ticket type by its identifier.
   *
   * @param ticketTypeId identifier of the ticket type
   * @return the corresponding {@link TicketType} entity
   * @throws TicketTypeNotFoundException if the ticket type does not exist
   */
  @Override
  @Transactional(readOnly = true)
  public TicketType getTicketType(UUID ticketTypeId) {
    return ticketTypeRepository
        .findById(ticketTypeId)
        .orElseThrow(
            () ->
                new TicketTypeNotFoundException("ticket types not found for Id : " + ticketTypeId));
  }

  /**
   * Retrieves all ticket types associated with a specific event.
   *
   * @param eventId identifier of the event
   * @return list of ticket types belonging to the event
   */
  @Override
  @Transactional(readOnly = true)
  public List<TicketType> getTicketTypesForEvent(UUID eventId) {
    return ticketTypeRepository.findByEventId(eventId);
  }

  /**
   * Retrieves only published ticket types for a specific event.
   *
   * <p>This method filters ticket types by {@link TicketTypeStatusEnum#PUBLISHED}.
   *
   * @param eventId identifier of the event
   * @return list of published ticket types
   */
  @Override
  @Transactional(readOnly = true)
  public List<TicketType> getPublishedTicketTypesForEvent(UUID eventId) {
    return ticketTypeRepository.findByEventIdAndTicketTypeStatus(
        eventId, TicketTypeStatusEnum.PUBLISHED);
  }
}
