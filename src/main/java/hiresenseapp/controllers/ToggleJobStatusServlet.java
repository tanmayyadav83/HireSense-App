package hiresenseapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import hiresenseapp.dao.JobDao;

public class ToggleJobStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null 
                || !"employer".equals((String) session.getAttribute("userRole"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // FIX: "job" → "jobId"
            int jobId = Integer.parseInt(request.getParameter("jobId"));

            JobDao.toggleJobStatus(jobId);
            response.sendRedirect("EmployerDashboardServlet");

        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("EmployerDashboardServlet?error=statusToggleFailed");
        }
    }
}
