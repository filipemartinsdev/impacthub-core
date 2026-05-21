package br.social.impacthub.model.dto;

import java.time.Instant;
import java.util.UUID;

public record EventCommentResponse(
        UUID id,
        UserResponse user,
        String content,
        Instant createdAt
) {
    public static record UserResponse(UUID id, String username, String name){}
}
