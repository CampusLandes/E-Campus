package com.campuslandes.ecampus.service.mapper;

import com.campuslandes.ecampus.domain.*;
import com.campuslandes.ecampus.service.dto.TripEvaluationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TripEvaluation} and its DTO {@link TripEvaluationDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TripEvaluationMapper extends EntityMapper<TripEvaluationDTO, TripEvaluation> {

    @Mapping(source = "evaluated.id", target = "evaluatedId")
    @Mapping(source = "evaluated.login", target = "evaluatedLogin")
    @Mapping(source = "assessor.id", target = "assessorId")
    @Mapping(source = "assessor.login", target = "assessorLogin")
    TripEvaluationDTO toDto(TripEvaluation tripEvaluation);

    @Mapping(source = "evaluatedId", target = "evaluated")
    @Mapping(source = "assessorId", target = "assessor")
    TripEvaluation toEntity(TripEvaluationDTO tripEvaluationDTO);

    default TripEvaluation fromId(Long id) {
        if (id == null) {
            return null;
        }
        TripEvaluation tripEvaluation = new TripEvaluation();
        tripEvaluation.setId(id);
        return tripEvaluation;
    }
}
