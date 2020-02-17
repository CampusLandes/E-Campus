package com.campuslandes.ecampus.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.campuslandes.ecampus.domain.Message} entity.
 */
public class MessageDTO implements Serializable {

    private Long id;

    @NotNull
    private String message;


    private Set<UserDTO> sawPeople = new HashSet<>();

    private Long conversationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<UserDTO> getSawPeople() {
        return sawPeople;
    }

    public void setSawPeople(Set<UserDTO> users) {
        this.sawPeople = users;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageDTO messageDTO = (MessageDTO) o;
        if (messageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), messageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", conversationId=" + getConversationId() +
            "}";
    }
}
