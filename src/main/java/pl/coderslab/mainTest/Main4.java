package pl.coderslab.mainTest;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.utils.DatabaseConnection;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) throws SQLException {
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        String text;
        viewAllSolutions();
        viewMenu();

        while (!exit) {
            text = scan.nextLine();
            switch (text) {
                case "add": {
                    Solution solution = new Solution();
                    List<User> users = User.loadAllUsers(DatabaseConnection.getEfficientConnection());
                    for (User user : users) {
                        System.out.println(user.toString());
                    }
                    System.out.println("Wprowadz id użytkownika, dla którego chcesz dodać rozwiązanie");
                    solution.setUser(User.loadUserById(DatabaseConnection.getEfficientConnection(), Integer.valueOf(scan.nextLine())));
                    List<Exercise> exercises= Exercise.loadAllExercises(DatabaseConnection.getEfficientConnection());
                    for (Exercise exercise : exercises) {
                        System.out.println(exercise.toString());
                    }
                    System.out.println("Wprowadz id zadania, dla którego chcesz dodać rozwiązanie");
                    solution.setExercise(Exercise.loadExerciseById(DatabaseConnection.getEfficientConnection(), Integer.valueOf(scan.nextLine())));
                    solution.setCreated(Timestamp.valueOf(LocalDateTime.now()));
                    solution.saveToDB(DatabaseConnection.getEfficientConnection());
                    viewAllSolutions();
                    viewMenu();
                    break;
                }
                case "view": {
                    System.out.println("Wprowadz id użytkownika, którego chcesz zobaczyć rozwiązania");
                    List<Solution> solutions = Solution.loadAllByUserId(DatabaseConnection.getEfficientConnection(), Integer.valueOf(scan.nextLine()));
                    for (Solution solution : solutions) {
                        System.out.println(solution.toString());
                    }
                    viewAllSolutions();
                    viewMenu();
                    break;
                }
                case "quit": {
                    exit = true;
                    break;
                }
                default: {
                    System.out.println("Wybierz prawidłową opcję");
                }
            }
        }
    }

    public static void viewAllSolutions() throws SQLException {
        List<Solution> solutions = Solution.loadAllSolutions(DatabaseConnection.getEfficientConnection());
        for (Solution solution : solutions) {
            System.out.println(solution.toString());
        }
    }

    public static void viewMenu() {
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – przypisywanie zadań do użytkowników,\n" +
                "view – przeglądanie rozwiązań danego użytkownika,\n" +
                "quit – zakończenie programu.");
    }
}
