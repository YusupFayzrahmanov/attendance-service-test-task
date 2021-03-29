package attendanceservice.attendanceservicetesttask.dao;

import attendanceservice.attendanceservicetesttask.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserDAO {
    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByExternalId(Long externalId);

    User save(User user);
}
