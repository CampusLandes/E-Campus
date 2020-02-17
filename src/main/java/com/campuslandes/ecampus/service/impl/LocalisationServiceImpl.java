package com.campuslandes.ecampus.service.impl;

import com.campuslandes.ecampus.service.LocalisationService;
import com.campuslandes.ecampus.domain.Localisation;
import com.campuslandes.ecampus.repository.LocalisationRepository;
import com.campuslandes.ecampus.service.dto.LocalisationDTO;
import com.campuslandes.ecampus.service.mapper.LocalisationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Localisation}.
 */
@Service
@Transactional
public class LocalisationServiceImpl implements LocalisationService {

    private final Logger log = LoggerFactory.getLogger(LocalisationServiceImpl.class);

    private final LocalisationRepository localisationRepository;

    private final LocalisationMapper localisationMapper;

    public LocalisationServiceImpl(LocalisationRepository localisationRepository, LocalisationMapper localisationMapper) {
        this.localisationRepository = localisationRepository;
        this.localisationMapper = localisationMapper;
    }

    /**
     * Save a localisation.
     *
     * @param localisationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LocalisationDTO save(LocalisationDTO localisationDTO) {
        log.debug("Request to save Localisation : {}", localisationDTO);
        Localisation localisation = localisationMapper.toEntity(localisationDTO);
        localisation = localisationRepository.save(localisation);
        return localisationMapper.toDto(localisation);
    }

    /**
     * Get all the localisations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LocalisationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Localisations");
        return localisationRepository.findAll(pageable)
            .map(localisationMapper::toDto);
    }


    /**
     * Get one localisation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LocalisationDTO> findOne(Long id) {
        log.debug("Request to get Localisation : {}", id);
        return localisationRepository.findById(id)
            .map(localisationMapper::toDto);
    }

    /**
     * Delete the localisation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Localisation : {}", id);
        localisationRepository.deleteById(id);
    }
}
