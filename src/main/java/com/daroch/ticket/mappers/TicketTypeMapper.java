package com.daroch.ticket.mappers;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.dto.tickettype.request.CreateTicketTypeRequest;
import com.daroch.ticket.dto.tickettype.request.UpdateTicketTypeRequest;
import com.daroch.ticket.dto.tickettype.response.CreateTicketTypeResponse;
import com.daroch.ticket.dto.tickettype.response.GetEventTicketTypesResponse;
import com.daroch.ticket.dto.tickettype.response.UpdateTicketTypeResponse;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketTypeMapper {

  // -------- Incoming (API → Application) --------
  CreateTicketTypeCommand toCommand(CreateTicketTypeRequest dto);

  UpdateTicketTypeCommand toCommand(UpdateTicketTypeRequest dto);

  // -------- Outgoing (Domain → API) --------

  CreateTicketTypeResponse toCreateResponse(TicketType ticketType);

  GetEventTicketTypesResponse toGetResponse(TicketType ticketType);

  UpdateTicketTypeResponse toUpdateResponse(TicketType ticketType);
}
