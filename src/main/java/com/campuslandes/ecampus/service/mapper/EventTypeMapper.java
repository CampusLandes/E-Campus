package com.campuslandes.ecampus.service.mapper;

import com.campuslandes.ecampus.domain.*;
import com.campuslandes.ecampus.service.dto.EventTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventType} and its DTO {@link EventTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventTypeMapper extends EntityMapper<EventTypeDTO, EventType> {



    default EventType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventType eventType = new EventType();
        eventType.setId(id);
        return eventType;
    }
}
