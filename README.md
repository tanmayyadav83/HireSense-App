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

Register with email OTP verification

Upload resume and apply to job

AI-based match score calculation

View personal applications & statuses

🏢 Employer:

Register/login with OTP

Post jobs with title, description, skills, location, package, vacancies

View applicants for each job

Download resumes

Shortlist / Reject candidates

Activate / Deactivate jobs

🛡️ Admin:

View all users & jobs

Block/Unblock users

Remove accounts

Monitor platform activities

🤖 AI Features:

Uses AffindaAI Resume Parsing API

Extracts: skills, projects, education, experience, summary

Computes AI Match Score (%)

# Tech Stack:

Frontend:

HTML

CSS

JavaScript

Bootstrap 5

Backend:

Java

JSP + Servlets

Apache Tomcat 10

JDBC

Database:

MySQL

AI Integration

AffindaAI Resume Parsing API (JSON-based extraction)
