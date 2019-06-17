package pl.coderslab.mainTest;

import pl.coderslab.model.Solution;
import pl.coderslab.utils.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class Main5test {
    public static void main(String[] args) throws SQLException {
        List<Solution> solutions = Solution.loadAllSolutions(DatabaseConnection.getEfficientConnection(), 5);
        for (Solution solution :
                solutions) {
            System.out.println(solution);
        }
    }
}