package com.daroch.ticket.services.impl.ticket;

import com.daroch.ticket.domain.entities.Ticket;
import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.domain.enums.TicketStatusEnum;
import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import com.daroch.ticket.dto.ticket.response.CreateTicketResponse;
import com.daroch.ticket.dto.ticket.response.UpdateTicketResponse;
import com.daroch.ticket.exceptions.TicketNotFoundException;
import com.daroch.ticket.exceptions.TicketTypeNotFoundException;
import com.daroch.ticket.exceptions.ValidationException;
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
  public CreateTicketResponse createTicket(UUID userId, CreateTicketCommand cmd) {

    TicketType ticketType =
        ticketTypeRepository
            .findById(cmd.getTicketTypeId())
            .orElseThrow(() -> new TicketTypeNotFoundException());

    if (ticketType.getTicketTypeStatus() != TicketTypeStatusEnum.PUBLISHED) {
      throw new ValidationException("TicketType Status is not Published");
    }

    if (ticketType.getTotalAvailable() <= 0) {
      throw new ValidationException("Ticket is not available");
    }

    Ticket ticket = new Ticket();

    ticket.setUserId(userId);
    ticket.setTicketTypeId(ticketType.getTicketTypeId());
    ticket.setTicketStatus(TicketStatusEnum.CREATED);
    ticket.setPriceAtPurchase(ticketType.getPrice());

    ticketRepository.save(ticket);

    return ticketMapper.toCreateResponse(ticket);
  }

  @Override
  public UpdateTicketResponse updateTicketForUser(
      UUID userId, UUID eventId, UpdateTicketCommand cmd) {
    Ticket ticket =
        ticketRepository
            .findById(cmd.getTicketId())
            .orElseThrow(() -> new TicketNotFoundException());

    if (!ticket.getEventId().equals(eventId)) {
      throw new ValidationException("Ticket does not belong to this eventId");
    }
    if (!ticket.getUserId().equals(userId)) {
      throw new ValidationException("this user does now own this Ticket");
    }

    if (cmd.getTicketStatus() != null) {
      ticket.setTicketStatus(cmd.getTicketStatus());
    }

    if (cmd.getUsedAt() != null) {
      ticket.setUsedAt(cmd.getUsedAt());
    }

    if (cmd.getCancelledAt() != null) {
      ticket.setCancelledAt(cmd.getCancelledAt());
    }

    Ticket update = ticketRepository.save(ticket);

    return ticketMapper.toUpdateResponse(update);
  }

  @Override
  public void deleteTicketForOrganizer(UUID userId, UUID eventId, UUID ticketId) {

    Ticket ticket =
        ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException());

    if (!ticket.getEventId().equals(eventId)) {
      throw new ValidationException("eventId and ticket eventId does not mateches");
    }
    if (!ticket.getUserId().equals(userId)) {
      throw new ValidationException("UserId does not matches userId");
    }

    ticketRepository.delete(ticket);
  }
}
