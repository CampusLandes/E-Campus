package com.campuslandes.ecampus.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Message.
 */
@Entity
@Table(name = "message")
public class Message extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "message", nullable = false)
    private String message;

    @ManyToMany
    @JoinTable(name = "message_saw_people",
               joinColumns = @JoinColumn(name = "message_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "saw_people_id", referencedColumnName = "id"))
    private Set<User> sawPeople = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("messages")
    private Conversation conversation;

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

    public Message message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<User> getSawPeople() {
        return sawPeople;
    }

    public Message sawPeople(Set<User> users) {
        this.sawPeople = users;
        return this;
    }

    public Message addSawPeople(User user) {
        this.sawPeople.add(user);
        return this;
    }

    public Message removeSawPeople(User user) {
        this.sawPeople.remove(user);
        return this;
    }

    public void setSawPeople(Set<User> users) {
        this.sawPeople = users;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Message conversation(Conversation conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }
        return id != null && id.equals(((Message) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
