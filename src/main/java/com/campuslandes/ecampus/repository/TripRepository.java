package com.campuslandes.ecampus.repository;

import com.campuslandes.ecampus.domain.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Trip entity.
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("select trip from Trip trip where trip.driver.login = ?#{principal.username}")
    List<Trip> findByDriverIsCurrentUser();

    @Query(value = "select distinct trip from Trip trip left join fetch trip.passengers",
        countQuery = "select count(distinct trip) from Trip trip")
    Page<Trip> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct trip from Trip trip left join fetch trip.passengers")
    List<Trip> findAllWithEagerRelationships();

    @Query("select trip from Trip trip left join fetch trip.passengers where trip.id =:id")
    Optional<Trip> findOneWithEagerRelationships(@Param("id") Long id);

}
