package hiresenseapp.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import hiresenseapp.dao.ApplicationDao;
import hiresenseapp.dao.JobDao;
import hiresenseapp.dao.ResumeAnalysisLogDao;
import hiresenseapp.pojo.ApplicationPojo;
import hiresenseapp.pojo.JobPojo;
import hiresenseapp.pojo.ResumeAnalysisLogsPojo;
import hiresenseapp.utils.AffindaAPI;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class UserDashboardServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
       
    
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  HttpSession session=request.getSession(false);
  if(session==null||session.getAttribute("userId")==null) {
   response.sendRedirect("login.jsp");
   return;
  }
  int userId=(Integer)session.getAttribute("userId");
  String search=request.getParameter("search");
  String sort=request.getParameter("sort");
  String location=request.getParameter("location");
  String experience=request.getParameter("experience");
  String packageLpa=request.getParameter("packageLpa");
  try {
   List<ResumeAnalysisLogsPojo>logs=ResumeAnalysisLogDao.getLogsByUser(userId);
   
   boolean resumeUploaded = !logs.isEmpty();
   session.setAttribute("resumeUploaded", resumeUploaded);
   request.setAttribute("resumeUploaded", resumeUploaded);

   
   
   List<String>userSkills=null;
   if(resumeUploaded) {
    JSONObject obj=new JSONObject(logs.get(0).getJsonResult());
    userSkills=AffindaAPI.extractSkills(obj.toString());    
   }
   List<JobPojo>jobs=JobDao.getAllJobsForUserDashboard(search, sort, location, experience, packageLpa);
   if(resumeUploaded && userSkills!=null) {
    for(JobPojo job:jobs) {
     int score=AffindaAPI.calculateMatchScore(job.getSkills(),userSkills);
     job.setScore(score);
    }
   }
   List<ApplicationPojo>appliedList=ApplicationDao.getApplicationByUser(userId);
   Set<Integer>appliedJobIds=new HashSet<>();
   for(ApplicationPojo app:appliedList) {
     appliedJobIds.add(app.getJobId()); 
   }
   request.setAttribute("jobs",jobs);
   request.setAttribute("appliedJobIds",appliedJobIds);
   request.setAttribute("search",search);
   request.setAttribute("sort",sort);
   request.setAttribute("location",location);
   request.setAttribute("experience",experience);
   request.setAttribute("packageLpa",packageLpa);
   request.setAttribute("resumeUploaded",resumeUploaded);
   RequestDispatcher rd=request.getRequestDispatcher("userDashboard.jsp");
   rd.forward(request, response);   
  }catch(Exception ex) {
   ex.printStackTrace();
   response.sendRedirect("error.jsp");
  }
   
   
  }
  
 

 
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
 }

}