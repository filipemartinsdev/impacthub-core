package br.social.impacthub.service.mapper;

import br.social.impacthub.model.dto.EventCommentResponse;
import br.social.impacthub.model.entity.EventComment;
import org.springframework.stereotype.Component;

@Component
public class EventCommentMapper {
    public EventCommentResponse toResponse(EventComment entity) {
        return new EventCommentResponse(
                entity.getId(),
                new EventCommentResponse.UserResponse(
                        entity.getUser().getUserId(),
                        entity.getUser().getUsername(),
                        entity.getUser().getName()
                ),
                entity.getContent(),
                entity.getCreatedAt()
        );
    }
}
