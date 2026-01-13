package com.daroch.ticket.dto.ticket.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequest {

  @NotNull(message = "Event Id is required")
  private UUID eventId;

  @NotNull(message = "TicketType Id is required")
  private UUID ticketTypeId;
}
