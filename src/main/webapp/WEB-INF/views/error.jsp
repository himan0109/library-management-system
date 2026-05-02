<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background: #f0f4f8; display: flex; align-items: center; justify-content: center; min-height: 100vh; }
        .error-box { background: #fff; border-radius: 12px; padding: 3rem; max-width: 500px; text-align: center; box-shadow: 0 4px 20px rgba(0,0,0,.1); }
        .error-icon { font-size: 3.5rem; }
        h1 { color: #c53030; margin: 1rem 0 .5rem; }
        p  { color: #4a5568; margin-bottom: 2rem; }
        a  { color: #2c5282; font-weight: 600; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<div class="error-box">
    <div class="error-icon">&#9888;</div>
    <h1>${errorTitle}</h1>
    <p>${errorMessage}</p>
    <a href="/">&#8592; Back to Home</a>
</div>
</body>
</html>
