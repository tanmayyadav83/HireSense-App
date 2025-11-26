package hiresenseapp.controllers;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import hiresenseapp.dao.ApplicationDao;
import hiresenseapp.pojo.ApplicationPojo;


public class ResumeDetailServlet extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
if (session == null || !"admin".equals(session.getAttribute("userRole"))) {
response.sendRedirect("login.jsp");
return;
}
int appId = Integer.parseInt(request.getParameter("appId"));
try {
ApplicationPojo app = ApplicationDao.getApplicationById(appId);
request.setAttribute("application", app);
RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/resumeDetail.jsp");
rd.forward(request, response);
} catch (Exception e) {
e.printStackTrace();
response.sendRedirect("AdminPanelServlet?tab=applications&error=1");
}
}
}