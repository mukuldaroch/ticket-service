package com.daroch.ticket.services;

import com.daroch.ticket.dto.ticket.response.CreateTicketResponse;
import com.daroch.ticket.dto.ticket.response.UpdateTicketResponse;
import com.daroch.ticket.services.commands.ticket.CreateTicketCommand;
import com.daroch.ticket.services.commands.ticket.UpdateTicketCommand;
import java.util.UUID;

public interface TicketCommandService {

  CreateTicketResponse createTicket(UUID userId, CreateTicketCommand ticket);

  UpdateTicketResponse updateTicketForUser(
      UUID userId, UUID eventId, UpdateTicketCommand ticket);

  void deleteTicketForOrganizer(UUID userId, UUID eventId, UUID ticketId);
}
