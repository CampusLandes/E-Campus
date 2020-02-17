package com.campuslandes.ecampus.repository;

import com.campuslandes.ecampus.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Message entity.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select distinct message from Message message left join fetch message.sawPeople",
        countQuery = "select count(distinct message) from Message message")
    Page<Message> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct message from Message message left join fetch message.sawPeople")
    List<Message> findAllWithEagerRelationships();

    @Query("select message from Message message left join fetch message.sawPeople where message.id =:id")
    Optional<Message> findOneWithEagerRelationships(@Param("id") Long id);

}
