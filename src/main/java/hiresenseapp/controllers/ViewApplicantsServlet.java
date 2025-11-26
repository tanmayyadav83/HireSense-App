package hiresenseapp.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import hiresenseapp.dao.ApplicationDao;
import hiresenseapp.dao.JobDao;
import hiresenseapp.pojo.ApplicationPojo;
import hiresenseapp.pojo.JobPojo;

public class ViewApplicantsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null
                || !"employer".equals((String) session.getAttribute("userRole"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // FIX: handle null jobId
            String jobParam = request.getParameter("jobId");
            if (jobParam == null) {
                response.sendRedirect("EmployerDashboardServlet?error=noJobId");
                return;
            }

            int jobId = Integer.parseInt(jobParam);

            String status = request.getParameter("status") != null
                    ? request.getParameter("status")
                    : "applied";

            JobPojo job = JobDao.getJobById(jobId);

            if (job == null) {
                response.sendRedirect("EmployerDashboardServlet?error=InvalidJob");
                return;
            }

            List<ApplicationPojo> list = ApplicationDao.getApplicationsByJobAndStatus(jobId, status);

            request.setAttribute("job", job);
            request.setAttribute("applicants", list);
            request.setAttribute("status", status);

            RequestDispatcher rd = request.getRequestDispatcher("viewApplicants.jsp");
            rd.forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("EmployerDashboardServlet?error=applicantFetchFailed");
        }
    }
}
