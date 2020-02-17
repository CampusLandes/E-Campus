package com.campuslandes.ecampus.service.mapper;

import com.campuslandes.ecampus.domain.*;
import com.campuslandes.ecampus.service.dto.InvitationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Invitation} and its DTO {@link InvitationDTO}.
 */
@Mapper(componentModel = "spring", uses = {EventMapper.class, UserMapper.class})
public interface InvitationMapper extends EntityMapper<InvitationDTO, Invitation> {

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.title", target = "eventTitle")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    InvitationDTO toDto(Invitation invitation);

    @Mapping(source = "eventId", target = "event")
    @Mapping(source = "userId", target = "user")
    Invitation toEntity(InvitationDTO invitationDTO);

    default Invitation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Invitation invitation = new Invitation();
        invitation.setId(id);
        return invitation;
    }
}
