package com.campuslandes.ecampus.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.campuslandes.ecampus.domain.Localisation} entity.
 */
public class LocalisationDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String localisation;

    private String gpsPosition;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getGpsPosition() {
        return gpsPosition;
    }

    public void setGpsPosition(String gpsPosition) {
        this.gpsPosition = gpsPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LocalisationDTO localisationDTO = (LocalisationDTO) o;
        if (localisationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), localisationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LocalisationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", localisation='" + getLocalisation() + "'" +
            ", gpsPosition='" + getGpsPosition() + "'" +
            "}";
    }
}
