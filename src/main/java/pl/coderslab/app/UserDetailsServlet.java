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

@WebServlet("/UserServlet")
public class UserDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        try (Connection conn = DbUtil.getConn()) {
            User user = User.loadUserById(conn, userId);
            request.setAttribute("user", user);
            List<Solution> solutions = Solution.loadAllByUserId(conn, userId);
            request.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/userDetailsPage.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            response.getWriter().append("Brak połączenia z bazą danych");
        }
    }
}
