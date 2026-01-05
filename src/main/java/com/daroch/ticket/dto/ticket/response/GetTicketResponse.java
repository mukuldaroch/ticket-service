package com.daroch.ticket.dto.ticket.response;

import com.daroch.ticket.domain.enums.TicketStatusEnum;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTicketResponse{

  private UUID ticketId;
  private UUID userId;
  private UUID ticketTypeId;
  private TicketStatusEnum ticketStatus;
  private Double priceAtPurchase;
}
