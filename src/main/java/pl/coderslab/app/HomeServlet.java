package pl.coderslab.app;

import pl.coderslab.model.Solution;
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

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numberSolutions = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        try (Connection conn = DbUtil.getConn()) {
            List<Solution> solutions = Solution.loadAllSolutions(conn, numberSolutions);
            request.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/solutionsList.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            response.getWriter().append("Brak połączenia z bazą danych");
        }
    }
}
