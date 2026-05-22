package br.social.impacthub.service;

import br.social.impacthub.exception.NotificationNotFoundException;
import br.social.impacthub.infrastructure.persistence.NotificationRepository;
import br.social.impacthub.model.dto.NotificationResponse;
import br.social.impacthub.model.dto.PagedResponse;
import br.social.impacthub.model.entity.Notification;
import br.social.impacthub.service.mapper.NotificationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

// TODO: unit tests
@Service
public class NotificationService {
    private final NotificationMapper notificationMapper;
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationMapper notificationMapper, NotificationRepository notificationRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
    }

    public PagedResponse<NotificationResponse> getAll(UUID userId, Pageable pageable) {
        Page<Notification> page = notificationRepository.findAllByUserId(userId, pageable);

        return PagedResponse.<NotificationResponse>builder()
                .page(page.getNumber())
                .size(page.getSize())
                .isLast(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .content(page.getContent().stream()
                        .map(notificationMapper::toResponse)
                        .toList()
                )
                .build();
    }

    public PagedResponse<NotificationResponse> getAllUnviewed(UUID userId, Pageable pageable) {
        Page<Notification> page = notificationRepository.findAllUnviewedByUserId(userId, pageable);

        return PagedResponse.<NotificationResponse>builder()
                .page(page.getNumber())
                .size(page.getSize())
                .isLast(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .content(page.getContent().stream()
                        .map(notificationMapper::toResponse)
                        .toList()
                )
                .build();
    }

    public PagedResponse<NotificationResponse> getAllViewed(UUID userId, Pageable pageable) {
        Page<Notification> page = notificationRepository.findAllViewedByUserId(userId, pageable);

        return PagedResponse.<NotificationResponse>builder()
                .page(page.getNumber())
                .size(page.getSize())
                .isLast(page.isLast())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .content(page.getContent().stream()
                        .map(notificationMapper::toResponse)
                        .toList()
                )
                .build();
    }

    public void view(UUID notificationId){
        Notification notification =  notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found by ID: "+notificationId));

        notification.setIsViewed(true);
        notificationRepository.save(notification);
    }
}
