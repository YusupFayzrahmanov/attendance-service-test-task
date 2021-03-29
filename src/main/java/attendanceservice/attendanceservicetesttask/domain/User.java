package attendanceservice.attendanceservicetesttask.domain;


import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final String DEFAULT_PASSWORD = "DefaultPassword123";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "external_id", unique = true)
    private Long externalId;
    @Column(name = "username", nullable = false, unique = true, length = 64)
    private String username;
    @Column(name = "password", nullable = false, length = 128)
    private String password;
    @Column(name = "name", length = 64)
    private String name;
    @Column(name = "patronymic", length = 64)
    private String patronymic;
    @Column(name = "surname", length = 64)
    private String surname;
    @Column(name = "role_id", nullable = false, columnDefinition = "int8")
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    public User(){ }

    private User(Long externalId, String username, String password, String name,
                 String patronymic, String surname, UserRole role) {
        this.externalId = externalId;
        this.username = username;
        this.password = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.role = role;
        this.deleted = false;
        this.createdAt = new Date();
        this.lastUpdate = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExternalId() { return externalId; }

    public void setExternalId(Long externalId) { this.externalId = externalId; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() { return surname; }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserRole getRole() { return role; }

    public void setRole(UserRole role) { this.role = role; }

    public boolean isDeleted() { return deleted; }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public Date getLastUpdate() { return lastUpdate; }

    public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public boolean passwordIsEqual(String password) {
        return this.password.toUpperCase().equals(DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) ||
                username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public enum UserRole {
        ADMIN(1, "Admin"),
        USER(2, "User");

        private int id;
        private String name;

        private UserRole(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId() { return id; }

        public String getName() { return name; }
    }

    public static class Builder {
        private Long externalId;
        private String username;
        private String password;
        private String name;
        private UserRole role;

        public Builder setExternalId(Long externalId) {
            if(externalId == null || externalId.longValue() < 0L) {
                throw new IllegalStateException("External id cannot be null or less than 0");
            }
            this.externalId = externalId;
            return this;
        }

        public Builder setUsername(String username) {
            if(StringUtils.isEmpty(username)) {
                throw new IllegalStateException("Username cannot be null");
            }
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            if(StringUtils.isEmpty(password)) {
                throw new IllegalStateException("Password cannot be null");
            }
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRole(UserRole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(externalId, username, password, name, null, null, role);
        }

        public User buildDefaultWithExternalIdAndUsername(Long externalId, String username){
            setExternalId(externalId);
            setUsername(username);
            return new User(this.externalId, this.username, DEFAULT_PASSWORD, null, null, null, UserRole.USER);
        }
    }
}
