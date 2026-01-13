package com.daroch.ticket.services.impl.tickettype;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import com.daroch.ticket.dto.tickettype.response.CreateTicketTypeResponse;
import com.daroch.ticket.dto.tickettype.response.UpdateTicketTypeResponse;
import com.daroch.ticket.exceptions.BusinessException;
import com.daroch.ticket.exceptions.TicketTypeNotFoundException;
import com.daroch.ticket.mappers.TicketTypeMapper;
import com.daroch.ticket.repositories.TicketTypeRepository;
import com.daroch.ticket.services.TicketTypeService;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

  //  ------------------------------------------------------------- dependancy

  private final TicketTypeRepository ticketTypeRepository;
  private final TicketTypeMapper ticketTypeMapper;

  //  ------------------------------------------------------------- commands
  public CreateTicketTypeResponse createTicketType(CreateTicketTypeCommand cmd) {

    if (cmd == null) {
      throw new IllegalArgumentException("Ticket types list cannot be empty");
    }

    TicketType ticketType = new TicketType();
    ticketType.setEventId(cmd.getEventId());
    ticketType.setName(cmd.getName());
    ticketType.setPrice(cmd.getPrice());
    ticketType.setDescription(cmd.getDescription());
    ticketType.setTotalAvailable(cmd.getTotalAvailable());
    ticketType.setTicketTypeStatus(cmd.getTicketTypeStatus());

    // save to database
    ticketTypeRepository.save(ticketType);

    return ticketTypeMapper.toCreateResponse(ticketType);
  }

  @Override
  @Transactional
  public List<CreateTicketTypeResponse> createTicketTypes(List<CreateTicketTypeCommand> cmds) {

    if (cmds == null || cmds.isEmpty()) {
      throw new IllegalArgumentException("Ticket types list cannot be empty");
    }

    List<CreateTicketTypeResponse> entities = cmds.stream().map(this::createTicketType).toList();

    return entities;
  }

  @Override
  public UpdateTicketTypeResponse updateTicketType(UUID ticketTypeId, UpdateTicketTypeCommand cmd) {

    TicketType ticketType =
        ticketTypeRepository
            .findById(ticketTypeId)
            .orElseThrow(
                () ->
                    new TicketTypeNotFoundException("TicketType not found for ID" + ticketTypeId));

    if (!ticketType.getEventId().equals(cmd.getEventId())) {
      throw new BusinessException("Ticket type does not belong to this event");
    }

    if (cmd.getName() != null) {
      ticketType.setName(cmd.getName());
    }

    if (cmd.getPrice() != null) {
      ticketType.setPrice(cmd.getPrice());
    }

    if (cmd.getDescription() != null) {
      ticketType.setDescription(cmd.getDescription());
    }

    if (cmd.getTotalAvailable() != null) {
      ticketType.setTotalAvailable(cmd.getTotalAvailable());
    }

    TicketType saved = ticketTypeRepository.save(ticketType);

    return ticketTypeMapper.toUpdateResponse(saved);
  }

  @Override
  @Transactional
  public List<UpdateTicketTypeResponse> updateTicketTypes(List<UpdateTicketTypeCommand> cmd) {
    //
    // if (cmd == null || cmd.isEmpty()) {
    //   throw new BusinessException("No ticket types provided for update");
    // }
    //
    // List<UpdateTicketTypeResponse> response = new ArrayList<>();
    //
    // response = cmd.stream().map(this::updateTicketType).toList();
    //
    // return response;
    return null;
  }

  @Override
  public void deleteTicketType(UUID ticketTypeId) {

    if (ticketTypeId == null) {
      throw new BusinessException("No ticket types provided for deletion");
    }

    TicketType ticketType =
        ticketTypeRepository
            .findById(ticketTypeId)
            .orElseThrow(
                () ->
                    new TicketTypeNotFoundException(
                        "ticket types not found for Id : " + ticketTypeId));

    ticketTypeRepository.delete(ticketType);
  }

  // -------------------------------------------------------------- queries

  @Override
  @Transactional(readOnly = true)
  public TicketType getTicketType(UUID ticketTypeId) {
    return ticketTypeRepository
        .findById(ticketTypeId)
        .orElseThrow(
            () ->
                new TicketTypeNotFoundException("ticket types not found for Id : " + ticketTypeId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<TicketType> getTicketTypesForEvent(UUID eventId) {
    return ticketTypeRepository.findByEventId(eventId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<TicketType> getPublishedTicketTypesForEvent(UUID eventId) {
    return ticketTypeRepository.findByEventIdAndTicketTypeStatus(
        eventId, TicketTypeStatusEnum.PUBLISHED);
  }
}
