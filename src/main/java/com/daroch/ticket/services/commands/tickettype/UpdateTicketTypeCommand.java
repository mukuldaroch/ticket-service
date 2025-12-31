package com.daroch.ticket.services.commands.tickettype;

import java.util.UUID;

import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketTypeCommand {

  private UUID ticketTypeId;
  private UUID eventId;
  private String name;
  private Double price;
  private String description;
  private TicketTypeStatusEnum status;
  private Integer totalAvailable;
}
