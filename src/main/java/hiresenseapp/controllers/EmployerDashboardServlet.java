package hiresenseapp.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import hiresenseapp.dao.JobDao;
import hiresenseapp.pojo.JobPojo;


public class EmployerDashboardServlet extends HttpServlet {
	
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(false);

		if (session == null) {
		    response.sendRedirect("login.jsp");
		    return;
		}

		String role = (String) session.getAttribute("userRole");

		if (role == null || !(role.equals("employer") || role.equals("admin"))) {
		    response.sendRedirect("login.jsp");
		    return;
		}

		
		
		int employerId=(Integer)session.getAttribute("userId");
		String search=request.getParameter("search");
		String sort=request.getParameter("sort");
		String status=request.getParameter("status");
		try {
			List<JobPojo> jobList=JobDao.getJobsByEmployer(employerId, search, status, sort);
				request.setAttribute("jobList",jobList);
				request.setAttribute("search",search);
				request.setAttribute("status",status);
				request.setAttribute("sort",sort);
				RequestDispatcher rd=request.getRequestDispatcher("employeeDashboard.jsp");
				rd.forward(request, response);
				
			
		}catch(Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("error.jsp");
		}
		
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		       throws ServletException, IOException {
		    doGet(req, res);
		}


	

}
