# HireSense – AI-Powered Smart Job Portal

HireSense is an intelligent job portal that connects Job Seekers and Employers, enhanced with AI-powered resume parsing and match scoring.
It simplifies recruitment by automatically analyzing resumes and generating a match percentage using ApprentAI's Resume Parsing API.

# Table of Contents

Introduction

Features

Tech Stack

How to Run

Project Screenshots

# Introduction
HireSense is a modern job portal designed to improve hiring using artificial intelligence.
It provides three roles:

Job Seeker – Uploads resume, applies to jobs, views application status.

Employer – Posts jobs, views applicants, shortlists/rejects candidates, manages job visibility.

Admin – Manages users, jobs, and platform activity.

The system uses AI resume parsing to extract skills, experience, and education from uploaded resumes, then matches it with job requirements to calculate a match score.
This score helps employers quickly identify the best candidates.

# Key Features:

👨‍💼 Job Seeker:

➤ Register with email OTP verification

➤ Upload resume and apply to job

➤ AI-based match score calculation

➤ View personal applications & statuses

🏢 Employer:

➤ Register/login with OTP

➤ Post jobs with title, description, skills, location, package, vacancies

➤ View applicants for each job

➤ Download resumes

➤ Shortlist / Reject candidates

➤ Activate / Deactivate jobs

🛡️ Admin:

➤ View all users & jobs

➤ Block/Unblock users

➤ Remove accounts

➤ Monitor platform activities

🤖 AI Features:

➤ Uses AffindaAI Resume Parsing API

➤ Extracts: skills, projects, education, experience, summary

➤ Computes AI Match Score (%)

# Tech Stack:

Frontend:

● HTML

● CSS

● JavaScript

● Bootstrap 5

Backend:

● Java

● JSP + Servlets

● Apache Tomcat 10

● JDBC

Database:

● MySQL

AI Integration:

● AffindaAI Resume Parsing API (JSON-based extraction)

# How to Run Locally
1. Clone the Repository
https://github.com/tanmayyadav83/HireSense-App.git

2. Import Project in Eclipse/IntelliJ

Select Import → Existing Projects

Choose your project folder

3. Configure Database

Create database: hiresense_db

Import SQL tables (users, jobs, applications, logs)

4. Update DB Credentials

In DBConnection.java:

openConnection("jdbc:mysql://localhost:3306/hiresense_db", "root", "password");

5. Add API Key

Put your ApprentAI API key in the resume parsing service file.

6. Run on Tomcat

Add project to Tomcat server

Start server

Open:

http://localhost:2025/hiresenseapp/

# Screenshots:

![login](https://raw.githubusercontent.com/tanmayyadav83/HireSense-App/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-19%20140416.png)
![2](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-19%20140623.png)
![3](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-19%20140453.png)
![4](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-19%20140752.png)
![5](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-19%20140818.png)
![6](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-26%20160933.png)
![7](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-19%20140914.png)
![8](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-19%20140942.png)
![9](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-26%20161036.png)
![10](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-26%20161100.png)
![11](https://github.com/tanmayyadav83/HireSense-App/blob/241bb485cdb477d83d6092392107d1f6ef920ea1/Screenshot%202025-11-26%20161408.png)

