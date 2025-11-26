package hiresenseapp.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import hiresenseapp.dao.*;
import hiresenseapp.pojo.*;

public class AdminPanelServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // FIX: Admin login protection
        if (session == null || !"admin".equals(session.getAttribute("userRole"))) {
            response.sendRedirect("login.jsp?msg=Admin login required");
            return;
        }



        try {

            // Load Dashboard Counts
            int totalUsers = UserDao.countUsers();
            int totalJobs = JobDao.countJobs();
            int totalApplications = ApplicationDao.countApplications();

            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalJobs", totalJobs);
            request.setAttribute("totalApplications", totalApplications);

            // Load job listings
            List<JobPojo> jobs = JobDao.getAllJobsWithEmployerAndApplicantCount();
            request.setAttribute("jobs", jobs);

            // Load users
//            List<UserPojo> users = UserDao.getAllUsers();
//            request.setAttribute("users", users);
            
            String search = request.getParameter("search");
            String role = request.getParameter("role");
            String status = request.getParameter("status");

            List<UserPojo> users;
            if (search != null || role != null || status != null) {
                users = UserDao.getFilteredUsers(search, role, status);
            } else {
                users = UserDao.getAllUsers();
            }

            request.setAttribute("users", users);


            // FIX: Correct path
            RequestDispatcher rd = request.getRequestDispatcher("adminPanel.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            
            // FIX: Forward instead of redirect
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
