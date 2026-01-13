package com.daroch.ticket.dto.ticket.response;

import com.daroch.ticket.domain.enums.TicketStatusEnum;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {

  private UUID ticketId;
  private UUID userId;
  private UUID eventId;
  private UUID ticketTypeId;
  private TicketStatusEnum ticketStatus;
  private Double priceAtPurchase;

  private LocalDateTime UsedAt;
  private LocalDateTime CancelledAt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
