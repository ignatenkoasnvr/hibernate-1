package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        Car cfr1 = new Car("Mersedes", 1);
        user1.setCar(cfr1);
        userService.add(user1);

        User user11 = new User("User2", "Lastname2", "user22@mail.ru");
        Car cfr11 = new Car("BMW", 3);
        user11.setCar(cfr11);
        userService.add(user11);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        User user111 = userService.getUserByCar("BMW", 3);
        System.out.println("Владелец машины по модели и серии - " + user111);

        context.close();
    }
}
