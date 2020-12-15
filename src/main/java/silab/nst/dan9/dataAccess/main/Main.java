package silab.nst.dan9.dataAccess.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import silab.nst.dan9.dataAccess.config.Config;
import silab.nst.dan9.dataAccess.domain.User;
import silab.nst.dan9.dataAccess.repository.Repository;
import silab.nst.dan9.dataAccess.service.UserService;

import java.util.List;

@Component
public class Main {
    @Autowired
    private Repository userRepository;

    @Autowired
    private UserService userService;


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Main main = context.getBean(Main.class);

//        main.saveUserInRepository();

//        main.findUserById(18l);

//        main.deleteUserFromRepository(21l);


        System.out.println("\n\n\nAll Users: => ");
        main.getAllUsersFromRepository();
    }

    private void deleteUserFromRepository(Long id) {
        try {
            User user = userService.findUserByID(id);
            if (user != null) {
                userService.delete(user);
                System.out.println("User deleted successfully.");
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllUsersFromRepository() {
        try {
            List<User> users = userService.getAllUsers();
//            List<User> users = userRepository.getAll();
            users.forEach(user -> System.out.println(user));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
