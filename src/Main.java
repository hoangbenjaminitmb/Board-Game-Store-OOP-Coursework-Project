package app;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<User> userList = loadUsers("UserAccounts.txt");
        if (userList.isEmpty()) {
            System.err.println("No users have loaded. \nClosing Program");
            return;
        }

        try (Scanner consoleInput = new Scanner(System.in)) {
            System.out.println("WELCOME");
            while (true) { 
                printWelcomeMenu(userList);
                try {
                    int selection = Integer.parseInt(consoleInput.nextLine().trim());

                    if (selection == 0) break;
                    if (selection > 0  && selection <= userList.size()) {
                        loadRoles(userList.get(selection - 1), consoleInput);
                    } else {
                        System.out.println("Invalid. User does not exist.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid. User does not exist.");
                }
            }
        }
        System.err.println("Goodbye \nClosing Program");
    }
    
    /**
     * Loads and returns a dynamic list of users from the UserAccounts.txt file
     * This is a dynamic list should the possibility that new users could be added/deleted.
     * 
     * @param fileName the name of the file to load users from
     * @throws IOException if the file cannot be read or found.
     * @return a list of users loaded from the file, or an empty list if the file doesn't exist.
     */
    private static List<User> loadUsers(String fileName) {
        List<User> users = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(";");

                if (details.length >= 6) {
                    int id = Integer.parseInt(details[0].trim());
                    String name = details[1].trim();

                    Address address = new Address (
                        Integer.parseInt(details[2].trim()), // house number
                        details[3].trim(), // postcode
                        details[4].trim() // city
                    );

                    String role = details[5].trim().toLowerCase();

                    users.add(new User(id, name, address,  role));
                }
            }
        } catch (Exception e) {
            return List.of();
        } 
        return users;
    }

    /**
     * Determines user's role and runs the corresponding command-line interface (CLI) based on their role. 
     * 
     * @param user the User object representing the user whose role is to be determined
     * @param consoleInput the Scanner object used for user input in the CLI  
     */
    private static void loadRoles(User user, Scanner consoleInput) {
        switch (user.getRole()) {
            case "admin" -> new AdminCLI(consoleInput).run();
            case "customer" -> new CustomerCLI(consoleInput, user).run();
            default -> System.out.println("Role does not exist");
        }
    }

    private static void printWelcomeMenu(List<User> userList) {
        System.out.println("PLEASE SELECT USER BY INPUTTING THE CORRESPONDING NUMBER (or 0 for exit)");
        // Formatting for list of users
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            System.out.printf(
                "%d) | %s | %s | %s%n", 
                (i+1), user.getId(), user.getName(), user.getRole()
            );
        }
        System.out.println("0) Exit");
    }
}