package br.social.impacthub.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "notification")
public class Notification {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;

    @NotEmpty
    private String description;

    @Column(name = "is_viewed")
    private Boolean isViewed = false;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
}
