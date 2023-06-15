package io.github.jokoframework.securitystarterbackend.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRolIdPK implements Serializable {

    private static final long serialVersionUID = -5333441678587467065L;
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "rol_id")
    private Integer rolId;

    // Constructors, getters, and setters


    public UserRolIdPK() {
    }

    public UserRolIdPK(Integer userId, Integer rolId) {
        this.userId = userId;
        this.rolId = rolId;
    }


    // Equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRolIdPK)) return false;
        UserRolIdPK that = (UserRolIdPK) o;
        return userId.equals(that.userId) &&
                rolId.equals(that.rolId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + rolId.hashCode();
        return result;
    }

    // Getters and Setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    @Override
    public String toString() {
        return "UserRolIdPK{" +
                "userId=" + userId +
                ", rolId=" + rolId +
                '}';
    }
}

