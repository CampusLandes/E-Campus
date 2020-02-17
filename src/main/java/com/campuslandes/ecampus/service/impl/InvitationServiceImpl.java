package com.campuslandes.ecampus.service.impl;

import com.campuslandes.ecampus.service.InvitationService;
import com.campuslandes.ecampus.domain.Invitation;
import com.campuslandes.ecampus.repository.InvitationRepository;
import com.campuslandes.ecampus.service.dto.InvitationDTO;
import com.campuslandes.ecampus.service.mapper.InvitationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Invitation}.
 */
@Service
@Transactional
public class InvitationServiceImpl implements InvitationService {

    private final Logger log = LoggerFactory.getLogger(InvitationServiceImpl.class);

    private final InvitationRepository invitationRepository;

    private final InvitationMapper invitationMapper;

    public InvitationServiceImpl(InvitationRepository invitationRepository, InvitationMapper invitationMapper) {
        this.invitationRepository = invitationRepository;
        this.invitationMapper = invitationMapper;
    }

    /**
     * Save a invitation.
     *
     * @param invitationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvitationDTO save(InvitationDTO invitationDTO) {
        log.debug("Request to save Invitation : {}", invitationDTO);
        Invitation invitation = invitationMapper.toEntity(invitationDTO);
        invitation = invitationRepository.save(invitation);
        return invitationMapper.toDto(invitation);
    }

    /**
     * Get all the invitations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvitationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Invitations");
        return invitationRepository.findAll(pageable)
            .map(invitationMapper::toDto);
    }


    /**
     * Get one invitation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvitationDTO> findOne(Long id) {
        log.debug("Request to get Invitation : {}", id);
        return invitationRepository.findById(id)
            .map(invitationMapper::toDto);
    }

    /**
     * Delete the invitation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Invitation : {}", id);
        invitationRepository.deleteById(id);
    }
}
