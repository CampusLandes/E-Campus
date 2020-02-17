package com.campuslandes.ecampus.service.mapper;

import com.campuslandes.ecampus.domain.*;
import com.campuslandes.ecampus.service.dto.LocalisationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Localisation} and its DTO {@link LocalisationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LocalisationMapper extends EntityMapper<LocalisationDTO, Localisation> {



    default Localisation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Localisation localisation = new Localisation();
        localisation.setId(id);
        return localisation;
    }
}
