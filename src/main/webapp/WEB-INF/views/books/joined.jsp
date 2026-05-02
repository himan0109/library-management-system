<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Books &times; Authors Join | LibraryMS</title>
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
        .page-header { margin-bottom: 1.2rem; }
        .page-header h1 { font-size: 1.8rem; color: #2c5282; }
        .page-header p  { color: #718096; margin-top: .3rem; font-size: .93rem; }
        .sql-box {
            background: #1a202c; color: #68d391; border-radius: 8px;
            padding: 1rem 1.4rem; font-family: monospace; font-size: .88rem;
            margin-bottom: 1.8rem; overflow-x: auto;
        }
        table { width: 100%; border-collapse: collapse; background: #fff; border-radius: 10px; overflow: hidden; box-shadow: 0 2px 12px rgba(0,0,0,.08); }
        thead th { background: #553c9a; color: #fff; padding: .85rem 1rem; text-align: left; font-size: .9rem; }
        tbody tr { border-bottom: 1px solid #e2e8f0; transition: background .15s; }
        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #faf5ff; }
        td { padding: .8rem 1rem; font-size: .88rem; }
        .badge-genre  { display: inline-block; padding: .2rem .6rem; border-radius: 20px; font-size: .78rem; font-weight: 600; background: #e9d8fd; color: #553c9a; }
        .badge-nation { display: inline-block; padding: .2rem .6rem; border-radius: 20px; font-size: .78rem; font-weight: 600; background: #bee3f8; color: #2c5282; }
        .btn { display: inline-block; padding: .55rem 1.3rem; border-radius: 6px; text-decoration: none; font-weight: 600; font-size: .9rem; transition: transform .15s; }
        .btn-secondary { background: #718096; color: #fff; }
        .btn-secondary:hover { background: #4a5568; }
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
        <h1>&#128279; Books &times; Authors &mdash; Inner Join View</h1>
        <p>Result of the custom JPQL INNER JOIN query between the <strong>books</strong> and <strong>authors</strong> tables.</p>
    </div>

    <div class="sql-box">
        SELECT b.id, b.title, b.isbn, b.published_year, b.genre,<br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; a.id, a.name, a.nationality<br>
        FROM books b INNER JOIN authors a ON b.author_id = a.id<br>
        ORDER BY a.name, b.title
    </div>

    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Book Title</th>
                <th>ISBN</th>
                <th>Year</th>
                <th>Genre</th>
                <th>Author Name</th>
                <th>Nationality</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${joinedBooks}" varStatus="st">
                <tr>
                    <td>${st.count}</td>
                    <td><strong>${row.title}</strong></td>
                    <td style="font-family:monospace; font-size:.8rem;">${row.isbn}</td>
                    <td>${row.publishedYear}</td>
                    <td><span class="badge-genre">${row.genre}</span></td>
                    <td>${row.authorName}</td>
                    <td><span class="badge-nation">${row.authorNationality}</span></td>
                </tr>
            </c:forEach>
            <c:if test="${empty joinedBooks}">
                <tr><td colspan="7" style="text-align:center; color:#718096; padding:2rem;">No data available.</td></tr>
            </c:if>
        </tbody>
    </table>

    <div style="margin-top:1.5rem;">
        <a href="/books" class="btn btn-secondary">&#8592; Back to Books</a>
    </div>
</div>
<footer>&copy; 2025 Library Management System</footer>
</body>
</html>
