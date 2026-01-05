package com.daroch.ticket.mappers;

import com.daroch.ticket.domain.entities.Ticket;
import com.daroch.ticket.dto.ticket.request.CreateTicketRequest;
import com.daroch.ticket.dto.ticket.request.UpdateTicketRequest;
import com.daroch.ticket.dto.ticket.response.CreateTicketResponse;
import com.daroch.ticket.dto.ticket.response.UpdateTicketResponse;
import com.daroch.ticket.services.commands.ticket.CreateTicketCommand;
import com.daroch.ticket.services.commands.ticket.UpdateTicketCommand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

  // -------- Incoming (API → Application) --------
  CreateTicketCommand toCommand(CreateTicketRequest dto);

  UpdateTicketCommand toCommand(UpdateTicketRequest dto);

  // -------- Outgoing (Domain → API) --------
  CreateTicketResponse toCreateResponse(Ticket ticket);

  UpdateTicketResponse toUpdateResponse(Ticket ticket);
}
