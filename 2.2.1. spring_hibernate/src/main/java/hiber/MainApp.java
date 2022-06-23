package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User vasya = new User("User1", "Lastname1", "user1@mail.ru");
      User petya = new User("User2", "Lastname2", "user2@mail.ru");
      User olga = new User("User3", "Lastname3", "user3@mail.ru");
      User sveta = new User("User4", "Lastname4", "user4@mail.ru");

      Car volvo = new Car("Audi", 1);
      Car bmw = new Car("BMW", 2);
      Car suzuki = new Car("Oka", 3);
      Car lada = new Car("Lada", 4);

      userService.add(vasya.setCar(volvo).setUser(vasya));
      userService.add(petya.setCar(bmw).setUser(petya));
      userService.add(olga.setCar(suzuki).setUser(olga));
      userService.add(sveta.setCar(lada).setUser(sveta));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getCar("BMW", 2));

      try {
         User notFoundUser = userService.getCar("GAZ", 4211);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }
      context.close();
   }
}
