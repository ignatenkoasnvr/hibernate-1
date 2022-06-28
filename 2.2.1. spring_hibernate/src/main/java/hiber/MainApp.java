package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User User1 = new User("User1", "Lastname1", "user1@mail.ru");
      User User2 = new User("User2", "Lastname2", "user2@mail.ru");
      User User3 = new User("User3", "Lastname3", "user3@mail.ru");
      User User4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car Audi = new Car("Audi", 1);
      Car BMW = new Car("BMW", 2);
      Car Oka = new Car("Oka", 3);
      Car Lada = new Car("Lada", 4);

      userService.add(User1.setCar(Audi).setUser(User1));
      userService.add(User2.setCar(BMW).setUser(User2));
      userService.add(User3.setCar(Oka).setUser(User3));
      userService.add(User4.setCar(Lada).setUser(User4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getCar("BMW", 2));

      context.close();
   }
}
