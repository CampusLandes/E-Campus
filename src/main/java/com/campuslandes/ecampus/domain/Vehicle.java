package com.campuslandes.ecampus.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull
    @Column(name = "nb_place", nullable = false)
    private Integer nbPlace;

    @Column(name = "bagage")
    private String bagage;

    @Column(name = "trunk_volume")
    private Integer trunkVolume;

    @Column(name = "smoking")
    private Boolean smoking;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Vehicle type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public Vehicle nbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
        return this;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getBagage() {
        return bagage;
    }

    public Vehicle bagage(String bagage) {
        this.bagage = bagage;
        return this;
    }

    public void setBagage(String bagage) {
        this.bagage = bagage;
    }

    public Integer getTrunkVolume() {
        return trunkVolume;
    }

    public Vehicle trunkVolume(Integer trunkVolume) {
        this.trunkVolume = trunkVolume;
        return this;
    }

    public void setTrunkVolume(Integer trunkVolume) {
        this.trunkVolume = trunkVolume;
    }

    public Boolean isSmoking() {
        return smoking;
    }

    public Vehicle smoking(Boolean smoking) {
        this.smoking = smoking;
        return this;
    }

    public void setSmoking(Boolean smoking) {
        this.smoking = smoking;
    }

    public User getUser() {
        return user;
    }

    public Vehicle user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        return id != null && id.equals(((Vehicle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", nbPlace=" + getNbPlace() +
            ", bagage='" + getBagage() + "'" +
            ", trunkVolume=" + getTrunkVolume() +
            ", smoking='" + isSmoking() + "'" +
            "}";
    }
}
