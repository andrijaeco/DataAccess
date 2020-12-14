package silab.nst.dan9.dataAccess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import silab.nst.dan9.dataAccess.domain.User;
import silab.nst.dan9.dataAccess.repository.Repository;
import silab.nst.dan9.dataAccess.service.UserService;

import java.util.List;

@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private Repository userRepository;

    @Override
    public User add(User user) throws Exception {
//        add user in database in table user and update table stats
//        key = USER  and value+=1
        return (User) userRepository.add(user);
    }

    @Override
    public User update(User user) throws Exception {
//        update specific user
        return null;
    }

    @Override
    public User findUserByID(Long id) throws Exception {
        return (User) userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return userRepository.getAll();
    }

    @Override
    public User delete(User user) throws Exception {
//        delete user from table user and update table stats
        userRepository.delete(user);
        return null;
    }
}
