package silab.nst.dan9.dataAccess.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import silab.nst.dan9.dataAccess.config.Config;
import silab.nst.dan9.dataAccess.domain.Stats;
import silab.nst.dan9.dataAccess.domain.User;
import silab.nst.dan9.dataAccess.repository.Repository;
import silab.nst.dan9.dataAccess.repository.stats.operation.StatsOperation;
import silab.nst.dan9.dataAccess.service.StatsService;
import silab.nst.dan9.dataAccess.service.UserService;

import java.util.List;

@Component
public class Main {
    @Autowired
    private Repository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StatsService statsService;


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Main main = context.getBean(Main.class);

//        main.saveUserInRepository();

//        main.findUserById(18l);

//        main.deleteUserFromRepository(23l);

//        main.updateUserInRepository(20l);

//        main.updateStats("users", 1l, StatsOperation.DELETE);

        main.getAllStatsFromRepository();
        main.getAllUsersFromRepository();

    }

    private void updateStats(String key, Long value, StatsOperation operation) {
        try {
            statsService.updateStats(key, value, operation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllStatsFromRepository() {
        try {
            System.out.println("\n\nAll Stats: => ");
            List<Stats> stats = statsService.getALlStats();
            stats.forEach(stat -> System.out.println(stat));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUserInRepository(Long id) {
        try {
            User foundUser = userService.findUserByID(id);
            foundUser.setFirstName("c");
            foundUser.setLastName("c");
            foundUser.setUsername("c");
            foundUser.setPassword("c");
            userService.update(foundUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUserFromRepository(Long id) {
        try {
            User user = userService.findUserByID(id);
            if (user != null) {
                userService.delete(user);
                System.out.println("User deleted successfully.");
                updateStats("users", 1l, StatsOperation.DELETE);
            } else {
                System.out.println("User doesn't exist!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findUserById(Long id) {
        try {
            User user = userService.findUserByID(id);
            if (user != null) {
                System.out.println("User found => ");
                System.out.println(user);
            } else {
                System.out.println("User doesn't exist!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveUserInRepository() {
        User user = new User("John", "Smith", "john", "john");
        try {
            System.out.println(userService.add(user));
            System.out.println("User inserted successfully.");
            updateStats("users", 1l, StatsOperation.INSERT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllUsersFromRepository() {
        try {
            List<User> users = userService.getAllUsers();
            System.out.println("\n\nAll Users: => ");
            users.forEach(user -> System.out.println(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
