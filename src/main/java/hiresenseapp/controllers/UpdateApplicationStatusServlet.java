package hiresenseapp.controllers;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import hiresenseapp.dao.ApplicationDao;



public class UpdateApplicationStatusServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
String role = (String) session.getAttribute("userRole");
if (session == null || role == null || !(role.equals("employer") || role.equals("admin"))) {
    response.sendRedirect("login.jsp");
    return;
}


int appId = Integer.parseInt(request.getParameter("appId"));
String status = request.getParameter("status");
try {
ApplicationDao.updateApplicationStatus(appId, status);
response.sendRedirect("ViewApplicantsServlet?jobId=" + request.getParameter("jobId") + "&status=applied");

} catch (Exception e) {
e.printStackTrace();
response.sendRedirect("ViewApplicantsServlet?jobId=" 
	    + request.getParameter("jobId") + "&status=" 
	    + request.getParameter("status") + "&error=1");

}
}
}