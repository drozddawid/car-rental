package db2.carrental.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uzytkownicy")
public class Uzytkownicy {
    @Id
    @Column(name = "LOGIN", nullable = false, length = 30)
    private String id;

    @Column(name = "USER_GROUP_ID")
    private Integer userGroupId;

    @Column(name = "PASSWORD_HASH", length = 256)
    private String passwordHash;

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uzytkownicy(String id, Integer userGroupId, String passwordHash) {
        this.id = id;
        this.userGroupId = userGroupId;
        this.passwordHash = passwordHash;
    }

    public Uzytkownicy() {
    }
}