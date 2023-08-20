package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.addUser(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.addUser(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.addUser(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.addUser(new User("User4", "Lastname4", "user4@mail.ru"));
        userService.addWithCar(new User("User5", "Lastname5", "user5@mail.ru"), new Car("Mercedes‑Benz", 190));
        userService.addWithCar(new User("User6", "Lastname6", "user6@mail.ru"), new Car("BMW", 750));
        User user1 = new User("User7", "Lastname7", "user7@mail.ru");
        Car car1 = new Car("AUDI", 8);
        userService.addWithCar(user1, car1);
        Car car2 = new Car("Lada", 2101);
        User user2 = new User("User8", "Lastname8", "user8@mail.ru");
        Car car3 = new Car("AUDI", 9);
        userService.addWithCar(user2, car2);
        userService.addWithCar(user1, car2);
        userService.addWithCar(user2, car3);
        userService.addUser(new User("User9", "Lastname9", "user9@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                Car hisCar = user.getCar();
                System.out.print("Has a car: ");
                System.out.print("Model = " + hisCar.getModel());
                System.out.println(", Series = " + hisCar.getSeries());
            }
            System.out.println();
        }
        List<Car> cars = userService.listCars();
        cars.forEach(System.out::println);

        System.out.println("++++++++++++++++++++++");
        userService.exchangeCarsUsers(50L,40L);

        List<User> owners = userService.getUsersByCarModelAndSeries("AUDI", 8);
        owners.forEach(System.out::println);

        context.close();
    }
}
