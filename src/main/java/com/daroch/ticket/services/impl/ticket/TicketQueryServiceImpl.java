package com.daroch.ticket.services.impl.ticket;

import com.daroch.ticket.dto.ticket.response.GetTicketResponse;
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

  @Override
  public Optional<GetTicketResponse> getTicketForUser(UUID userId, UUID ticketId) {
    return null;
  }

  @Override
  public Page<GetTicketResponse> listUpcomingTicketsForUser(UUID userId, Pageable pageable) {
    return null;
  }

  @Override
  public Page<GetTicketResponse> listExpiredTicketsForUser(UUID userId, Pageable pageable) {
    return null;
  }
}
