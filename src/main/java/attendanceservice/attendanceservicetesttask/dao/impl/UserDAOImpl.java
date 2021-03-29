package attendanceservice.attendanceservicetesttask.dao.impl;

import attendanceservice.attendanceservicetesttask.dao.UserDAO;
import attendanceservice.attendanceservicetesttask.dao.repository.UserRepository;
import attendanceservice.attendanceservicetesttask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getFirstByUsernameEquals(username);
    }

    @Override
    public Optional<User> getUserByExternalId(Long externalId) {
        return userRepository.getFirstByExternalIdEquals(externalId);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
