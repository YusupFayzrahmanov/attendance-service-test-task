package attendanceservice.attendanceservicetesttask.dao.repository;

import attendanceservice.attendanceservicetesttask.domain.WebPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebPageRepository extends JpaRepository<WebPage, Long> {

    @Query("select distinct wp from WebPage wp where wp.externalId=:externalId")
    Optional<WebPage> getFirstByExternalIdEquals(@Param("externalId") Long externalId);
}
