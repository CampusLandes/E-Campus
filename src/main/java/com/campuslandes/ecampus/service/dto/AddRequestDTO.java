package com.campuslandes.ecampus.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.campuslandes.ecampus.domain.enumeration.InvitationStatus;

/**
 * A DTO for the {@link com.campuslandes.ecampus.domain.AddRequest} entity.
 */
public class AddRequestDTO implements Serializable {

    private Long id;

    private String message;

    @NotNull
    private InvitationStatus status;


    private Long eventId;

    private String eventTitle;

    private Long userId;

    private String userLogin;

    private Long validatorId;

    private String validatorLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getValidatorId() {
        return validatorId;
    }

    public void setValidatorId(Long userId) {
        this.validatorId = userId;
    }

    public String getValidatorLogin() {
        return validatorLogin;
    }

    public void setValidatorLogin(String userLogin) {
        this.validatorLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddRequestDTO addRequestDTO = (AddRequestDTO) o;
        if (addRequestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addRequestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AddRequestDTO{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", eventId=" + getEventId() +
            ", eventTitle='" + getEventTitle() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            ", validatorId=" + getValidatorId() +
            ", validatorLogin='" + getValidatorLogin() + "'" +
            "}";
    }
}
