package br.social.impacthub.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "event_comment")
public class EventComment {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne @JoinColumn(name = "user_id")
    private UserProfile user;

    @NotBlank
    private String content;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
}
