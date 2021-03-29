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

    public VisitEvent(){ }

    public VisitEvent(WebPage page, User user, Date eventDate) {
        this.page = page;
        this.user = user;
        this.eventDate = eventDate;
        this.createdAt = new Date();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public WebPage getPage() { return page; }

    public void setPage(WebPage page) { this.page = page; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Date getEventDate() { return eventDate; }

    public void setEventDate(Date eventDate) { this.eventDate = eventDate; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

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

    public static class Builder {
        private WebPage page;
        private User user;
        private Date eventDate;

        public Builder setPage(WebPage page) {
            if(page == null) {
                throw new IllegalStateException("Page cannot be null");
            }
            this.page = page;
            return this;
        }

        public Builder setUser(User user) {
            if(user == null) {
                throw new IllegalStateException("User cannot be null");
            }
            this.user = user;
            return this;
        }

        public Builder setEventDate(Date eventDate) {
            if(eventDate == null) {
                throw new IllegalStateException("Event date cannot be null");
            }
            this.eventDate = eventDate;
            return this;
        }

        public VisitEvent build() {
            return new VisitEvent(page, user, eventDate);
        }
    }
}
