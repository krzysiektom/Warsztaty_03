package pl.coderslab.app;

import pl.coderslab.model.Exercise;
import pl.coderslab.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ExercisesServlet")
public class ExercisesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String exerciseId = request.getParameter("exerciseId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        try (Connection conn = DbUtil.getConn()) {
            if (NotNullAndNotEmpty(title) && NotNullAndNotEmpty(description)) {
                if (exerciseId == null) {
                    new Exercise(title, description).saveToDB(conn);
                } else {
                    Exercise exercise = Exercise.loadExerciseById(conn, Integer.parseInt(exerciseId));
                    exercise.setTitle(title);
                    exercise.setDescription(description);
                    exercise.saveToDB(conn);
                }
            }
            doGet(request, response);
        } catch (SQLException e) {
            response.getWriter().append("Brak połączenia z bazą danych");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String exerciseId = request.getParameter("exerciseId");
        String title = request.getParameter("title");
        try (Connection conn = DbUtil.getConn()) {
            if (exerciseId != null && title == null) {
                Exercise.loadExerciseById(conn, Integer.parseInt(exerciseId)).delete(conn);
            }
            List<Exercise> exercises = Exercise.loadAllExercises(conn);
            request.setAttribute("exercises", exercises);
            getServletContext().getRequestDispatcher("/exercisesPage.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            response.getWriter().append("Brak połączenia z bazą danych");
        }
    }

    private static boolean NotNullAndNotEmpty(String string) {
        return string != null && !string.equals("");
    }
}
