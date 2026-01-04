package com.daroch.ticket.services;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.dtos.tickettype.response.CreateTicketTypeResponse;
import com.daroch.ticket.dtos.tickettype.response.UpdateTicketTypeResponse;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import java.util.List;
import java.util.UUID;

public interface TicketTypeService {
  // single create
  CreateTicketTypeResponse createTicketType(UUID eventId, CreateTicketTypeCommand ticketType);

  // bulk create
  List<CreateTicketTypeResponse> createTicketTypes(
      UUID eventId, List<CreateTicketTypeCommand> ticketTypes);

  // single update
  UpdateTicketTypeResponse updateTicketType(UUID eventId, UpdateTicketTypeCommand command);

  // bulk update
  List<UpdateTicketTypeResponse> updateTicketTypes(
      UUID eventId, List<UpdateTicketTypeCommand> ticketTypeCommands);

  // bulk delete
  void deleteTicketTypes(UUID eventId, List<UUID> ticketTypeIds);

  // queries
  List<TicketType> getTicketTypesForEvent(UUID eventId);

  List<TicketType> getPublishedTicketTypesForEvent(UUID eventId);
}
