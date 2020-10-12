package attendanceservice.attendanceservicetesttask.service;

import attendanceservice.attendanceservicetesttask.domain.User;

public interface UserService {
    User create(String username, String password, String name, String patronymic, String surname);
    User getOneById(Long id);
    User getOneByUsername(String username);
    User getOneByExternalIdOrCreateIfNotExist(Long id);
}
