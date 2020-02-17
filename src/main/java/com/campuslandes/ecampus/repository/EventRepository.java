package com.campuslandes.ecampus.repository;

import com.campuslandes.ecampus.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Event entity.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select event from Event event where event.responsible.login = ?#{principal.username}")
    List<Event> findByResponsibleIsCurrentUser();

    @Query(value = "select distinct event from Event event left join fetch event.participants",
        countQuery = "select count(distinct event) from Event event")
    Page<Event> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct event from Event event left join fetch event.participants")
    List<Event> findAllWithEagerRelationships();

    @Query("select event from Event event left join fetch event.participants where event.id =:id")
    Optional<Event> findOneWithEagerRelationships(@Param("id") Long id);

}
