package hiresenseapp.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;


public class DownloadResumeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileName = request.getParameter("file");

        if (fileName == null || fileName.isBlank()) {
            response.sendError(400, "Invalid file name");
            return;
        }

        // Folder where resumes are stored inside your project
        String resumeDir = getServletContext().getRealPath("/resumes/");
        File file = new File(resumeDir, fileName);

        if (!file.exists()) {
            response.sendError(404, "Resume not found");
            return;
        }

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");

        try (FileInputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {

            byte buffer[] = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
