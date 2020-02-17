package com.campuslandes.ecampus.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.campuslandes.ecampus.domain.enumeration.InvitationStatus;

/**
 * A AddRequest.
 */
@Entity
@Table(name = "add_request")
public class AddRequest extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "message")
    private String message;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InvitationStatus status;

    @ManyToOne
    @JsonIgnoreProperties("addRequests")
    private Event event;

    @ManyToOne
    @JsonIgnoreProperties("addRequests")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("addRequests")
    private User validator;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public AddRequest message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InvitationStatus getStatus() {
        return status;
    }

    public AddRequest status(InvitationStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }

    public Event getEvent() {
        return event;
    }

    public AddRequest event(Event event) {
        this.event = event;
        return this;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public AddRequest user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getValidator() {
        return validator;
    }

    public AddRequest validator(User user) {
        this.validator = user;
        return this;
    }

    public void setValidator(User user) {
        this.validator = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddRequest)) {
            return false;
        }
        return id != null && id.equals(((AddRequest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AddRequest{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
