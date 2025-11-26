package hiresenseapp.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import hiresenseapp.dao.UserDao;
import hiresenseapp.pojo.UserPojo;


public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserPojo admin = UserDao.getUserByEmailAndPassword(email, password);

            if (admin != null && "admin".equalsIgnoreCase(admin.getRole())) {

                // Create session
                HttpSession session = request.getSession();
                session.setAttribute("adminId", admin.getId());
                session.setAttribute("userRole", "admin");
                session.setAttribute("adminName", admin.getName());
                session.setAttribute("email", admin.getEmail());

                // Redirect to admin dashboard
                response.sendRedirect("AdminPanelServlet");
                return;
            }

            // Not admin
            response.sendRedirect("login.jsp?msg=Invalid Admin Credentials");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?msg=Admin Login Failed");
        }
    }
}
