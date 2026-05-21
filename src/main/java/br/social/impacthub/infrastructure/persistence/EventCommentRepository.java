package br.social.impacthub.infrastructure.persistence;

import br.social.impacthub.model.entity.EventComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventCommentRepository extends JpaRepository<EventComment, UUID> {
    public Page<EventComment> findAllByEventId(UUID eventId, Pageable pageable);
}
