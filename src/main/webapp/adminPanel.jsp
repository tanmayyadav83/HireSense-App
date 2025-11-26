<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, hiresenseapp.pojo.UserPojo, hiresenseapp.pojo.JobPojo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel | HireSense</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
</head>
<body>

<%@ include file="includes/header.jsp" %>

<main class="container py-5">
  <h2 class="mb-4">👑 Admin Dashboard</h2>

  <!-- Dashboard Summary -->
  <div class="row text-center mb-5">
    <div class="col-md-4">
      <div class="card shadow-sm p-4">
        <h5>Total Users</h5>
        <h3 class="text-primary"><%= request.getAttribute("totalUsers") %></h3>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card shadow-sm p-4">
        <h5>Total Jobs</h5>
        <h3 class="text-success"><%= request.getAttribute("totalJobs") %></h3>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card shadow-sm p-4">
        <h5>Total Applications</h5>
        <h3 class="text-warning"><%= request.getAttribute("totalApplications") %></h3>
      </div>
    </div>
  </div>

  <!-- Filter Section -->
  <div class="mb-4 card p-4">
    <h5>Filter Users</h5>
    <form action="AdminPanelServlet" method="get">
      <div class="row g-2">
        <div class="col-md-4">
          <input type="text" name="search" class="form-control" placeholder="Search by name or email"
                 value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
        </div>
        <div class="col-md-3">
          <select name="role" class="form-select">
            <option value="">All roles</option>
            <option value="employer" <%= "employer".equals(request.getParameter("role")) ? "selected" : "" %>>Employer</option>
            <option value="user" <%= "user".equals(request.getParameter("role")) ? "selected" : "" %>>User</option>
          </select>
        </div>
        <div class="col-md-3">
          <select name="status" class="form-select">
            <option value="">All status</option>
            <option value="active" <%= "active".equals(request.getParameter("status")) ? "selected" : "" %>>Active</option>
            <option value="blocked" <%= "blocked".equals(request.getParameter("status")) ? "selected" : "" %>>Blocked</option>
          </select>
        </div>
        <div class="col-md-2">
          <button type="submit" class="btn btn-primary w-100">Apply Filters</button>
        </div>
      </div>
    </form>
  </div>

  <!-- Users Table -->
  <div class="card p-4 mb-5">
    <h5>📄 Users</h5>
    <table class="table table-striped table-bordered align-middle mt-3">
      <thead>
        <tr>
          <th>Name</th>
          <th>Email</th>
          <th>Role</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <%
          List<UserPojo> users = (List<UserPojo>) request.getAttribute("users");
          if (users != null && !users.isEmpty()) {
            for (UserPojo u : users) {
        %>
        <tr>
          <td><%= u.getName() %></td>
          <td><%= u.getEmail() %></td>
          <td><%= u.getRole() %></td>
          <td>
            <span class="badge <%= "active".equals(u.getStatus()) ? "bg-success" : "bg-danger" %>">
              <%= u.getStatus() %>
            </span>
          </td>
          <td>
            <% if ("active".equals(u.getStatus())) { %>
              <form action="BlockUserServlet" method="post" style="display:inline;">
                <input type="hidden" name="userId" value="<%= u.getId() %>">
                <button type="submit" class="btn btn-danger btn-sm">Block</button>
              </form>
            <% } else { %>
              <form action="UnblockUserServlet" method="post" style="display:inline;">
                <input type="hidden" name="userId" value="<%= u.getId() %>">
                <button type="submit" class="btn btn-success btn-sm">Unblock</button>
              </form>
            <% } %>
          </td>
        </tr>
        <%
            }
          } else {
        %>
        <tr>
          <td colspan="5" class="text-center text-warning">No Users Found</td>
        </tr>
        <% } %>
      </tbody>
    </table>
  </div>

  <!-- Job Listings Table -->
  <div class="card p-4 mb-4">
    <h5>💼 Manage Job Listings</h5>
    <table class="table table-striped table-bordered align-middle mt-3">
      <thead>
        <tr>
          <th>Job Title</th>
          <th>Company</th>
          <th>Applicants</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <%
          List<JobPojo> jobs = (List<JobPojo>) request.getAttribute("jobs");
          if (jobs != null && !jobs.isEmpty()) {
            for (JobPojo j : jobs) {
        %>
        <tr>
          <td><%= j.getTitle() %></td>
          <td><%= j.getCompany() %></td>
          <td><%= j.getApplicantCount() %></td>
          <td>
            <span class="badge <%= "active".equals(j.getStatus()) ? "bg-success" : "bg-secondary" %>">
              <%= j.getStatus() %>
            </span>
          </td>
          <td>
            <form action="RemoveJobServlet" method="post" style="display:inline;">
              <input type="hidden" name="jobId" value="<%= j.getId() %>">
              <button type="submit" class="btn btn-danger btn-sm">Remove</button>
            </form>
          </td>
        </tr>
        <%
            }
          } else {
        %>
        <tr>
          <td colspan="5" class="text-center text-warning">No Jobs Found</td>
        </tr>
        <% } %>
      </tbody>
    </table>
  </div>
</main>

<%@ include file="includes/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
