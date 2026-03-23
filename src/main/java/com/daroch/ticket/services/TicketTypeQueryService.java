package com.daroch.ticket.services;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.dto.tickettype.response.CreateTicketTypeResponse;
import com.daroch.ticket.dto.tickettype.response.UpdateTicketTypeResponse;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import java.util.List;
import java.util.UUID;

public interface TicketTypeQueryService {
  // queries
  TicketType getTicketType(UUID ticketTypeId);

  List<TicketType> getTicketTypesForEvent(UUID eventId);

  List<TicketType> getPublishedTicketTypesForEvent(UUID eventId);
}
