package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import attendanceservice.attendanceservicetesttask.domain.WebPage;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface VisitEventService {
    VisitEvent createVisitEvent(User user, WebPage webPage, Date eventDate);
    CompletableFuture<VisitEvent> createVisitEvent(User user, Long externalPageId, Date eventDate) throws InterruptedException;
    Map<WebPage, Integer> getUserUniqueVisitEventCountForEveryPage(User user);
    List<VisitEvent> getVisitEventsForPeriod(Date startDate, Date endDate);
    List<User> getUniqueUsersForVisitEvents(List<VisitEvent> visitEvents);
    List<User> getUsersForVisitEventsCountForUniquePage(List<VisitEvent> visitEvents, int count);
}
