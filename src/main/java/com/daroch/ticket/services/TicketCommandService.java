package com.daroch.ticket.services;

import com.daroch.ticket.dto.ticket.response.CreateTicketResponse;
import com.daroch.ticket.dto.ticket.response.UpdateTicketResponse;
import com.daroch.ticket.services.commands.ticket.CreateTicketCommand;
import com.daroch.ticket.services.commands.ticket.UpdateTicketCommand;
import java.util.UUID;

public interface TicketCommandService {

  CreateTicketResponse createTicket(UUID organizerId, CreateTicketCommand ticket);

  UpdateTicketResponse updateTicketForOrganizer(
      UUID organizerId, UUID eventId, UpdateTicketCommand ticket);

  void deleteTicketForOrganizer(UUID organizerId, UUID eventId, UUID ticketId);
}
