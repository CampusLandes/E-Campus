package com.campuslandes.ecampus.service;

import com.campuslandes.ecampus.service.dto.AddRequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.campuslandes.ecampus.domain.AddRequest}.
 */
public interface AddRequestService {

    /**
     * Save a addRequest.
     *
     * @param addRequestDTO the entity to save.
     * @return the persisted entity.
     */
    AddRequestDTO save(AddRequestDTO addRequestDTO);

    /**
     * Get all the addRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AddRequestDTO> findAll(Pageable pageable);


    /**
     * Get the "id" addRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AddRequestDTO> findOne(Long id);

    /**
     * Delete the "id" addRequest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
