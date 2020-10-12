package attendanceservice.attendanceservicetesttask.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "visit_events")
public class VisitEvent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "page_id", nullable = false)
    private WebPage page;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "event_date", nullable = false)
    private Date eventDate;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    protected VisitEvent(){}

    public VisitEvent(WebPage page, User user, Date eventDate) {
        if(page == null)
            throw new IllegalArgumentException("Page is null");
        if(user == null)
            throw new IllegalArgumentException("User is null");
        if(eventDate == null)
            throw new IllegalArgumentException("Event date is null");
        this.page = page;
        this.user = user;
        this.eventDate = eventDate;
        this.createdAt = new Date();
    }

    public Long getId() { return id; }

    //For Hibernate only
    private void setId(Long id) { this.id = id; }

    public WebPage getPage() { return page; }

    //For Hibernate only
    private void setPage(WebPage page) { this.page = page; }

    public User getUser() { return user; }

    //For Hibernate only
    private void setUser(User user) { this.user = user; }

    public Date getEventDate() { return eventDate; }

    //For Hibernate only
    private void setEventDate(Date eventDate) { this.eventDate = eventDate; }

    public Date getCreatedAt() { return createdAt; }

    //For Hibernate only
    private void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitEvent that = (VisitEvent) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
