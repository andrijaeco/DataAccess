package silab.nst.dan9.dataAccess.service;

import silab.nst.dan9.dataAccess.domain.User;

import java.util.List;

public interface UserService {
    User add(User user) throws Exception;

    User delete(User user) throws Exception;

    User update(User user) throws Exception;

    User findUserByID(Long id) throws Exception;

    List<User> getAllUsers() throws Exception;
}
