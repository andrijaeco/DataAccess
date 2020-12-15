package silab.nst.dan9.dataAccess.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import silab.nst.dan9.dataAccess.domain.User;
import silab.nst.dan9.dataAccess.repository.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


//@Service // <- this also can be used
//@org.springframework.stereotype.Repository // <- this also can be used
@Component
@Transactional(propagation = Propagation.MANDATORY)
public class UserRepository implements Repository<User, Long> {

    @Autowired // jdbcTemplate must be @Component, must be @Bean which we create in Config.class
    private JdbcTemplate jdbcTemplate;

    @Override
    public User add(User user) throws Exception {
        jdbcTemplate.update("insert into user(firstName, lastName, username, password) values(?, ?, ?, ?)", user.getFirstName(),
                user.getLastName(), user.getUsername(), user.getPassword());
        // TODO: 14-Dec-20 return object which was inserted
        return user;
    }

    @Override
    public User update(User user) throws Exception {
        return null;
    }

    @Override
    public User delete(User user) throws Exception {
        String query = "delete from user where id = " + user.getId();
        jdbcTemplate.update("delete from user where id = ?", user.getId());
        return user;
    }

    @Override
    public List<User> getAll() throws Exception {
        String query = "select * from user";
        return jdbcTemplate.query(query, new UserMapper());
    }

    @Override
    public User findById(Long id) throws Exception {
        String query = "select * from user where id = " + id;
        List<User> result = jdbcTemplate.query(query, new UserMapper());
        return result.size() == 1 ? result.get(0) : null;
    }


    class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
//            System.out.println("rowNum: " + rowNum);

            return new User(
                    resultSet.getLong("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("username"),
                    resultSet.getString("password"));
        }
    }
}
