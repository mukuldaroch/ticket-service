package com.daroch.ticket.services.impl.ticket;

import com.daroch.ticket.domain.entities.Ticket;
import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.domain.enums.TicketStatusEnum;
import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import com.daroch.ticket.dtos.ticket.response.CreateTicketResponse;
import com.daroch.ticket.dtos.ticket.response.UpdateTicketResponse;
import com.daroch.ticket.exceptions.BusinessException;
import com.daroch.ticket.exceptions.TicketNotFoundException;
import com.daroch.ticket.exceptions.TicketTypeNotFoundException;
import com.daroch.ticket.mappers.TicketMapper;
import com.daroch.ticket.repositories.TicketRepository;
import com.daroch.ticket.repositories.TicketTypeRepository;
import com.daroch.ticket.services.TicketCommandService;
import com.daroch.ticket.services.commands.ticket.CreateTicketCommand;
import com.daroch.ticket.services.commands.ticket.UpdateTicketCommand;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketCommandServiceImpl implements TicketCommandService {

  private final TicketTypeRepository ticketTypeRepository;
  private final TicketRepository ticketRepository;
  private final TicketMapper ticketMapper;

  @Override
  public CreateTicketResponse createTicket(UUID userId, CreateTicketCommand ticketCommand) {

    TicketType ticketType =
        ticketTypeRepository
            .findById(ticketCommand.getTicketTypeId())
            .orElseThrow(() -> new TicketTypeNotFoundException("Ticket type not found"));

    if (ticketType.getStatus() != TicketTypeStatusEnum.PUBLISHED) {
      throw new BusinessException("Ticket type not active");
    }

    if (ticketType.getTotalAvailable() <= 0) {
      throw new BusinessException("Tickets sold out");
    }

    // ticketType.decrementAvailability();

    Ticket ticketToCreate = new Ticket();

    ticketToCreate.setOrganizerId(userId);
    ticketToCreate.setTicketTypeId(ticketType.getTicketTypeId());
    ticketToCreate.setTicketStatus(TicketStatusEnum.CREATED);
    ticketToCreate.setPriceAtPurchase(ticketType.getPrice());

    ticketRepository.save(ticketToCreate);

    return ticketMapper.toCreateResponse(ticketToCreate);
  }

  @Override
  public UpdateTicketResponse updateTicketForOrganizer(
      UUID organizerId, UUID eventId, UpdateTicketCommand command) {
    Ticket ticket =
        ticketRepository
            .findById(command.getTicketId())
            .orElseThrow(
                () ->
                    new TicketNotFoundException(
                        "TicketType not found for ID" + command.getTicketId()));

    if (!ticket.getEventId().equals(eventId)) {
      throw new BusinessException("Ticket type does not belong to this event");
    }
    if (!ticket.getOrganizerId().equals(organizerId)) {
      throw new BusinessException("organizer does not belong to this event");
    }

    // private LocalDateTime usedAt;
    // private LocalDateTime cancelledAt;
    if (command.getTicketStatus() != null) {
      ticket.setTicketStatus(command.getTicketStatus());
    }

    if (command.getUsedAt() != null) {
      ticket.setUsedAt(command.getUsedAt());
    }

    if (command.getCancelledAt() != null) {
      ticket.setCancelledAt(command.getCancelledAt());
    }

    Ticket update = ticketRepository.save(ticket);

    return ticketMapper.toUpdateResponse(update);
  }

  @Override
  public void deleteTicketForOrganizer(UUID organizerId, UUID eventId, UUID ticketId) {

    Ticket ticket =
        ticketRepository
            .findById(ticketId)
            .orElseThrow(
                () -> new TicketNotFoundException("TicketType not found for ID" + ticketId));

    if (!ticket.getEventId().equals(eventId)) {
      throw new BusinessException("Ticket type does not belong to this event");
    }
    if (!ticket.getOrganizerId().equals(organizerId)) {
      throw new BusinessException("organizer does not belong to this event");
    }

    ticketRepository.delete(ticket);
  }
}
