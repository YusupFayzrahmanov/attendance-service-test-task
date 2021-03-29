package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public interface VisitEventService {
    VisitEvent createVisitEvent(User user, WebPage webPage, Date eventDate);

    CompletableFuture<VisitEvent> createVisitEventAsync(User user, WebPage webPage, Date eventDate);
}
