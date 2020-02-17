package com.campuslandes.ecampus.service;

import com.campuslandes.ecampus.service.dto.ConversationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.campuslandes.ecampus.domain.Conversation}.
 */
public interface ConversationService {

    /**
     * Save a conversation.
     *
     * @param conversationDTO the entity to save.
     * @return the persisted entity.
     */
    ConversationDTO save(ConversationDTO conversationDTO);

    /**
     * Get all the conversations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ConversationDTO> findAll(Pageable pageable);

    /**
     * Get all the conversations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ConversationDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" conversation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ConversationDTO> findOne(Long id);

    /**
     * Delete the "id" conversation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
