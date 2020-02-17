package com.campuslandes.ecampus.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.campuslandes.ecampus.domain.enumeration.UserType;

/**
 * A DTO for the {@link com.campuslandes.ecampus.domain.TripEvaluation} entity.
 */
public class TripEvaluationDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer note;

    private String comment;

    @NotNull
    private UserType type;


    private Long evaluatedId;

    private String evaluatedLogin;

    private Long assessorId;

    private String assessorLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Long getEvaluatedId() {
        return evaluatedId;
    }

    public void setEvaluatedId(Long userId) {
        this.evaluatedId = userId;
    }

    public String getEvaluatedLogin() {
        return evaluatedLogin;
    }

    public void setEvaluatedLogin(String userLogin) {
        this.evaluatedLogin = userLogin;
    }

    public Long getAssessorId() {
        return assessorId;
    }

    public void setAssessorId(Long userId) {
        this.assessorId = userId;
    }

    public String getAssessorLogin() {
        return assessorLogin;
    }

    public void setAssessorLogin(String userLogin) {
        this.assessorLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TripEvaluationDTO tripEvaluationDTO = (TripEvaluationDTO) o;
        if (tripEvaluationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tripEvaluationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TripEvaluationDTO{" +
            "id=" + getId() +
            ", note=" + getNote() +
            ", comment='" + getComment() + "'" +
            ", type='" + getType() + "'" +
            ", evaluatedId=" + getEvaluatedId() +
            ", evaluatedLogin='" + getEvaluatedLogin() + "'" +
            ", assessorId=" + getAssessorId() +
            ", assessorLogin='" + getAssessorLogin() + "'" +
            "}";
    }
}
