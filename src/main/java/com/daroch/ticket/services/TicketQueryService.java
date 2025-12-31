package com.daroch.ticket.services;

import com.daroch.ticket.dtos.ticket.response.GetTicketResponseDto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

public interface TicketQueryService {

  Optional<GetTicketResponseDto> getTicketForUser(UUID userId, UUID ticketId);

  Page<GetTicketResponseDto> listUpcomingTicketsForUser(UUID userId, Pageable pageable);

  Page<GetTicketResponseDto> listExpiredTicketsForUser(UUID userId, Pageable pageable);
}
