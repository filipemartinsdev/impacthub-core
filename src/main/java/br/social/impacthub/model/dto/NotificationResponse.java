package br.social.impacthub.model.dto;

import java.time.Instant;
import java.util.UUID;

public record NotificationResponse(
        UUID id,
        String description,
        Boolean isViewed,
        Instant createdAt
) {
}
