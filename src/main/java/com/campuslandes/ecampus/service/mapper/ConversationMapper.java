package com.campuslandes.ecampus.service.mapper;

import com.campuslandes.ecampus.domain.*;
import com.campuslandes.ecampus.service.dto.ConversationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Conversation} and its DTO {@link ConversationDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ConversationMapper extends EntityMapper<ConversationDTO, Conversation> {

    @Mapping(source = "creator.id", target = "creatorId")
    @Mapping(source = "creator.login", target = "creatorLogin")
    ConversationDTO toDto(Conversation conversation);

    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "removeMessage", ignore = true)
    @Mapping(source = "creatorId", target = "creator")
    @Mapping(target = "removeParticipants", ignore = true)
    Conversation toEntity(ConversationDTO conversationDTO);

    default Conversation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Conversation conversation = new Conversation();
        conversation.setId(id);
        return conversation;
    }
}
