package hiresenseapp.controllers;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import hiresenseapp.dao.JobDao;



public class RemoveJobServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
if (session == null || !"admin".equals(session.getAttribute("userRole"))) {
response.sendRedirect("login.jsp");
return;
}
int jobId = Integer.parseInt(request.getParameter("jobId"));
try {
JobDao.deleteJob(jobId);
response.sendRedirect("AdminPanelServlet?tab=jobs&msg=removed");
} catch (Exception e) {
e.printStackTrace();
response.sendRedirect("AdminPanelServlet?tab=jobs&error=1");
}
}
}