package br.social.impacthub.infrastructure.web.docs;

import br.social.impacthub.model.dto.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Ong Category")
public interface OngCategoryControllerDocs {

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get all ONG categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ONG categories successfully retrieved."),
    })
    ResponseEntity<StandardResponse<List<String>>> getAll();
}
