package com.daroch.ticket.dto.tickettype.request;

import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketTypeRequest {

  @NotNull(message = "ticketTypeId is required")
  private UUID ticketTypeId;

  @NotNull(message = "eventId is required")
  private UUID eventId;

  private String name;
  private Double price;
  private String description;
  private Integer totalAvailable;
  private TicketTypeStatusEnum ticketTypeStatus;
}
