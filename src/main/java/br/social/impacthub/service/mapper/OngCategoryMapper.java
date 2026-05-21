package br.social.impacthub.service.mapper;

import br.social.impacthub.model.entity.OngCategory;
import org.springframework.stereotype.Component;

@Component
public class OngCategoryMapper {
    public String toResponse(OngCategory entity) {
        return entity.getName();
    }
}
