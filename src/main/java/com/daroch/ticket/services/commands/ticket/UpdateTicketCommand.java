package com.daroch.ticket.services.commands.ticket;

import com.daroch.ticket.domain.enums.TicketStatusEnum;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketCommand {

  private UUID ticketId;
  private UUID eventId;
  private UUID organizerId;
  private UUID ticketTypeId;
  private TicketStatusEnum ticketStatus;
  private LocalDateTime usedAt;
  private LocalDateTime cancelledAt;
}
