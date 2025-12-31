package com.daroch.ticket.services.commands.tickettype;

import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeCommand {

  private UUID eventId;
  private String name;
  private Double price;
  private String description;
  private TicketTypeStatusEnum status;
  private Integer totalAvailable;
}
