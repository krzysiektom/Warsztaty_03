package pl.coderslab.app;

import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;
import pl.coderslab.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userGroupId = request.getParameter("userGroupId");
        try (Connection conn = DbUtil.getConn()) {
            if (userId == null) {
                User user = new User(username, email,password,UserGroup.loadUserGroupById(conn, Integer.parseInt(userGroupId)));
                user.saveToDB(conn);
            } else {
                User user = User.loadUserById(conn, Integer.parseInt(userId));
                if (NotNullAndNotEmpty(username)) {
                    user.setUsername(username);
                }
                if (NotNullAndNotEmpty(email)) {
                    user.setEmail(email);
                }
                if (NotNullAndNotEmpty(password) && !password.equals(user.getPassword())) {
                    user.setPassword(password);
                }
                if (NotNullAndNotEmpty(userGroupId)) {
                    user.setUserGroup(UserGroup.loadUserGroupById(conn, Integer.parseInt(userGroupId)));
                }
                user.saveToDB(conn);
            }
            doGet(request, response);
        } catch (SQLException e) {
            response.getWriter().append(String.valueOf(e.getErrorCode()) + " ");
            response.getWriter().append("Użytkownik o takim mailu już istnieje");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userId = request.getParameter("userId");
        try (Connection conn = DbUtil.getConn()) {
            if (userId != null && username == null) {
                User.loadUserById(conn, Integer.parseInt(userId)).delete(conn);
            }
            List<User> users = User.loadAllUsers(conn);
            request.setAttribute("users", users);
            List<UserGroup> userGroups = UserGroup.loadAllUserGroups(conn);
            HttpSession session = request.getSession();
            session.setAttribute("userGroups", userGroups);
            getServletContext().getRequestDispatcher("/usersPage.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            response.getWriter().append("Brak połączenia z bazą danych");
            return;
        }
    }

    private static boolean NotNullAndNotEmpty(String string) {
        if (string != "" && string != null) {
            return true;
        } else {
            return false;
        }
    }
}
