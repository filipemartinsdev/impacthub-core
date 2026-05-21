package br.social.impacthub.service;

import br.social.impacthub.exception.EventNotFoundException;
import br.social.impacthub.infrastructure.persistence.EventCommentRepository;
import br.social.impacthub.infrastructure.persistence.EventRepository;
import br.social.impacthub.infrastructure.persistence.UserProfileRepository;
import br.social.impacthub.model.dto.CreateEventCommentRequest;
import br.social.impacthub.model.dto.EventCommentResponse;
import br.social.impacthub.model.dto.PagedResponse;
import br.social.impacthub.model.entity.EventComment;
import br.social.impacthub.service.mapper.EventCommentMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

// TODO: unit tests
@Service
public class EventCommentService {
    private final EventCommentRepository eventCommentRepository;
    private final EventCommentMapper eventCommentMapper;
    private final EventRepository eventRepository;
    private final UserProfileRepository userRepository;

    public EventCommentService(EventCommentRepository eventCommentRepository, EventCommentMapper eventCommentMapper, EventRepository eventRepository, UserProfileRepository userRepository) {
        this.eventCommentRepository = eventCommentRepository;
        this.eventCommentMapper = eventCommentMapper;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public void create(UUID eventId, CreateEventCommentRequest request, UUID userId) {
        EventComment comment = new EventComment();
        comment.setEvent(eventRepository.getReferenceById(eventId));
        comment.setUser(userRepository.getReferenceById(userId));
        comment.setContent(request.content());

        eventCommentMapper.toResponse(
                eventCommentRepository.save(comment)
        );
    }

    public PagedResponse<EventCommentResponse> getAllByEvent(UUID id, Pageable pageable) {
        eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found by ID: "+id));

        Page<EventComment> page = eventCommentRepository.findAllByEventId(id, pageable);

        return PagedResponse.<EventCommentResponse>builder()
                .content(page.getContent().stream()
                        .map(eventCommentMapper::toResponse)
                        .toList()
                )
                .page(page.getNumber())
                .size(page.getSize())
                .isLast(page.isLast())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
