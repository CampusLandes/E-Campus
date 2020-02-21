package com.campuslandes.ecampus.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.campuslandes.ecampus.domain.Trip} entity.
 */
public class TripDTO implements Serializable {

    private Long id;

    private Instant departureDate;

    private Long startLocalisationId;

    private String startLocalisationName;

    private Long endLocalisationId;

    private String endLocalisationName;

    private Long driverId;

    private String driverLogin;

    private Set<UserDTO> passengers = new HashSet<>();

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

    public Instant getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Instant departureDate) {
        this.departureDate = departureDate;
    }

    public Long getStartLocalisationId() {
        return startLocalisationId;
    }

    public void setStartLocalisationId(Long localisationId) {
        this.startLocalisationId = localisationId;
    }

    public String getStartLocalisationName() {
        return startLocalisationName;
    }

    public void setStartLocalisationName(String localisationName) {
        this.startLocalisationName = localisationName;
    }

    public Long getEndLocalisationId() {
        return endLocalisationId;
    }

    public void setEndLocalisationId(Long localisationId) {
        this.endLocalisationId = localisationId;
    }

    public String getEndLocalisationName() {
        return endLocalisationName;
    }

    public void setEndLocalisationName(String localisationName) {
        this.endLocalisationName = localisationName;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long userId) {
        this.driverId = userId;
    }

    public String getDriverLogin() {
        return driverLogin;
    }

    public void setDriverLogin(String userLogin) {
        this.driverLogin = userLogin;
    }

    public Set<UserDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<UserDTO> users) {
        this.passengers = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TripDTO tripDTO = (TripDTO) o;
        if (tripDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tripDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TripDTO{" +
            "id=" + getId() +
            ", departureDate='" + getDepartureDate() + "'" +
            ", startLocalisationId=" + getStartLocalisationId() +
            ", startLocalisationName='" + getStartLocalisationName() + "'" +
            ", endLocalisationId=" + getEndLocalisationId() +
            ", endLocalisationName='" + getEndLocalisationName() + "'" +
            ", driverId=" + getDriverId() +
            ", driverLogin='" + getDriverLogin() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
