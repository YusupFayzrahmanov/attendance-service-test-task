package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getOneByUsername(String username);

    User getOneByExternalId(Long externalId);

    User create(User user);

    User getByExternalIdOrCreateIfNotExists(Long externalId);
}
