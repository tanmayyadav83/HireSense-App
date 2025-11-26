<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error | HireSense</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
  background: linear-gradient(135deg, #a18cd1, #fbc2eb);
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: 'Poppins', sans-serif;
}
.card {
  background: #fff;
  padding: 40px;
  border-radius: 16px;
  box-shadow: 0 5px 20px rgba(0,0,0,0.15);
  text-align: center;
}
h1 {
  font-size: 60px;
  color: #dc3545;
}
p {
  color: #555;
  font-size: 18px;
}
a {
  margin-top: 20px;
}
</style>
</head>
<body>
  <div class="card">
    <h1>⚠️ Oops!</h1>
    <h3>Something went wrong</h3>
    <p>We couldn’t process your request. Please try again later.</p>
    <a href="login.jsp" class="btn btn-primary mt-3">Go Back</a>
  </div>
</body>
</html>
