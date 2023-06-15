package io.github.jokoframework.securitystarterbackend.entities;

import javax.persistence.*;

@Entity
@Table(name = "rol", schema = "profile")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer rolId;

    @Column(name = "description", length = 40, unique = true)
    private String description;

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RolEntity{" +
                "rolId=" + rolId +
                ", description='" + description + '\'' +
                '}';
    }
}
