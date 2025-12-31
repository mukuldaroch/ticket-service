package com.daroch.ticket.services;

import com.daroch.ticket.dtos.ticket.response.CreateTicketResponseDto;
import com.daroch.ticket.dtos.ticket.response.UpdateTicketResponseDto;
import com.daroch.ticket.services.commands.ticket.CreateTicketCommand;
import com.daroch.ticket.services.commands.ticket.UpdateTicketCommand;
import java.util.UUID;

public interface TicketCommandService {

  CreateTicketResponseDto createTicket(UUID organizerId, CreateTicketCommand ticket);

  UpdateTicketResponseDto updateTicketForOrganizer(
      UUID organizerId, UUID eventId, UpdateTicketCommand ticket);

  void deleteTicketForOrganizer(UUID organizerId, UUID eventId, UUID ticketId);
}
