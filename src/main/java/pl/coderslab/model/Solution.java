package pl.coderslab.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private Exercise exercise;
    private User user;

    public Solution(Timestamp created, Timestamp updated, String description, Exercise exercise, User user) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise = exercise;
        this.user = user;
    }

    public Solution() {
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getupdated() {
        return updated;
    }

    public String getDescription() {
        return description;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public User getuser() {
        return user;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setupdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO solutions(created, updated, description, exercise_id, user_id) VALUES (?, ?, ?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setTimestamp(1, this.created);
            preparedStatement.setTimestamp(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exercise.getId());
            preparedStatement.setLong(5, this.user.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "update solutions SET created=?, updated=?, description=? exercise_id=?, user_id=? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setTimestamp(1, this.created);
            preparedStatement.setTimestamp(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exercise.getId());
            preparedStatement.setLong(5, this.user.getId());
            preparedStatement.setInt(6, this.id);
            preparedStatement.executeUpdate();
        }
    }

    static public Solution loadSolutionById(Connection conn, int solutionId) throws SQLException {
        String sql = "SELECT * FROM solutions where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, solutionId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return getSolutionFromResultSet(conn, resultSet);
        }
        return null;
    }

    static public List<Solution> loadAllsolutions(Connection conn) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solutions";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            solutions.add(getSolutionFromResultSet(conn, resultSet));
        }
        return solutions;
    }

    static public List<Solution> loadAllsolutions(Connection conn, int numberSolutions) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        String sql = "SELECT * FROM solutions ORDER BY created DESC LIMIT ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, numberSolutions);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            solutions.add(getSolutionFromResultSet(conn, resultSet));
        }
        return solutions;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM solutions WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

    static public List<Solution> loadAllByUserId(Connection conn, int userId) throws SQLException {
        List<Solution> solutions = new ArrayList<Solution>();
        String sql = "SELECT * FROM solutions WHERE user_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            solutions.add(getSolutionFromResultSet(conn, resultSet));
        }
        return solutions;
    }

    static public List<Solution> loadAllByExerciseId(Connection conn, int exerciseId) throws SQLException {
        List<Solution> solutions = new ArrayList<Solution>();
        String sql = "SELECT * FROM solutions WHERE exercise_id=? order by created";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, exerciseId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            solutions.add(getSolutionFromResultSet(conn, resultSet));
        }
        return solutions;
    }

    private static Solution getSolutionFromResultSet(Connection conn, ResultSet resultSet) throws SQLException {
        Solution loadedSolution = new Solution();
        loadedSolution.id = resultSet.getInt("id");
        loadedSolution.created = resultSet.getTimestamp("created");
        loadedSolution.updated = resultSet.getTimestamp("updated");
        loadedSolution.description = resultSet.getString("description");
        loadedSolution.exercise = Exercise.loadExerciseById(conn, resultSet.getInt("exercise_id"));
        loadedSolution.user = User.loadUserById(conn, resultSet.getInt("user_id"));
        return loadedSolution;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", exercise=" + exercise.toString() +
                ", user=" + user.toString() +
                '}';
    }
}