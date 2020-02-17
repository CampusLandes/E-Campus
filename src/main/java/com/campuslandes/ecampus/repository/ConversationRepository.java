package com.campuslandes.ecampus.repository;

import com.campuslandes.ecampus.domain.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Conversation entity.
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("select conversation from Conversation conversation where conversation.creator.login = ?#{principal.username}")
    List<Conversation> findByCreatorIsCurrentUser();

    @Query(value = "select distinct conversation from Conversation conversation left join fetch conversation.participants",
        countQuery = "select count(distinct conversation) from Conversation conversation")
    Page<Conversation> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct conversation from Conversation conversation left join fetch conversation.participants")
    List<Conversation> findAllWithEagerRelationships();

    @Query("select conversation from Conversation conversation left join fetch conversation.participants where conversation.id =:id")
    Optional<Conversation> findOneWithEagerRelationships(@Param("id") Long id);

}
