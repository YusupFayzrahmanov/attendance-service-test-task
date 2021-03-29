package attendanceservice.attendanceservicetesttask.service.impl;

import attendanceservice.attendanceservicetesttask.dao.UserDAO;
import attendanceservice.attendanceservicetesttask.domain.User;
import attendanceservice.attendanceservicetesttask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User getOneByUsername(String username) {
        if(StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("Username is null or empty");
        }
        final User foundUser = userDAO.getUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return foundUser;

    }

    @Override
    public User getOneByExternalId(Long externalId) {
        final User foundUser = userDAO.getUserByExternalId(externalId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with external id = " + externalId));
        return foundUser;
    }

    @Override
    public User create(User user) {
        User createdUser = userDAO.save(user);
        return createdUser;
    }

    @Override
    public User getByExternalIdOrCreateIfNotExists(Long externalId) {
        User user;
        try {
            user = getOneByExternalId(externalId);
        }
        catch (EntityNotFoundException e) {
            logger.error(e.getMessage(), e);
            User newUser = new User.Builder()
                    .buildDefaultWithExternalIdAndUsername(externalId, externalId.toString());
            user = create(newUser);
        }
        return user;
    }
}
