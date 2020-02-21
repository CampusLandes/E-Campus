package com.campuslandes.ecampus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.campuslandes.ecampus.domain.enumeration.EventStatus;

/**
 * A DTO for the {@link com.campuslandes.ecampus.domain.Event} entity.
 */
public class EventDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    private String desc;

    private Instant completionDate;

    @NotNull
    private EventStatus status;

    private Long typeId;

    private String typeName;

    private Long localisationId;

    private String localisationName;

    private Long responsibleId;

    private String responsibleLogin;

    private Set<UserDTO> participants = new HashSet<>();

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public Long getId() {
        return id;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Instant getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Instant completionDate) {
        this.completionDate = completionDate;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long eventTypeId) {
        this.typeId = eventTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String eventTypeName) {
        this.typeName = eventTypeName;
    }

    public Long getLocalisationId() {
        return localisationId;
    }

    public void setLocalisationId(Long localisationId) {
        this.localisationId = localisationId;
    }

    public String getLocalisationName() {
        return localisationName;
    }

    public void setLocalisationName(String localisationName) {
        this.localisationName = localisationName;
    }

    public Long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Long userId) {
        this.responsibleId = userId;
    }

    public String getResponsibleLogin() {
        return responsibleLogin;
    }

    public void setResponsibleLogin(String userLogin) {
        this.responsibleLogin = userLogin;
    }

    public Set<UserDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<UserDTO> users) {
        this.participants = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventDTO eventDTO = (EventDTO) o;
        if (eventDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", desc='" + getDesc() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", typeId=" + getTypeId() +
            ", typeName='" + getTypeName() + "'" +
            ", localisationId=" + getLocalisationId() +
            ", localisationName='" + getLocalisationName() + "'" +
            ", responsibleId=" + getResponsibleId() +
            ", responsibleLogin='" + getResponsibleLogin() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
