package com.campuslandes.ecampus.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.campuslandes.ecampus.domain.enumeration.UserType;

/**
 * A TripEvaluation.
 */
@Entity
@Table(name = "trip_evaluation")
public class TripEvaluation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "note", nullable = false)
    private Integer note;

    @Column(name = "comment")
    private String comment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserType type;

    @ManyToOne
    @JsonIgnoreProperties("tripEvaluations")
    private User evaluated;

    @ManyToOne
    @JsonIgnoreProperties("tripEvaluations")
    private User assessor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNote() {
        return note;
    }

    public TripEvaluation note(Integer note) {
        this.note = note;
        return this;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public TripEvaluation comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserType getType() {
        return type;
    }

    public TripEvaluation type(UserType type) {
        this.type = type;
        return this;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public User getEvaluated() {
        return evaluated;
    }

    public TripEvaluation evaluated(User user) {
        this.evaluated = user;
        return this;
    }

    public void setEvaluated(User user) {
        this.evaluated = user;
    }

    public User getAssessor() {
        return assessor;
    }

    public TripEvaluation assessor(User user) {
        this.assessor = user;
        return this;
    }

    public void setAssessor(User user) {
        this.assessor = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TripEvaluation)) {
            return false;
        }
        return id != null && id.equals(((TripEvaluation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TripEvaluation{" +
            "id=" + getId() +
            ", note=" + getNote() +
            ", comment='" + getComment() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
