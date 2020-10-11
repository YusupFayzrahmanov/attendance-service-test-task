package attendanceservice.attendanceservicetesttask.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "web_pages")
public class WebPage implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    private WebPage(){}

    public WebPage(String id, String name){
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Id is null or empty");
        this.id = id;
        this.name = name;
        this.createdAt = new Date();
        this.lastUpdate = createdAt;
    }

    public String getId() { return id; }

    //For Hibernate only
    private void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getLastUpdate() { return lastUpdate; }

    public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

    public Date getCreatedAt() { return createdAt; }

    //For Hibernate only
    private void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebPage webPage = (WebPage) o;
        return id.equals(webPage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
