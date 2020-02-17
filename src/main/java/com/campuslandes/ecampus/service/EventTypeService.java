package com.campuslandes.ecampus.service;

import com.campuslandes.ecampus.service.dto.EventTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.campuslandes.ecampus.domain.EventType}.
 */
public interface EventTypeService {

    /**
     * Save a eventType.
     *
     * @param eventTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EventTypeDTO save(EventTypeDTO eventTypeDTO);

    /**
     * Get all the eventTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EventTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" eventType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EventTypeDTO> findOne(Long id);

    /**
     * Delete the "id" eventType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
