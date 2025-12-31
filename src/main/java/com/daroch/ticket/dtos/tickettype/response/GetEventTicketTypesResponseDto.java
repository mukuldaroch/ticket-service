package com.daroch.ticket.dtos.tickettype.response;

import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventTicketTypesResponseDto {

  private UUID ticketTypeId;
  private UUID eventId;
  private String name;
  private Double price;
  private String description;
  private Integer totalAvailable;
  private TicketTypeStatusEnum status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
