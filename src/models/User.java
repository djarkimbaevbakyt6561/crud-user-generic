package models;

import java.util.List;
import java.util.Scanner;

public class User {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private int age;

    public User() {
    }

    public User(String username, String email, String firstName, String lastName, int age) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    public static User registerUser(List<User> users) {
        User newUser = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        newUser.setFirstName(scanner.nextLine());
        System.out.print("Введите фамилию: ");
        newUser.setLastName(scanner.nextLine());
        System.out.print("Введите имя пользователя: ");
        newUser.setUsername(scanner.nextLine());
        System.out.print("Введите возраст: ");
        while (true) {
            if (scanner.hasNextInt()) {
                newUser.setAge(scanner.nextInt());
                break;
            } else {
                System.out.println("Введите число!");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.print("Введите email: ");
        newUser.setEmail(scanner.nextLine());
        boolean foundUserEmail = false;
        for (User user : users) {
            if (user.getEmail().equals(newUser.getEmail())) {
                foundUserEmail = true;
                break;
            }
        }
        if (newUser.getFirstName().isEmpty() || newUser.getLastName().isEmpty() || newUser.getEmail().isEmpty()) {
            System.out.println("Поля не должны быть пустыми!❌");
        } else if (!newUser.getEmail().contains("@gmail.com")) {
            System.out.println("Не корректый адрес эл.почты!❌");
        } else if (foundUserEmail) {
            System.out.println("Пользователь с таким email уже существует!❌");
        } else {
            System.out.println("Пользователь успешно добавлен!✅");
            return newUser;
        }
        return null;
    }
}
