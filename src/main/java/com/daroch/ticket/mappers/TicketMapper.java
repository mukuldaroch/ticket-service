package com.daroch.ticket.mappers;

import com.daroch.ticket.domain.entities.Ticket;
import com.daroch.ticket.dtos.ticket.request.CreateTicketRequestDto;
import com.daroch.ticket.dtos.ticket.request.UpdateTicketRequestDto;
import com.daroch.ticket.dtos.ticket.response.CreateTicketResponseDto;
import com.daroch.ticket.dtos.ticket.response.UpdateTicketResponseDto;
import com.daroch.ticket.services.commands.ticket.CreateTicketCommand;
import com.daroch.ticket.services.commands.ticket.UpdateTicketCommand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

  // -------- Incoming (API → Application) --------
  CreateTicketCommand toCommand(CreateTicketRequestDto dto);

  UpdateTicketCommand toCommand(UpdateTicketRequestDto dto);

  // -------- Outgoing (Domain → API) --------
  CreateTicketResponseDto toCreateResponse(Ticket ticket);

  UpdateTicketResponseDto toUpdateResponse(Ticket ticket);
}
