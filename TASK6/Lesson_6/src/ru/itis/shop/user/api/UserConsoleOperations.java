package ru.itis.shop.user.api;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.domain.User;

import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            break;
            case "3": {
                System.out.println("Введите id пользователя:");
                Integer id = Integer.parseInt(scanner.nextLine());
                if(userService.getUserById(id) == null) {
                    System.out.println("Пользователь с таким id не найден");
                    break;
                }
                UserDto user = userService.getUserById(id);
                System.out.println(user.getId() + " " + user.getEmail() + " " + user.getProfileDescription());
            }
            break;
            case "4": {
                System.out.println("Введите email пользователя:");
                String email = scanner.nextLine();
                if(userService.getUserByEmail(email) == null) {
                    System.out.println("Пользователь с таким email не найден");
                    break;
                }
                System.out.println("Введите новое описание профиля:");
                String newDescription = scanner.nextLine();
                userService.updateUserProfileDescriptionByEmail(email, newDescription);
            }
            break;
            case "5": {
                System.out.println("Список всех пользователей:");
                for (UserDto user : userService.getAllUsers()) {
                    System.out.println(user.getId() + " " + user.getEmail() + " " + user.getProfileDescription());
                }
            }
            break;
            case "6": {
                System.out.println("Введите описание профиля:");
                String description = scanner.nextLine();
                if (userService.getUsersByProfileDescription(description).size() > 0) {
                    for (UserDto user : userService.getUsersByProfileDescription(description)) {
                        System.out.println(user.getId() + " " + user.getEmail() + " " + user.getProfileDescription());
                    }
                } else {
                    System.out.println("Пользователи с таким описанием профиля не найдены");
                }
            }
            break;
            case "7": {
                System.out.println("Введите email пользователя:");
                String email = scanner.nextLine();
                UserDto user = userService.getUserByEmail(email);
                if (user != null) {
                    System.out.println(user.getId() + " " + user.getProfileDescription() + " ");
                }
                else {
                    System.out.println("Пользователь с таким email не найден");
                }
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным описанием профиля");
        System.out.println("7. Показать информацию о пользователя по email");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регистрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("Email или пароль не верны");
        }
    }



}
