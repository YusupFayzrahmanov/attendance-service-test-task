package attendanceservice.attendanceservicetesttask.dao.impl;

import attendanceservice.attendanceservicetesttask.dao.VisitEventDAO;
import attendanceservice.attendanceservicetesttask.dao.repository.VisitEventRepository;
import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class VisitEventDAOImpl implements VisitEventDAO {
    private final VisitEventRepository visitEventRepository;

    @Autowired
    public VisitEventDAOImpl(VisitEventRepository visitEventRepository) {
        this.visitEventRepository = visitEventRepository;
    }

    @Override
    public List<VisitEvent> findAllByUser(User user) {
        return visitEventRepository.findAllByUserEquals(user);
    }

    @Override
    public Long findCountForPeriod(Date startDate, Date endDate) {
        return visitEventRepository.findCountByEventDateBetween(startDate, endDate);
    }

    @Override
    public Long findCountOfUniqueUsersForPeriod(Date start, Date end) {
        return visitEventRepository.findCountOfUniqueUsersForPeriod(start, end);
    }

    @Override
    public Long findCountOfUsersWhoVisitForPeriodUniqueWebPageMoreThan(int count, Date start, Date end) {
        return visitEventRepository.findCountOfUsersWhoVisitForPeriodUniqueWebPageMoreThan(count, start, end);
    }

    @Override
    public VisitEvent save(VisitEvent visitEvent) {
        return visitEventRepository.save(visitEvent);
    }
}
