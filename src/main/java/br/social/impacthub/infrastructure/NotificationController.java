package br.social.impacthub.infrastructure;

import br.social.impacthub.infrastructure.persistence.NotificationRepository;
import br.social.impacthub.infrastructure.web.docs.NotificationControllerDocs;
import br.social.impacthub.model.dto.NotificationResponse;
import br.social.impacthub.model.dto.PagedResponse;
import br.social.impacthub.model.dto.StandardResponse;
import br.social.impacthub.service.NotificationService;
import br.social.impacthub.service.security.AuthService;
import feign.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController implements NotificationControllerDocs {
    private final AuthService authService;
    private final NotificationService notificationService;

    public NotificationController(AuthService authService, NotificationService notificationService) {
        this.authService = authService;
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<StandardResponse<PagedResponse<NotificationResponse>>> findAll(
            @RequestParam(required = false) Boolean isViewed,
            Pageable pageable
    ){
        UUID authenticatedUserId = authService.getAuthenticatedUser().userId();

        PagedResponse<NotificationResponse> response;

        if(isViewed == null)
            response = notificationService.getAll(authenticatedUserId, pageable);
        else if (isViewed)
            response = notificationService.getAllViewed(authenticatedUserId, pageable);
        else
            response = notificationService.getAllUnviewed(authenticatedUserId, pageable);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponse.success(response));
    }

    @PostMapping("/{id}")
    public ResponseEntity<StandardResponse<Void>> view(@PathVariable UUID id){
        notificationService.view(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponse.success());
    }
}
