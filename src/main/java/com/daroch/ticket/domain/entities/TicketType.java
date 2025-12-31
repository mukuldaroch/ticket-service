package com.daroch.ticket.domain.entities;

import com.daroch.ticket.domain.enums.TicketTypeStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "ticket_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TicketType {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID ticketTypeId;

  @Column(name = "event_id", nullable = false)
  private UUID eventId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "price", nullable = false)
  private Double price;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "total_avalaible", nullable = false)
  private Integer totalAvailable;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private TicketTypeStatusEnum status;

  @CreatedDate
  @Column(name = "created", updatable = false, nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated", nullable = false)
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TicketType)) return false;
    return ticketTypeId!= null && ticketTypeId.equals(((TicketType) o).ticketTypeId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
