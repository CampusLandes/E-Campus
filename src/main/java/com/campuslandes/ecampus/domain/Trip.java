package com.campuslandes.ecampus.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Trip.
 */
@Entity
@Table(name = "trip")
public class Trip extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "departure_date")
    private Instant departureDate;

    @ManyToOne
    @JsonIgnoreProperties("trips")
    private Localisation startLocalisation;

    @ManyToOne
    @JsonIgnoreProperties("trips")
    private Localisation endLocalisation;

    @ManyToOne
    @JsonIgnoreProperties("trips")
    private User driver;

    @ManyToMany
    @JoinTable(name = "trip_passenger",
               joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    private Set<User> passengers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDepartureDate() {
        return departureDate;
    }

    public Trip departureDate(Instant departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public void setDepartureDate(Instant departureDate) {
        this.departureDate = departureDate;
    }

    public Localisation getStartLocalisation() {
        return startLocalisation;
    }

    public Trip startLocalisation(Localisation localisation) {
        this.startLocalisation = localisation;
        return this;
    }

    public void setStartLocalisation(Localisation localisation) {
        this.startLocalisation = localisation;
    }

    public Localisation getEndLocalisation() {
        return endLocalisation;
    }

    public Trip endLocalisation(Localisation localisation) {
        this.endLocalisation = localisation;
        return this;
    }

    public void setEndLocalisation(Localisation localisation) {
        this.endLocalisation = localisation;
    }

    public User getDriver() {
        return driver;
    }

    public Trip driver(User user) {
        this.driver = user;
        return this;
    }

    public void setDriver(User user) {
        this.driver = user;
    }

    public Set<User> getPassengers() {
        return passengers;
    }

    public Trip passengers(Set<User> users) {
        this.passengers = users;
        return this;
    }

    public Trip addPassenger(User user) {
        this.passengers.add(user);
        return this;
    }

    public Trip removePassenger(User user) {
        this.passengers.remove(user);
        return this;
    }

    public void setPassengers(Set<User> users) {
        this.passengers = users;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trip)) {
            return false;
        }
        return id != null && id.equals(((Trip) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Trip{" +
            "id=" + getId() +
            ", departureDate='" + getDepartureDate() + "'" +
            "}";
    }
}
