package com.daroch.ticket.dtos.ticket.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequestDto {

  @NotNull(message = "userId is required")
  private UUID userId;

  @NotNull(message = "ticketTypeId is required")
  private UUID ticketTypeId;
}
