package attendanceservice.attendanceservicetesttask.dao;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface VisitEventDAO {
    List<VisitEvent> findAllByUser(User user);

    Long findCountForPeriod(Date startDate, Date endDate);

    Long findCountOfUniqueUsersForPeriod(Date start, Date end);

    Long findCountOfUsersWhoVisitForPeriodUniqueWebPageMoreThan(int count, Date start, Date end);

    VisitEvent save(VisitEvent visitEvent);
}
