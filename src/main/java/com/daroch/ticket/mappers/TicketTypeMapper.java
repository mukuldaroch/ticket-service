package com.daroch.ticket.mappers;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.dtos.tickettype.request.CreateTicketTypeRequestDto;
import com.daroch.ticket.dtos.tickettype.request.UpdateTicketTypeRequestDto;
import com.daroch.ticket.dtos.tickettype.response.CreateTicketTypeResponseDto;
import com.daroch.ticket.dtos.tickettype.response.GetEventTicketTypesResponseDto;
import com.daroch.ticket.dtos.tickettype.response.UpdateTicketTypeResponseDto;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketTypeMapper {

  // -------- Incoming (API → Application) --------
  CreateTicketTypeCommand toCommand(CreateTicketTypeRequestDto dto);

  UpdateTicketTypeCommand toCommand(UpdateTicketTypeRequestDto dto);

  // -------- Outgoing (Domain → API) --------

  CreateTicketTypeResponseDto toCreateResponse(TicketType ticketType);

  GetEventTicketTypesResponseDto toGetResponse(TicketType ticketType);

  UpdateTicketTypeResponseDto toUpdateResponse(TicketType ticketType);
}
