package com.daroch.ticket.services.impl.ticket;

import com.daroch.ticket.dto.ticket.response.TicketResponse;
import com.daroch.ticket.mappers.TicketMapper;
import com.daroch.ticket.repositories.TicketRepository;
import com.daroch.ticket.services.TicketQueryService;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketQueryServiceImpl implements TicketQueryService {

  private final TicketRepository ticketRepository;
  private final TicketMapper ticketMapper;

  @Override
  public Optional<TicketResponse> getTicketForUser(UUID userId, UUID ticketId) {
    return null;
  }

  @Override
  public Page<TicketResponse> listUpcomingTicketsForUser(UUID userId, Pageable pageable) {
    return null;
  }

  @Override
  public Page<TicketResponse> listExpiredTicketsForUser(UUID userId, Pageable pageable) {
    return null;
  }
}
