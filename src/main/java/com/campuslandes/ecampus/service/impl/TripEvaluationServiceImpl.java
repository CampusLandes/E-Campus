package com.campuslandes.ecampus.service.impl;

import com.campuslandes.ecampus.service.TripEvaluationService;
import com.campuslandes.ecampus.domain.TripEvaluation;
import com.campuslandes.ecampus.repository.TripEvaluationRepository;
import com.campuslandes.ecampus.service.dto.TripEvaluationDTO;
import com.campuslandes.ecampus.service.mapper.TripEvaluationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TripEvaluation}.
 */
@Service
@Transactional
public class TripEvaluationServiceImpl implements TripEvaluationService {

    private final Logger log = LoggerFactory.getLogger(TripEvaluationServiceImpl.class);

    private final TripEvaluationRepository tripEvaluationRepository;

    private final TripEvaluationMapper tripEvaluationMapper;

    public TripEvaluationServiceImpl(TripEvaluationRepository tripEvaluationRepository, TripEvaluationMapper tripEvaluationMapper) {
        this.tripEvaluationRepository = tripEvaluationRepository;
        this.tripEvaluationMapper = tripEvaluationMapper;
    }

    /**
     * Save a tripEvaluation.
     *
     * @param tripEvaluationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TripEvaluationDTO save(TripEvaluationDTO tripEvaluationDTO) {
        log.debug("Request to save TripEvaluation : {}", tripEvaluationDTO);
        TripEvaluation tripEvaluation = tripEvaluationMapper.toEntity(tripEvaluationDTO);
        tripEvaluation = tripEvaluationRepository.save(tripEvaluation);
        return tripEvaluationMapper.toDto(tripEvaluation);
    }

    /**
     * Get all the tripEvaluations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TripEvaluationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TripEvaluations");
        return tripEvaluationRepository.findAll(pageable)
            .map(tripEvaluationMapper::toDto);
    }


    /**
     * Get one tripEvaluation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TripEvaluationDTO> findOne(Long id) {
        log.debug("Request to get TripEvaluation : {}", id);
        return tripEvaluationRepository.findById(id)
            .map(tripEvaluationMapper::toDto);
    }

    /**
     * Delete the tripEvaluation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TripEvaluation : {}", id);
        tripEvaluationRepository.deleteById(id);
    }
}
