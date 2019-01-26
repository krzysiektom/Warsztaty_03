package pl.coderslab.app;

import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;
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

@WebServlet("/GroupUserServlet")
public class GroupUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        try (Connection conn = DbUtil.getConn()) {
            UserGroup userGroup = UserGroup.loadUserGroupById(conn,groupId);
            request.setAttribute("userGroup",userGroup);
            List<User> users =User.loadAllByGroupId (conn,groupId);
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/usersList.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            response.getWriter().append("Brak połączenia z bazą danych");
            return;
        }

    }
}
