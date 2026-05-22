package br.social.impacthub.service.mapper;

import br.social.impacthub.model.dto.NotificationResponse;
import br.social.impacthub.model.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationResponse toResponse(Notification entity) {
        return new NotificationResponse(
                entity.getId(),
                entity.getDescription(),
                entity.getIsViewed(),
                entity.getCreatedAt()
        );
    }
}
