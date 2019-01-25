package pl.coderslab.app;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
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

@WebServlet("/ExerciseSolutionServlet")
public class ExerciseSolutionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int solutionId = Integer.parseInt(request.getParameter("solutionId"));
        try (Connection conn = DbUtil.getConn()) {
            Solution solution=Solution.loadSolutionById(conn,solutionId);
            request.setAttribute("solution",solution);
            getServletContext().getRequestDispatcher("/solutionPage.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            response.getWriter().append("Brak połączenia z bazą danych");
            return;
        }
    }
}
