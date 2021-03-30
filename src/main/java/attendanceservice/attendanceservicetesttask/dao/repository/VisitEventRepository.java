package attendanceservice.attendanceservicetesttask.dao.repository;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.domain.VisitEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitEventRepository extends JpaRepository<VisitEvent, Long> {

    @Query("select ve from VisitEvent ve where ve.user = :user")
    List<VisitEvent> findAllByUserEquals(@Param("user") User user);

    @Query("select count(ve) from VisitEvent ve where ve.eventDate >= :startDate and ve.eventDate < :endDate")
    Optional<Long> findCountByEventDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("select count(distinct ve.user.id) from VisitEvent ve where ve.eventDate >= :start and ve.eventDate < :end group by ve.user.id")
    List<Long> findCountOfUniqueUsersForPeriod(@Param("start") Date start, @Param("end") Date end);

    @Query("select count(distinct ve.user.id) from VisitEvent ve" +
            " where ve.eventDate >= :start and ve.eventDate < :end group by ve.user.id having count(ve.page.id) > :count")
    List<Long> findCountOfUsersWhoVisitForPeriodUniqueWebPageMoreThan(@Param("count") Long count,
                                                                @Param("start") Date start, @Param("end") Date end);
}
