package com.campuslandes.ecampus.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.campuslandes.ecampus.domain.enumeration.EventStatus;

/**
 * A Event.
 */
@Entity
@Table(name = "event")
public class Event extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "jhi_desc")
    private String desc;

    @Column(name = "completion_date")
    private Instant completionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EventStatus status;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("events")
    private EventType type;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("events")
    private Localisation localisation;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("events")
    private User responsible;

    @ManyToMany
    @JoinTable(name = "event_participants",
               joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "participants_id", referencedColumnName = "id"))
    private Set<User> participants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Event title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public Event desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Instant getCompletionDate() {
        return completionDate;
    }

    public Event completionDate(Instant completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public void setCompletionDate(Instant completionDate) {
        this.completionDate = completionDate;
    }

    public EventStatus getStatus() {
        return status;
    }

    public Event status(EventStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Event imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public EventType getType() {
        return type;
    }

    public Event type(EventType eventType) {
        this.type = eventType;
        return this;
    }

    public void setType(EventType eventType) {
        this.type = eventType;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public Event localisation(Localisation localisation) {
        this.localisation = localisation;
        return this;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public User getResponsible() {
        return responsible;
    }

    public Event responsible(User user) {
        this.responsible = user;
        return this;
    }

    public void setResponsible(User user) {
        this.responsible = user;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public Event participants(Set<User> users) {
        this.participants = users;
        return this;
    }

    public Event addParticipants(User user) {
        this.participants.add(user);
        return this;
    }

    public Event removeParticipants(User user) {
        this.participants.remove(user);
        return this;
    }

    public void setParticipants(Set<User> users) {
        this.participants = users;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        return id != null && id.equals(((Event) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", desc='" + getDesc() + "'" +
            ", completionDate='" + getCompletionDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}
