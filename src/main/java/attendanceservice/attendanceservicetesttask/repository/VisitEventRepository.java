package attendanceservice.attendanceservicetesttask.repository;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VisitEventRepository extends JpaRepository<VisitEvent, String> {
    List<VisitEvent> findAllByUserEquals(User user);
    List<VisitEvent> findAllByEventDateBetween(Date startDate, Date endDate);
}
