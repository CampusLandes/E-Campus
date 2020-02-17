package com.campuslandes.ecampus.service;

import com.campuslandes.ecampus.service.dto.LocalisationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.campuslandes.ecampus.domain.Localisation}.
 */
public interface LocalisationService {

    /**
     * Save a localisation.
     *
     * @param localisationDTO the entity to save.
     * @return the persisted entity.
     */
    LocalisationDTO save(LocalisationDTO localisationDTO);

    /**
     * Get all the localisations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LocalisationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" localisation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LocalisationDTO> findOne(Long id);

    /**
     * Delete the "id" localisation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
