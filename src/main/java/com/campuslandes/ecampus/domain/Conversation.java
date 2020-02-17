package com.campuslandes.ecampus.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.campuslandes.ecampus.domain.enumeration.ConversationType;

import com.campuslandes.ecampus.domain.enumeration.ConversationPolicyType;

/**
 * A Conversation.
 */
@Entity
@Table(name = "conversation")
public class Conversation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ConversationType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "policy_type", nullable = false)
    private ConversationPolicyType policyType;

    @OneToMany(mappedBy = "conversation")
    private Set<Message> messages = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("conversations")
    private User creator;

    @ManyToMany
    @JoinTable(name = "conversation_participants",
               joinColumns = @JoinColumn(name = "conversation_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "participants_id", referencedColumnName = "id"))
    private Set<User> participants = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Conversation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConversationType getType() {
        return type;
    }

    public Conversation type(ConversationType type) {
        this.type = type;
        return this;
    }

    public void setType(ConversationType type) {
        this.type = type;
    }

    public ConversationPolicyType getPolicyType() {
        return policyType;
    }

    public Conversation policyType(ConversationPolicyType policyType) {
        this.policyType = policyType;
        return this;
    }

    public void setPolicyType(ConversationPolicyType policyType) {
        this.policyType = policyType;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public Conversation messages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }

    public Conversation addMessage(Message message) {
        this.messages.add(message);
        message.setConversation(this);
        return this;
    }

    public Conversation removeMessage(Message message) {
        this.messages.remove(message);
        message.setConversation(null);
        return this;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public User getCreator() {
        return creator;
    }

    public Conversation creator(User user) {
        this.creator = user;
        return this;
    }

    public void setCreator(User user) {
        this.creator = user;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public Conversation participants(Set<User> users) {
        this.participants = users;
        return this;
    }

    public Conversation addParticipants(User user) {
        this.participants.add(user);
        return this;
    }

    public Conversation removeParticipants(User user) {
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
        if (!(o instanceof Conversation)) {
            return false;
        }
        return id != null && id.equals(((Conversation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Conversation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", policyType='" + getPolicyType() + "'" +
            "}";
    }
}
