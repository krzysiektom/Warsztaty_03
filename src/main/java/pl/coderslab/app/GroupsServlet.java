package pl.coderslab.app;

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

@WebServlet("/GroupsServlet")
public class GroupsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupId = request.getParameter("groupId");
        String name = request.getParameter("name");
        try (Connection conn = DbUtil.getConn()) {
            if (NotNullAndNotEmpty(name)) {
                if (groupId == null) {
                    new UserGroup(name).saveToDB(conn);
                } else {
                    UserGroup userGroup = UserGroup.loadUserGroupById(conn, Integer.parseInt(groupId));
                    userGroup.setName(name);
                    userGroup.saveToDB(conn);
                }
            }
            doGet(request, response);
        } catch (SQLException e) {
            response.getWriter().append("Brak połączenia z bazą danych");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String groupId = request.getParameter("groupId");
        try (Connection conn = DbUtil.getConn()) {
            if (groupId != null && name == null) {
                UserGroup.loadUserGroupById(conn, Integer.parseInt(groupId)).delete(conn);
            }
            List<UserGroup> userGroups = UserGroup.loadAllUserGroups(conn);
            request.setAttribute("userGroups", userGroups);
            getServletContext().getRequestDispatcher("/groupsPage.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            response.getWriter().append(String.valueOf(e.getErrorCode()) + " ");
            response.getWriter().append("W usuwanej grupie są jeszcze użytkownicy");
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
