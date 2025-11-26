package hiresenseapp.controllers;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import hiresenseapp.dao.UserDao;



public class UnblockUserServlet extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
HttpSession session = request.getSession(false);
if (session == null || !"admin".equals(session.getAttribute("userRole"))) {
response.sendRedirect("login.jsp");
return;
}
int userId = Integer.parseInt(request.getParameter("userId"));
try {
UserDao.updateStatus(userId, "active");
response.sendRedirect("AdminPanelServlet?tab=users&msg=unblocked");
} catch (Exception e) {
e.printStackTrace();
response.sendRedirect("AdminPanelServlet?tab=users&error=1");
}
}
}