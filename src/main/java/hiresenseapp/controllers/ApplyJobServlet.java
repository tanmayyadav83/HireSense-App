package hiresenseapp.controllers;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import hiresenseapp.dao.ApplicationDao;
import hiresenseapp.dao.JobDao;
import hiresenseapp.dao.ResumeAnalysisLogDao;
import hiresenseapp.dao.UserDao;
import hiresenseapp.pojo.ApplicationPojo;
import hiresenseapp.pojo.JobPojo;
import hiresenseapp.pojo.ResumeAnalysisLogsPojo;
import hiresenseapp.pojo.UserPojo;
import hiresenseapp.utils.MailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class ApplyJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("userId")==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		int userId=(Integer)session.getAttribute("userId");
		int jobId=Integer.parseInt(request.getParameter("jobId"));
		int score=Integer.parseInt(request.getParameter("score"));
		try {
			String resumePath="N/A";
			List<ResumeAnalysisLogsPojo>logs=ResumeAnalysisLogDao.getLogsByUser(userId);
			if(!logs.isEmpty()) {
				String resultJson=logs.get(0).getJsonResult();
				JSONObject obj=new JSONObject(resultJson);
				JSONObject data=obj.getJSONObject("data");
				resumePath=data.optString("resumePath","N/A");
			}
			ApplicationPojo app=new ApplicationPojo(0,userId,jobId,resumePath,score,"applied",null);
			ApplicationDao.apply(app);
			UserPojo u1=UserDao.getUserById(userId);
			JobPojo job=JobDao.getJobById(jobId);
			MailUtil.sendApplicationConfirmation(u1.getName(),u1.getEmail(),job.getTitle(),job.getCompany());
			UserPojo u2=UserDao.getUserById(job.getEmployerId());
			MailUtil.sendNewApplicationNotificationToEmployer(u2.getName(), u2.getEmail(), u1.getName(), job.getTitle());
			response.sendRedirect("userDashboard.jsp?success=applied");
		}catch (Exception ex) {
			ex.printStackTrace();
			response.sendRedirect("userDashboard.jsp?error=apply_failed");
		}
		
	}

}
