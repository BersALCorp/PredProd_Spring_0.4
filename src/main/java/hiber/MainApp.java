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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        Car car1 = new Car("AUDI", 8);
        Car car2 = new Car("Lada", 2101);
        User user2 = new User("User7", "Lastname7", "user7@mail.ru");
        Car car3 = new Car("Mercedes‑Benz", 190);
        Car car4 = new Car("BMW", 750);
        User user4 = new User("User5", "Lastname5", "user5@mail.ru");
        userService.addCarToUserId(car1, 1);
        userService.addUserWithCar(user2, car2);
        userService.addUserWithCar(new User("User6", "Lastname6", "user6@mail.ru"), car3);
        userService.addUserWithCar(user4, car4);


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

        System.out.println("---------------------------ДОСТАЮ ПОЛЬЗОВАТЕЛЕЙ--------------------------");
        System.out.println(userService.getUserById(2));
        System.out.println(userService.getUserById(4));
        System.out.println(userService.getUserById(6));
        System.out.println("-----------------------------ВЫВОЖУ ВСЕ МАШИНЫ---------------------------");
        userService.listCars().forEach(System.out::println);
        System.out.println("-------------ВЫВОЖУ ВСЕХ ПОЛЬЗВАТЕЛЕЙ МАШИНЫ (модель, серия)-------------");
        userService.getUsersByCarModelAndSeries("Lada", 2101).forEach(System.out::println);
        System.out.println("------------ВЫВОЖУ ПЕРВОГО ПОЛЬЗОВАТЕЛЯ МАШИНЫ (модель, серия)-----------");
        System.out.println(userService.getFirstUserByCarModelAndSeries("Lada", 2101));
        System.out.println("-----------ВЫВОЖУ ПОСЛЕДНЕГО ПОЛЬЗОВАТЕЛЯ МАШИНЫ (модель, серия)----------");
        System.out.println(userService.getLastUserByCarModelAndSeries("Lada", 2101));

        context.close();
    }
}
