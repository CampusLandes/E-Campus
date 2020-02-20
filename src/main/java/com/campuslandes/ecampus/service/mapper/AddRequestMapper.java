package com.campuslandes.ecampus.service.mapper;

import com.campuslandes.ecampus.domain.*;
import com.campuslandes.ecampus.service.dto.AddRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddRequest} and its DTO {@link AddRequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {EventMapper.class, UserMapper.class})
public interface AddRequestMapper extends EntityMapper<AddRequestDTO, AddRequest> {

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.title", target = "eventTitle")
    @Mapping(source = "validator.id", target = "validatorId")
    @Mapping(source = "validator.login", target = "validatorLogin")
    AddRequestDTO toDto(AddRequest addRequest);

    @Mapping(source = "eventId", target = "event")
    @Mapping(source = "validatorId", target = "validator")
    AddRequest toEntity(AddRequestDTO addRequestDTO);

    default AddRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        AddRequest addRequest = new AddRequest();
        addRequest.setId(id);
        return addRequest;
    }
}
