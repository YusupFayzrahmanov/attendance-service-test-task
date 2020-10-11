package attendanceservice.attendanceservicetesttask.domain;


import org.springframework.lang.NonNull;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "surname")
    private String surname;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id")
    private UserRole role;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    private User(){}

    public User(String username, String password, String name,
                 String patronymic, String surname, UserRole role) {
        if(username == null || username.isEmpty())
            throw new IllegalArgumentException("Username is null or empty");
        if(password == null || password.isEmpty())
            throw new IllegalArgumentException("Password is null or empty");
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.role = role;
        this.deleted = false;
        this.createdAt = new Date();
        this.lastUpdate = createdAt;
    }

    public String getId() {
        return id;
    }

    //For Hibernate only
    private void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    //For Hibernate only
    private void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    //For Hibernate only
    private void setPassword(String password) {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isDeleted() { return deleted; }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public Date getLastUpdate() { return lastUpdate; }

    public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

    public Date getCreatedAt() { return createdAt; }

    //For Hibernate only
    private void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public boolean passwordIsEqual(String password) {
        return password.toUpperCase().equals(DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase());
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
}
