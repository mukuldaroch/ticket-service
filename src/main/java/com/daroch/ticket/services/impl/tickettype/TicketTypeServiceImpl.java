package com.daroch.ticket.services.impl.tickettype;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import com.daroch.ticket.dtos.tickettype.response.CreateTicketTypeResponseDto;
import com.daroch.ticket.dtos.tickettype.response.UpdateTicketTypeResponseDto;
import com.daroch.ticket.exceptions.BusinessException;
import com.daroch.ticket.exceptions.TicketTypeNotFoundException;
import com.daroch.ticket.mappers.TicketTypeMapper;
import com.daroch.ticket.repositories.TicketTypeRepository;
import com.daroch.ticket.services.TicketTypeService;
import com.daroch.ticket.services.commands.tickettype.CreateTicketTypeCommand;
import com.daroch.ticket.services.commands.tickettype.UpdateTicketTypeCommand;
import java.util.ArrayList;
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
  public CreateTicketTypeResponseDto createTicketType(
      UUID eventId, CreateTicketTypeCommand ticketType) {

    if (ticketType == null) {
      throw new IllegalArgumentException("Ticket types list cannot be empty");
    }

    TicketType ticketTypeToCreate = new TicketType();
    ticketTypeToCreate.setEventId(ticketType.getEventId());
    ticketTypeToCreate.setName(ticketType.getName());
    ticketTypeToCreate.setPrice(ticketType.getPrice());
    ticketTypeToCreate.setDescription(ticketType.getDescription());
    ticketTypeToCreate.setTotalAvailable(ticketType.getTotalAvailable());
    ticketTypeToCreate.setStatus(ticketType.getStatus());

    // save to database
    ticketTypeRepository.save(ticketTypeToCreate);

    return ticketTypeMapper.toCreateResponse(ticketTypeToCreate);
  }

  public List<CreateTicketTypeResponseDto> createTicketTypes(
      UUID eventId, List<CreateTicketTypeCommand> ticketTypes) {

    if (ticketTypes == null || ticketTypes.isEmpty()) {
      throw new IllegalArgumentException("Ticket types list cannot be empty");
    }

    List<TicketType> entities =
        ticketTypes.stream()
            .map(
                cmd -> {
                  TicketType ticketType = new TicketType();
                  ticketType.setEventId(eventId); // SINGLE source of truth
                  ticketType.setName(cmd.getName());
                  ticketType.setPrice(cmd.getPrice());
                  ticketType.setDescription(cmd.getDescription());
                  ticketType.setTotalAvailable(cmd.getTotalAvailable());
                  ticketType.setStatus(cmd.getStatus());
                  return ticketType;
                })
            .toList();

    List<TicketType> savedTicketTypes = ticketTypeRepository.saveAll(entities);

    return savedTicketTypes.stream().map(ticketTypeMapper::toCreateResponse).toList();
  }

  @Override
  public UpdateTicketTypeResponseDto updateTicketType(
      UUID eventId, UpdateTicketTypeCommand command) {

    TicketType ticketType =
        ticketTypeRepository
            .findById(command.getTicketTypeId())
            .orElseThrow(
                () ->
                    new TicketTypeNotFoundException(
                        "TicketType not found for ID" + command.getTicketTypeId()));

    if (!ticketType.getEventId().equals(eventId)) {
      throw new BusinessException("Ticket type does not belong to this event");
    }

    if (command.getName() != null) {
      ticketType.setName(command.getName());
    }

    if (command.getPrice() != null) {
      ticketType.setPrice(command.getPrice());
    }

    if (command.getDescription() != null) {
      ticketType.setDescription(command.getDescription());
    }

    if (command.getTotalAvailable() != null) {
      ticketType.setTotalAvailable(command.getTotalAvailable());
    }

    if (command.getStatus() != null) {
      ticketType.setStatus(command.getStatus());
    }

    TicketType saved = ticketTypeRepository.save(ticketType);

    return ticketTypeMapper.toUpdateResponse(saved);
  }

  @Override
  @Transactional
  public List<UpdateTicketTypeResponseDto> updateTicketTypes(
      UUID eventId, List<UpdateTicketTypeCommand> ticketTypesCommand) {

    if (ticketTypesCommand == null || ticketTypesCommand.isEmpty()) {
      throw new BusinessException("No ticket types provided for update");
    }

    List<UpdateTicketTypeResponseDto> response = new ArrayList<>();

    for (UpdateTicketTypeCommand command : ticketTypesCommand) {

      response.add(updateTicketType(eventId, command));
    }

    return response;
  }

  @Override
  @Transactional
  public void deleteTicketTypes(UUID eventId, List<UUID> ticketTypeIds) {

    if (ticketTypeIds == null || ticketTypeIds.isEmpty()) {
      throw new BusinessException("No ticket types provided for deletion");
    }

    List<TicketType> ticketTypes = ticketTypeRepository.findAllById(ticketTypeIds);

    if (ticketTypes.size() != ticketTypeIds.size()) {
      throw new TicketTypeNotFoundException("One or more ticket types not found");
    }

    for (TicketType ticketType : ticketTypes) {
      if (!ticketType.getEventId().equals(eventId)) {
        throw new BusinessException("Ticket type does not belong to this event");
      }
    }

    ticketTypeRepository.deleteAll(ticketTypes);
  }

  // -------------------------------------------------------------- queries
  @Override
  @Transactional(readOnly = true)
  public List<TicketType> getTicketTypesForEvent(UUID eventId) {
    return ticketTypeRepository.findByEventId(eventId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<TicketType> getPublishedTicketTypesForEvent(UUID eventId) {
    return ticketTypeRepository.findByEventIdAndStatus(eventId, TicketTypeStatusEnum.PUBLISHED);
  }
}
