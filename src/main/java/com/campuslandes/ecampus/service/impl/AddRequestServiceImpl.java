package com.campuslandes.ecampus.service.impl;

import com.campuslandes.ecampus.service.AddRequestService;
import com.campuslandes.ecampus.domain.AddRequest;
import com.campuslandes.ecampus.repository.AddRequestRepository;
import com.campuslandes.ecampus.service.dto.AddRequestDTO;
import com.campuslandes.ecampus.service.mapper.AddRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AddRequest}.
 */
@Service
@Transactional
public class AddRequestServiceImpl implements AddRequestService {

    private final Logger log = LoggerFactory.getLogger(AddRequestServiceImpl.class);

    private final AddRequestRepository addRequestRepository;

    private final AddRequestMapper addRequestMapper;

    public AddRequestServiceImpl(AddRequestRepository addRequestRepository, AddRequestMapper addRequestMapper) {
        this.addRequestRepository = addRequestRepository;
        this.addRequestMapper = addRequestMapper;
    }

    /**
     * Save a addRequest.
     *
     * @param addRequestDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AddRequestDTO save(AddRequestDTO addRequestDTO) {
        log.debug("Request to save AddRequest : {}", addRequestDTO);
        AddRequest addRequest = addRequestMapper.toEntity(addRequestDTO);
        addRequest = addRequestRepository.save(addRequest);
        return addRequestMapper.toDto(addRequest);
    }

    /**
     * Get all the addRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AddRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AddRequests");
        return addRequestRepository.findAll(pageable)
            .map(addRequestMapper::toDto);
    }


    /**
     * Get one addRequest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AddRequestDTO> findOne(Long id) {
        log.debug("Request to get AddRequest : {}", id);
        return addRequestRepository.findById(id)
            .map(addRequestMapper::toDto);
    }

    /**
     * Delete the addRequest by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AddRequest : {}", id);
        addRequestRepository.deleteById(id);
    }
}
