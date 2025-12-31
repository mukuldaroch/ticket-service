package com.daroch.ticket.dtos.ticket.request;

import com.daroch.ticket.domain.enums.TicketStatusEnum;
import jakarta.validation.constraints.NotNull;
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
public class UpdateTicketRequestDto {

  @NotNull(message = "ticketId is required")
  private UUID ticketId;

  @NotNull(message = "userId is required")
  private UUID userId;

  private UUID ticketTypeId;
  private TicketStatusEnum ticketStatus;
  private LocalDateTime usedAt;
  private LocalDateTime cancelledAt;
}
