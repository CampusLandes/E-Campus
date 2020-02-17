package com.campuslandes.ecampus.service.mapper;

import com.campuslandes.ecampus.domain.*;
import com.campuslandes.ecampus.service.dto.TripDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Trip} and its DTO {@link TripDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocalisationMapper.class, UserMapper.class})
public interface TripMapper extends EntityMapper<TripDTO, Trip> {

    @Mapping(source = "startLocalisation.id", target = "startLocalisationId")
    @Mapping(source = "startLocalisation.name", target = "startLocalisationName")
    @Mapping(source = "endLocalisation.id", target = "endLocalisationId")
    @Mapping(source = "endLocalisation.name", target = "endLocalisationName")
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "driver.login", target = "driverLogin")
    TripDTO toDto(Trip trip);

    @Mapping(source = "startLocalisationId", target = "startLocalisation")
    @Mapping(source = "endLocalisationId", target = "endLocalisation")
    @Mapping(source = "driverId", target = "driver")
    @Mapping(target = "removePassenger", ignore = true)
    Trip toEntity(TripDTO tripDTO);

    default Trip fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trip trip = new Trip();
        trip.setId(id);
        return trip;
    }
}
