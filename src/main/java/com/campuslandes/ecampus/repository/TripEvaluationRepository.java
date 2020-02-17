package com.campuslandes.ecampus.repository;

import com.campuslandes.ecampus.domain.TripEvaluation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the TripEvaluation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TripEvaluationRepository extends JpaRepository<TripEvaluation, Long> {

    @Query("select tripEvaluation from TripEvaluation tripEvaluation where tripEvaluation.evaluated.login = ?#{principal.username}")
    List<TripEvaluation> findByEvaluatedIsCurrentUser();

    @Query("select tripEvaluation from TripEvaluation tripEvaluation where tripEvaluation.assessor.login = ?#{principal.username}")
    List<TripEvaluation> findByAssessorIsCurrentUser();

}
