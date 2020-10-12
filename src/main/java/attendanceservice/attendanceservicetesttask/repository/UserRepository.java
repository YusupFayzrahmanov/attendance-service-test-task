package attendanceservice.attendanceservicetesttask.repository;

import attendanceservice.attendanceservicetesttask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select distinct u from User u where upper(u.username)=upper(:username)")
    Optional<User> getFirstByUsernameEquals(@Param("username") String username);
    @Query("select distinct u from User u where u.externalId=:externalId")
    Optional<User> getFirstByExternalIdEquals(@Param("externalId") Long externalId);
}
