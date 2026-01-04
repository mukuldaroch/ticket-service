package com.daroch.ticket.dtos.tickettype.request;

import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeRequest{

  @NotNull(message = "eventId is required")
  private UUID eventId;

  @NotBlank(message = "Ticket type name is required")
  private String name;

  @NotNull(message = "Price is required")
  @PositiveOrZero(message = "Price must be zero or greater")
  private Double price;

  private String description;

  @NotNull(message = "Total available is required")
  @PositiveOrZero(message = "Total available must be zero or greater")
  private Integer totalAvailable;

  private TicketTypeStatusEnum status;
}
