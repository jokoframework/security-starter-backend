package io.github.jokoframework.securitystarterbackend.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_rol", schema = "profile")
public class UserRolEntity {
    @EmbeddedId
    private UserRolIdPK id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "rol_id", insertable = false, updatable = false)
    private RolEntity rol;

    public UserRolIdPK getId() {
        return id;
    }

    public void setId(UserRolIdPK id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UserRolEntity{" +
                "id=" + id +
                ", user=" + user +
                ", rol=" + rol +
                '}';
    }
}
