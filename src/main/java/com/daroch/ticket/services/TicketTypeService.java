package com.daroch.ticket.services;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.dto.tickettype.response.CreateTicketTypeResponse;
import com.daroch.ticket.dto.tickettype.response.UpdateTicketTypeResponse;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import java.util.List;
import java.util.UUID;

public interface TicketTypeService {
  // single create
  CreateTicketTypeResponse createTicketType(CreateTicketTypeCommand cmd);

  // bulk create
  List<CreateTicketTypeResponse> createTicketTypes(List<CreateTicketTypeCommand> cmds);

  // single update
  UpdateTicketTypeResponse updateTicketType(UUID ticketTypeId, UpdateTicketTypeCommand cmd);

  // bulk update
  List<UpdateTicketTypeResponse> updateTicketTypes(
      List<UpdateTicketTypeCommand> cmds);

  // bulk delete
  void deleteTicketType(UUID ticketTypeId);

  // queries
  TicketType getTicketType(UUID ticketTypeId);

  List<TicketType> getTicketTypesForEvent(UUID eventId);

  List<TicketType> getPublishedTicketTypesForEvent(UUID eventId);
}
