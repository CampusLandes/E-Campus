package com.campuslandes.ecampus.service.mapper;

import com.campuslandes.ecampus.domain.*;
import com.campuslandes.ecampus.service.dto.EventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Event} and its DTO {@link EventDTO}.
 */
@Mapper(componentModel = "spring", uses = {EventTypeMapper.class, LocalisationMapper.class, UserMapper.class})
public interface EventMapper extends EntityMapper<EventDTO, Event> {

    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "type.name", target = "typeName")
    @Mapping(source = "localisation.id", target = "localisationId")
    @Mapping(source = "localisation.name", target = "localisationName")
    @Mapping(source = "responsible.id", target = "responsibleId")
    @Mapping(source = "responsible.login", target = "responsibleLogin")
    EventDTO toDto(Event event);

    @Mapping(source = "typeId", target = "type")
    @Mapping(source = "localisationId", target = "localisation")
    @Mapping(source = "responsibleId", target = "responsible")
    @Mapping(target = "removeParticipants", ignore = true)
    Event toEntity(EventDTO eventDTO);

    default Event fromId(Long id) {
        if (id == null) {
            return null;
        }
        Event event = new Event();
        event.setId(id);
        return event;
    }
}
