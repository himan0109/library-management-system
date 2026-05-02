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
        .container { max-width: 580px; margin: 3rem auto; padding: 0 1.5rem; }
        .card { background: #fff; border-radius: 12px; padding: 2.5rem; box-shadow: 0 4px 20px rgba(0,0,0,.1); }
        .card h2 { font-size: 1.5rem; color: #2c5282; margin-bottom: 1.8rem; }
        .form-row { display: flex; gap: 1rem; }
        .form-row .form-group { flex: 1; }
        .form-group { margin-bottom: 1.3rem; }
        label { display: block; font-size: .88rem; font-weight: 600; color: #4a5568; margin-bottom: .35rem; }
        input[type="text"], input[type="number"], select {
            width: 100%; padding: .65rem .9rem; border: 1.5px solid #cbd5e0;
            border-radius: 6px; font-size: .95rem; transition: border-color .2s; background: #fff;
        }
        input:focus, select:focus { outline: none; border-color: #4299e1; box-shadow: 0 0 0 3px rgba(66,153,225,.2); }
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

        <form:form action="${formAction}" method="post" modelAttribute="book">
            <div class="form-group">
                <label for="title">Title *</label>
                <form:input path="title" id="title" placeholder="e.g. 1984"/>
                <form:errors path="title" cssClass="error"/>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="isbn">ISBN *</label>
                    <form:input path="isbn" id="isbn" placeholder="e.g. 978-0451524935"/>
                    <form:errors path="isbn" cssClass="error"/>
                </div>
                <div class="form-group">
                    <label for="publishedYear">Published Year *</label>
                    <form:input path="publishedYear" id="publishedYear" type="number" placeholder="e.g. 1949"/>
                    <form:errors path="publishedYear" cssClass="error"/>
                </div>
            </div>
            <div class="form-group">
                <label for="genre">Genre *</label>
                <form:input path="genre" id="genre" placeholder="e.g. Dystopian Fiction"/>
                <form:errors path="genre" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="author.id">Author *</label>
                <form:select path="author.id" id="author.id">
                    <form:option value="" label="-- Select an Author --"/>
                    <c:forEach var="a" items="${authors}">
                        <form:option value="${a.id}" label="${a.name} (${a.nationality})"/>
                    </c:forEach>
                </form:select>
                <form:errors path="author" cssClass="error"/>
            </div>
            <div class="actions">
                <button type="submit" class="btn btn-primary">&#10003; Save Book</button>
                <a href="/books" class="btn btn-outline">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
