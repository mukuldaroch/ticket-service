package com.daroch.ticket.services;

import com.daroch.ticket.dto.ticket.response.TicketResponse;
import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

public interface TicketQueryService {

  Optional<TicketResponse> getTicketForUser(UUID userId, UUID ticketId);

  Page<TicketResponse> listUpcomingTicketsForUser(UUID userId, Pageable pageable);

  Page<TicketResponse> listExpiredTicketsForUser(UUID userId, Pageable pageable);
}
