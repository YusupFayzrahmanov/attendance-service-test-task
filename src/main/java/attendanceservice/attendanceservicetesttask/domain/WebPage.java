package attendanceservice.attendanceservicetesttask.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "web_pages")
public class WebPage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "external_id", unique = true)
    private Long externalId;
    @Column(name = "name", length = 128)
    private String name;
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    protected WebPage(){}

    private WebPage(Long externalId, String name){
        this.externalId = externalId;
        this.name = name;
        this.createdAt = new Date();
        this.lastUpdate = createdAt;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getExternalId() { return externalId; }

    public void setExternalId(Long externalId) { this.externalId = externalId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getLastUpdate() { return lastUpdate; }

    public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

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

    public static class Builer {
        private Long externalId;
        private String name;

        public Builer setExternalId(Long externalId) {
            if(externalId == null || externalId.longValue() < 0L) {
                throw new IllegalStateException("External id cannot be null or less than 0");
            }
            this.externalId = externalId;
            return this;
        }

        public Builer setName(String name) {
            this.name = name;
            return this;
        }

        public WebPage build() {
            return new WebPage(externalId, name);
        }
    }
}
