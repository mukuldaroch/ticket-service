package com.daroch.ticket.repositories;

import com.daroch.ticket.domain.entities.TicketType;
import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, UUID> {

  List<TicketType> findByEventId(UUID eventId);

  List<TicketType> findByEventIdAndStatus(UUID eventId, TicketTypeStatusEnum status);
}
