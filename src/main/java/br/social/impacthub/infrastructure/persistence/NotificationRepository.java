package br.social.impacthub.infrastructure.persistence;

import br.social.impacthub.model.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    @Query(value = """
        SELECT n
        FROM Notification n
        WHERE n.userProfile.userId = :userId
        """
    )
    Page<Notification> findAllByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query(value = """
        SELECT n
        FROM Notification n
        WHERE n.userProfile.userId = :userId
        AND n.isViewed IS FALSE
        """
    )
    Page<Notification> findAllUnviewedByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query(value = """
        SELECT n
        FROM Notification n
        WHERE n.userProfile.userId = :userId
        AND n.isViewed IS TRUE
        """
    )
    Page<Notification> findAllViewedByUserId(UUID userId, Pageable pageable);
}
