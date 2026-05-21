package br.social.impacthub.infrastructure.web;

import br.social.impacthub.infrastructure.web.docs.OngCategoryControllerDocs;
import br.social.impacthub.model.dto.PagedResponse;
import br.social.impacthub.model.dto.StandardResponse;
import br.social.impacthub.service.OngService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/ong-categories")
public class OngCategoryController implements OngCategoryControllerDocs {
    private final OngService ongService;

    public OngCategoryController(OngService ongService) {
        this.ongService = ongService;
    }

    @GetMapping
    public ResponseEntity<StandardResponse<List<String>>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(StandardResponse.success(ongService.getAllCategories()));
    }
}
