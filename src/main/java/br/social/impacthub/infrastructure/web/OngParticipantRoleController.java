package br.social.impacthub.infrastructure.web;

import br.social.impacthub.model.dto.StandardResponse;
import br.social.impacthub.model.entity.OngParticipantRole;
import br.social.impacthub.service.OngParticipantRoleService;
import br.social.impacthub.service.OngParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: openAPI docs
@RestController
@RequestMapping("/api/v1/ongs/participant-roles")
public class OngParticipantRoleController {
    private final OngParticipantRoleService ongParticipantRoleService;

    public OngParticipantRoleController(OngParticipantRoleService ongParticipantRoleService) {
        this.ongParticipantRoleService = ongParticipantRoleService;
    }

    @GetMapping
    public ResponseEntity<StandardResponse<List<String>>> getOngParticipantRoles(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponse.success(ongParticipantRoleService.getAll()));
    }
}
