package br.social.impacthub.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateEventCommentRequest (
        @NotBlank String content
){
}
