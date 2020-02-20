package com.campuslandes.ecampus.repository;

import com.campuslandes.ecampus.domain.AddRequest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the AddRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddRequestRepository extends JpaRepository<AddRequest, Long> {

    @Query("select addRequest from AddRequest addRequest where addRequest.validator.login = ?#{principal.username}")
    List<AddRequest> findByValidatorIsCurrentUser();

}
