package com.daroch.ticket.services.commands.ticket;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketCommand {
  private UUID userId;
  private UUID ticketTypeId;
}
