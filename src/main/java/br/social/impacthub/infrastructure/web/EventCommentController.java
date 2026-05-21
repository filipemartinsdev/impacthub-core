package br.social.impacthub.infrastructure.web;

import br.social.impacthub.model.dto.CreateEventCommentRequest;
import br.social.impacthub.model.dto.EventCommentResponse;
import br.social.impacthub.model.dto.PagedResponse;
import br.social.impacthub.model.dto.StandardResponse;
import br.social.impacthub.service.EventCommentService;
import br.social.impacthub.service.security.AuthService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
public class EventCommentController {
    private final EventCommentService eventCommentService;
    private final AuthService authService;

    public EventCommentController(EventCommentService eventCommentService, AuthService authService) {
        this.eventCommentService = eventCommentService;
        this.authService = authService;
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<StandardResponse<Void>> createEventComment(
            @PathVariable UUID id,
            @Valid @RequestBody CreateEventCommentRequest request
    ){
        UUID authenticatedUserId = authService.getAuthenticatedUser().userId();

        eventCommentService.create(id, request, authenticatedUserId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(StandardResponse.success());
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<StandardResponse<PagedResponse<EventCommentResponse>>> getEventComments(
            @PathVariable UUID id,
            Pageable pageable
    ){
        return ResponseEntity
                .ok(StandardResponse.success(eventCommentService.getAllByEvent(id, pageable)));
    }
}
