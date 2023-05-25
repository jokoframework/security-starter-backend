package io.github.jokoframework.securitystarterbackend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.jokoframework.securitystarterbackend.constants.StatusEnum;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {

    //TODO HACE FALTA AGG ID?
    @JsonProperty
    private String username;
    @JsonProperty
    private String email;
    @JsonProperty
    private Date creationDate;
    @JsonProperty
    private Date lastAccess;
    @JsonProperty
    private StatusEnum status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
