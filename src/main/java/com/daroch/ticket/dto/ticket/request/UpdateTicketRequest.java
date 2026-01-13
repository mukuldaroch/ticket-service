package com.daroch.ticket.dto.ticket.request;

import com.daroch.ticket.domain.enums.TicketStatusEnum;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketRequest {

  @NotNull(message = "ticketId is required")
  private UUID ticketId;

  private UUID eventId;
  private UUID ticketTypeId;

  private TicketStatusEnum ticketStatus;

  private LocalDateTime usedAt;
  private LocalDateTime cancelledAt;
}
