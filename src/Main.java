
import models.User;
import service.impls.UserServiceImpl;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1.Добавить пользователя
                    2.Посмотреть всех пользователей
                    3.Редактировать пользователя
                    4.Удалить пользователя
                    5.Выйти""");
            String num = scanner.nextLine();
            switch (num) {
                case "1" -> {
                    User newUser = User.registerUser(userService.get());
                    if (newUser != null) {
                        userService.add(newUser);
                    }
                }
                case "2" -> {
                    if (userService.get().isEmpty()) {
                        System.out.println("Пользователей нет!");
                    } else {
                        System.out.println("Все пользователи: ");
                        for (User user : userService.get()) {
                            System.out.println(user);
                        }
                    }
                }
                case "3" -> {
                    if (userService.get().isEmpty()) {
                        System.out.println("Пользователей нет!");
                    } else {
                        System.out.println("Все пользователи: ");
                        for (User user : userService.get()) {
                            System.out.println(user);
                        }
                        System.out.print("Выберите пользователя по email: ");
                        String email = scanner.nextLine();
                        User foundedUser = null;
                        for (User user : userService.get()) {
                            if (user.getEmail().equals(email)) {
                                foundedUser = new User();
                                foundedUser.setEmail(user.getEmail());
                                foundedUser.setAge(user.getAge());
                                foundedUser.setLastName(user.getLastName());
                                foundedUser.setFirstName(user.getFirstName());
                                foundedUser.setUsername(user.getUsername());
                                break;
                            }
                        }
                        if (foundedUser != null) {
                            String userEmail = foundedUser.getEmail();
                            boolean exitEdit = false;
                            while (!exitEdit) {
                                System.out.println("""
                                        Что вы хотите изменить?
                                        1.Имя
                                        2.Фамилия
                                        3.Возраст
                                        4.Имя пользователя
                                        5.Email
                                        6.Сохронить изменения""");
                                String num2 = scanner.nextLine();
                                switch (num2) {
                                    case "1" -> foundedUser.setFirstName(inputString("Ввeдите новое имя: "));
                                    case "2" -> foundedUser.setLastName(inputString("Ввeдите новую фамилию: "));
                                    case "3" -> {
                                        System.out.print("Введите возраст: ");
                                        while (true) {
                                            if (scanner.hasNextInt()) {
                                                foundedUser.setAge(scanner.nextInt());
                                                break;
                                            } else {
                                                System.out.println("Введите число!");
                                                scanner.nextLine();
                                            }
                                        }
                                        scanner.nextLine();
                                    }
                                    case "4" ->
                                            foundedUser.setUsername(inputString("Ввeдите новое имя пользователя: "));
                                    case "5" -> {
                                        while (true) {
                                            System.out.print("Введите email: ");
                                            String newEmail = scanner.nextLine();
                                            boolean foundUserEmail = false;
                                            for (User user : userService.get()) {
                                                if (user.getEmail().equals(newEmail)) {
                                                    foundUserEmail = true;
                                                    break;
                                                }
                                            }
                                            if (!foundUserEmail) {
                                                foundedUser.setEmail(newEmail);
                                                break;
                                            } else {
                                                System.out.println("Пользователь с таким email уже существует!");
                                            }
                                        }


                                    }
                                    case "6" -> {
                                        System.out.println(userService.update(foundedUser, userEmail));
                                        exitEdit = true;
                                    }
                                }
                            }
                        } else {
                            System.out.println("Пользователь не найден!");
                        }

                    }
                }
                case "4" -> {
                    if (userService.get().isEmpty()) {
                        System.out.println("Пользователeй нет!");
                    } else {
                        System.out.println("Все пользователи: ");
                        for (User user : userService.get()) {
                            System.out.println(user);
                        }
                        System.out.print("Выберите пользователя по email: ");
                        String email = scanner.nextLine();
                        User foundedUser = null;
                        for (User user : userService.get()) {
                            if (user.getEmail().equals(email)) {
                                foundedUser = user;
                                break;
                            }
                        }
                        if (foundedUser != null) {
                            if (userService.delete(foundedUser)) {
                                System.out.println("Успешно удалено!");
                            } else {
                                System.out.println("Ошибка при удалении!");
                            }
                        }
                    }
                }
                case "5" -> {
                    System.out.println("Вы успешно вышли!");
                    exit = true;
                }
            }
        }
    }

    public static String inputString(String prompt) {
        System.out.print(prompt);
        while (true) {
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Значение не должно быть пустым!");
            } else {
                return name;
            }
        }
    }
}