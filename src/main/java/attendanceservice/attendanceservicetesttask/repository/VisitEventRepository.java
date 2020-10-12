package attendanceservice.attendanceservicetesttask.repository;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VisitEventRepository extends JpaRepository<VisitEvent, Long> {
    @Query("select ve from VisitEvent ve where ve.user=:user")
    List<VisitEvent> findAllByUserEquals(@Param("user") User user);
    @Query("select ve from VisitEvent ve where ve.eventDate>=:startDate and ve.eventDate<:endDate")
    List<VisitEvent> findAllByEventDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
