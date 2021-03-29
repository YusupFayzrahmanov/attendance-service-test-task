package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public interface VisitEventStatisticsService {
    Map<WebPage, Integer> getUserUniqueVisitEventCountForEveryPage(User user);

    Long getCountOfVisitEventsForPeriod(Date start, Date end);

    Long getCountOfUniqueUsersOfVisitForPeriod(Date start, Date end);

    Long getCountOfUsersWhoVisitUniqueWebPageForPeriodMoreThan(int count, Date start, Date end);
}
