<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books | LibraryMS</title>
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
        .container { max-width: 1200px; margin: 2.5rem auto; padding: 0 1.5rem; }
        .page-header { display: flex; align-items: center; justify-content: space-between; margin-bottom: 1.5rem; }
        .page-header h1 { font-size: 1.8rem; color: #2c5282; }
        .header-actions { display: flex; gap: .8rem; }
        .btn { display: inline-block; padding: .55rem 1.3rem; border-radius: 6px; text-decoration: none; font-weight: 600; font-size: .9rem; transition: transform .15s; border: none; cursor: pointer; }
        .btn-primary { background: #2c5282; color: #fff; }
        .btn-primary:hover { background: #2a4a7f; transform: translateY(-1px); }
        .btn-secondary { background: #718096; color: #fff; }
        .btn-secondary:hover { background: #4a5568; }
        .btn-warning { background: #d69e2e; color: #fff; }
        .btn-warning:hover { background: #b7791f; }
        .btn-sm { padding: .35rem .85rem; font-size: .82rem; }
        .alert { padding: 1rem 1.2rem; border-radius: 8px; margin-bottom: 1.5rem; font-weight: 500; }
        .alert-success { background: #c6f6d5; color: #22543d; border-left: 4px solid #38a169; }
        table { width: 100%; border-collapse: collapse; background: #fff; border-radius: 10px; overflow: hidden; box-shadow: 0 2px 12px rgba(0,0,0,.08); }
        thead th { background: #2c5282; color: #fff; padding: .85rem 1rem; text-align: left; font-size: .9rem; letter-spacing: .4px; }
        tbody tr { border-bottom: 1px solid #e2e8f0; transition: background .15s; }
        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #ebf8ff; }
        td { padding: .8rem 1rem; font-size: .9rem; vertical-align: middle; }
        .genre-badge { display: inline-block; padding: .2rem .6rem; border-radius: 20px; font-size: .78rem; font-weight: 600; background: #e9d8fd; color: #553c9a; }
        footer { text-align: center; padding: 1.5rem; color: #718096; font-size: .85rem; margin-top: 2rem; }
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
    <div class="page-header">
        <h1>&#128214; Books</h1>
        <div class="header-actions">
            <a href="/books/joined" class="btn btn-secondary">&#128279; Joined View</a>
            <a href="/books/new"    class="btn btn-primary">&#10133; Add Book</a>
        </div>
    </div>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">&#10003; ${successMessage}</div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>ISBN</th>
                <th>Published</th>
                <th>Genre</th>
                <th>Author</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}" varStatus="st">
                <tr>
                    <td>${st.count}</td>
                    <td><strong>${book.title}</strong></td>
                    <td style="font-family:monospace; font-size:.82rem;">${book.isbn}</td>
                    <td>${book.publishedYear}</td>
                    <td><span class="genre-badge">${book.genre}</span></td>
                    <td>${book.author.name}</td>
                    <td>
                        <a href="/books/${book.id}/edit" class="btn btn-warning btn-sm">&#9998; Edit</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty books}">
                <tr><td colspan="7" style="text-align:center; color:#718096; padding:2rem;">No books found.</td></tr>
            </c:if>
        </tbody>
    </table>
</div>
<footer>&copy; 2025 Library Management System</footer>
</body>
</html>
