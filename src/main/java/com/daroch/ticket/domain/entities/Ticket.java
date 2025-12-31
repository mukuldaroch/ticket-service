package com.daroch.ticket.domain.entities;

import com.daroch.ticket.domain.enums.TicketStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Ticket {
  @Id
  @Column(name = "ticket_id", nullable = false, updatable = false)
  private UUID ticketId;

  @Column(name = "event_id", nullable = false, updatable = false)
  private UUID eventId;

  @Column(name = "user_id", nullable = false, updatable = false)
  private UUID OrganizerId;

  @Column(name = "ticket_type_id", nullable = false)
  private UUID ticketTypeId;

  @Column(name = "ticket_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private TicketStatusEnum ticketStatus;

  @Column(name = "price_at_purchase", nullable = false, updatable = false)
  private Double priceAtPurchase;

  @Column(name = "used_at", updatable = false)
  private LocalDateTime usedAt;

  @Column(name = "cancelled_at", updatable = false)
  private LocalDateTime cancelledAt;

  @CreatedDate
  @Column(name = "created", updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated", nullable = false)
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Ticket)) return false;
    return ticketId != null && ticketId.equals(((Ticket) o).ticketId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
