<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | LibraryMS</title>
    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f4f8; color: #1a202c; }
        .navbar {
            background: linear-gradient(135deg, #1a365d 0%, #2c5282 100%);
            padding: 0 2rem; display: flex; align-items: center; justify-content: space-between;
            height: 64px; box-shadow: 0 2px 8px rgba(0,0,0,.25);
        }
        .navbar-brand { color: #fff; font-size: 1.4rem; font-weight: 700; text-decoration: none; }
        .navbar-links a { color: #bee3f8; text-decoration: none; margin-left: 1.5rem; font-size: .95rem; }
        .navbar-links a:hover { color: #fff; }
        .container { max-width: 560px; margin: 3rem auto; padding: 0 1.5rem; }
        .card { background: #fff; border-radius: 12px; padding: 2.5rem; box-shadow: 0 4px 20px rgba(0,0,0,.1); }
        .card h2 { font-size: 1.5rem; color: #2c5282; margin-bottom: 1.8rem; }
        .form-group { margin-bottom: 1.3rem; }
        label { display: block; font-size: .88rem; font-weight: 600; color: #4a5568; margin-bottom: .35rem; }
        input[type="text"], input[type="email"], input[type="number"] {
            width: 100%; padding: .65rem .9rem; border: 1.5px solid #cbd5e0;
            border-radius: 6px; font-size: .95rem; transition: border-color .2s;
        }
        input:focus { outline: none; border-color: #4299e1; box-shadow: 0 0 0 3px rgba(66,153,225,.2); }
        .error { color: #c53030; font-size: .82rem; margin-top: .3rem; }
        .alert-error { background: #fed7d7; color: #c53030; padding: .9rem 1.1rem; border-radius: 8px; margin-bottom: 1.2rem; border-left: 4px solid #c53030; font-weight: 500; }
        .actions { display: flex; gap: 1rem; margin-top: 1.8rem; }
        .btn { padding: .65rem 1.6rem; border-radius: 6px; font-weight: 600; font-size: .95rem; text-decoration: none; border: none; cursor: pointer; transition: transform .15s; }
        .btn-primary { background: #2c5282; color: #fff; }
        .btn-primary:hover { background: #2a4a7f; transform: translateY(-1px); }
        .btn-outline { background: transparent; color: #4a5568; border: 1.5px solid #cbd5e0; }
        .btn-outline:hover { background: #edf2f7; }
    </style>
</head>
<body>
<nav class="navbar">
    <a class="navbar-brand" href="/">&#128218; LibraryMS</a>
    <div class="navbar-links">
        <a href="/authors">Authors</a>
        <a href="/books">Books</a>
        <a href="/books/joined">Join View</a>
        <a href="/h2-console" target="_blank">DB Console</a>
    </div>
</nav>

<div class="container">
    <div class="card">
        <h2>${pageTitle}</h2>

        <c:if test="${not empty errorMessage}">
            <div class="alert-error">&#9888; ${errorMessage}</div>
        </c:if>

        <form:form action="${formAction}" method="post" modelAttribute="author">
            <div class="form-group">
                <label for="name">Full Name *</label>
                <form:input path="name" id="name" placeholder="e.g. George Orwell"/>
                <form:errors path="name" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="email">Email Address *</label>
                <form:input path="email" id="email" type="email" placeholder="e.g. author@mail.com"/>
                <form:errors path="email" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="nationality">Nationality *</label>
                <form:input path="nationality" id="nationality" placeholder="e.g. British"/>
                <form:errors path="nationality" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="birthYear">Birth Year *</label>
                <form:input path="birthYear" id="birthYear" type="number" placeholder="e.g. 1903"/>
                <form:errors path="birthYear" cssClass="error"/>
            </div>
            <div class="actions">
                <button type="submit" class="btn btn-primary">&#10003; Save Author</button>
                <a href="/authors" class="btn btn-outline">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
