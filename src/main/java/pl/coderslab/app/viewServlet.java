package pl.coderslab.app;

import pl.coderslab.model.Exercise;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/viewServlet")
public class viewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numberSolutions = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        System.out.println(numberSolutions);
        try (Connection conn = DbUtil.getConn()) {
            List<Solution> solutions = Solution.loadAllSolutions(conn, numberSolutions);
            System.out.println(solutions);
            request.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/tableSolution.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

    }
}
