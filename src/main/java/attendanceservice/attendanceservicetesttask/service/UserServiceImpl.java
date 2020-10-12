package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(String username, String password, String name, String patronymic, String surname) {
        User user = User.createNew(username, password, name, patronymic, surname, User.UserRole.USER);
        return userRepository.save(user);
    }

    @Override
    public User getOneById(Long id) {
        if(id == null)
            throw new IllegalArgumentException("Id is null");
        User user = userRepository.getOne(id);
        if(user == null)
            throw new EntityNotFoundException("User not found");
        return user;
    }

    @Override
    public User getOneByUsername(String username) {
        if(username == null || username.isEmpty())
            throw new IllegalArgumentException("Username is null or empty");
        return userRepository.getFirstByUsernameEquals(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

    }

    @Override
    @Transactional
    public User getOneByExternalIdOrCreateIfNotExist(Long externalId) {
        User user = userRepository.getFirstByExternalIdEquals(externalId)
                .orElseGet(() -> userRepository.save(User.createDefaultFromExternal(externalId, externalId.toString())));
        return user;
    }
}
