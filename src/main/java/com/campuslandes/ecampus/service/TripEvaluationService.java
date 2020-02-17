package com.campuslandes.ecampus.service;

import com.campuslandes.ecampus.service.dto.TripEvaluationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.campuslandes.ecampus.domain.TripEvaluation}.
 */
public interface TripEvaluationService {

    /**
     * Save a tripEvaluation.
     *
     * @param tripEvaluationDTO the entity to save.
     * @return the persisted entity.
     */
    TripEvaluationDTO save(TripEvaluationDTO tripEvaluationDTO);

    /**
     * Get all the tripEvaluations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TripEvaluationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tripEvaluation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TripEvaluationDTO> findOne(Long id);

    /**
     * Delete the "id" tripEvaluation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
