package br.social.impacthub.infrastructure.web.docs;

import br.social.impacthub.model.dto.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "ONG Participants")
public interface OngParticipantRoleControllerDocs {

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get all ONG Participant roles")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "All ONG Participant roles retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponse.class)
                    )
            ),
    })
    ResponseEntity<StandardResponse<List<String>>> getOngParticipantRoles();
}
