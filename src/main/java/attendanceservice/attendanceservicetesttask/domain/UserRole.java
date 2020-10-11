package attendanceservice.attendanceservicetesttask.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_roles")
public class UserRole implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    private UserRole() {}

    public UserRole(String name){
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name is null");
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.createdAt = new Date();
        this.lastUpdate = createdAt;
    }

    public String getId() { return id; }

    //For Hibernate only
    private void setId(String id) { this.id = id; }

    public String getName() { return name; }

    //For Hibernate only
    private void setName(String name) { this.name = name; }

    public Date getLastUpdate() { return lastUpdate; }

    public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

    public Date getCreatedAt() { return createdAt; }

    //For Hibernate only
    private void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id.equals(userRole.id) &&
                name.equals(userRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
