package br.social.impacthub.infrastructure.web.docs;

import br.social.impacthub.model.dto.NotificationResponse;
import br.social.impacthub.model.dto.PagedResponse;
import br.social.impacthub.model.dto.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Tag(name = "Notifications")
public interface NotificationControllerDocs {
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get user notifications")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Notifications retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authenticated",
                    content = @Content
            )
    })
    ResponseEntity<StandardResponse<PagedResponse<NotificationResponse>>> findAll(Boolean isViewed, Pageable pageable);

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "View notification")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Notifications viewed successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Notification not found",
                    content = @Content
            )
    })
    ResponseEntity<StandardResponse<Void>> view(UUID id);
}
