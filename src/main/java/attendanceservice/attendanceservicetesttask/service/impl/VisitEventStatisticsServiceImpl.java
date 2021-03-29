package attendanceservice.attendanceservicetesttask.service.impl;

import attendanceservice.attendanceservicetesttask.dao.VisitEventDAO;
import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import attendanceservice.attendanceservicetesttask.domain.WebPage;
import attendanceservice.attendanceservicetesttask.service.VisitEventStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VisitEventStatisticsServiceImpl implements VisitEventStatisticsService {
    private final VisitEventDAO visitEventDAO;

    @Autowired
    public VisitEventStatisticsServiceImpl(VisitEventDAO visitEventDAO) {
        this.visitEventDAO = visitEventDAO;
    }

    @Override
    public Map<WebPage, Integer> getUserUniqueVisitEventCountForEveryPage(User user) {
        List<VisitEvent> userVisitEvents = visitEventDAO.findAllByUser(user);
        return userVisitEvents.stream().collect(Collectors.groupingBy(VisitEvent::getPage)).entrySet().stream()
                .collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue().size()));
    }

    @Override
    public Long getCountOfVisitEventsForPeriod(Date start, Date end) {
        return visitEventDAO.findCountForPeriod(start, end);
    }

    @Override
    public Long getCountOfUniqueUsersOfVisitForPeriod(Date start, Date end) {
        return visitEventDAO.findCountOfUniqueUsersForPeriod(start, end);
    }

    @Override
    public Long getCountOfUsersWhoVisitUniqueWebPageForPeriodMoreThan(int count, Date start, Date end) {
        return visitEventDAO.findCountOfUsersWhoVisitForPeriodUniqueWebPageMoreThan(count, start, end);
    }
}
