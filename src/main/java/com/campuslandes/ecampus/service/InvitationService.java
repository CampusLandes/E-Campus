package com.campuslandes.ecampus.service;

import com.campuslandes.ecampus.service.dto.InvitationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.campuslandes.ecampus.domain.Invitation}.
 */
public interface InvitationService {

    /**
     * Save a invitation.
     *
     * @param invitationDTO the entity to save.
     * @return the persisted entity.
     */
    InvitationDTO save(InvitationDTO invitationDTO);

    /**
     * Get all the invitations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvitationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" invitation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvitationDTO> findOne(Long id);

    /**
     * Delete the "id" invitation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
