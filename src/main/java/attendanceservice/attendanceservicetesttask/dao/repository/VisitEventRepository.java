package attendanceservice.attendanceservicetesttask.dao.repository;

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

    @Query("select ve from VisitEvent ve where ve.user = :user")
    List<VisitEvent> findAllByUserEquals(@Param("user") User user);

    @Query("select count(ve) from VisitEvent ve where ve.eventDate >= :startDate and ve.eventDate < :endDate")
    Long findCountByEventDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("select count(ve.user) from VisitEvent ve where ve.eventDate >= :start and ve.eventDate < :end group by ve.user")
    Long findCountOfUniqueUsersForPeriod(@Param("start") Date start, @Param("end") Date end);

    @Query("select count(ve.user) from VisitEvent ve where ve.eventDate >= :start and ve.eventDate < :end" +
            " group by ve.user, ve.page having count(ve.page) > :count")
    Long findCountOfUsersWhoVisitForPeriodUniqueWebPageMoreThan(@Param("count") int count, @Param("start") Date start, @Param("end") Date end);
}
