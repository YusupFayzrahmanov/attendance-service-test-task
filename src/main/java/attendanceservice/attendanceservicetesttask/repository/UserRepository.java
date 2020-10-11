package attendanceservice.attendanceservicetesttask.repository;

import attendanceservice.attendanceservicetesttask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
