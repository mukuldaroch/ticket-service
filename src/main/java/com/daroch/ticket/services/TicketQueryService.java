package com.daroch.ticket.services;

import com.daroch.ticket.dtos.ticket.response.GetTicketResponse;
import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

public interface TicketQueryService {

  Optional<GetTicketResponse> getTicketForUser(UUID userId, UUID ticketId);

  Page<GetTicketResponse> listUpcomingTicketsForUser(UUID userId, Pageable pageable);

  Page<GetTicketResponse> listExpiredTicketsForUser(UUID userId, Pageable pageable);
}
