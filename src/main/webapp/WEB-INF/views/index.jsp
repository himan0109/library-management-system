<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Management System</title>
    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f0f4f8; color: #1a202c; }
        .navbar {
            background: linear-gradient(135deg, #1a365d 0%, #2c5282 100%);
            padding: 0 2rem; display: flex; align-items: center; justify-content: space-between;
            height: 64px; box-shadow: 0 2px 8px rgba(0,0,0,.25);
        }
        .navbar-brand { color: #fff; font-size: 1.4rem; font-weight: 700; text-decoration: none; letter-spacing: .5px; }
        .navbar-links a { color: #bee3f8; text-decoration: none; margin-left: 1.5rem; font-size: .95rem; transition: color .2s; }
        .navbar-links a:hover { color: #fff; }
        .hero {
            background: linear-gradient(135deg, #2c5282 0%, #2b6cb0 100%);
            color: #fff; padding: 5rem 2rem; text-align: center;
        }
        .hero h1 { font-size: 2.8rem; margin-bottom: 1rem; font-weight: 800; }
        .hero p  { font-size: 1.15rem; opacity: .88; max-width: 560px; margin: 0 auto 2.5rem; }
        .btn { display: inline-block; padding: .7rem 1.8rem; border-radius: 6px; text-decoration: none; font-weight: 600; transition: transform .15s, box-shadow .15s; }
        .btn-light { background: #fff; color: #2c5282; }
        .btn-light:hover { transform: translateY(-2px); box-shadow: 0 6px 18px rgba(0,0,0,.18); }
        .cards { display: flex; gap: 2rem; justify-content: center; flex-wrap: wrap; padding: 3.5rem 2rem; }
        .card {
            background: #fff; border-radius: 12px; padding: 2.2rem; width: 280px;
            box-shadow: 0 4px 16px rgba(0,0,0,.08); transition: transform .2s, box-shadow .2s;
            text-align: center;
        }
        .card:hover { transform: translateY(-4px); box-shadow: 0 8px 28px rgba(0,0,0,.14); }
        .card-icon { font-size: 3rem; margin-bottom: 1rem; }
        .card h2 { font-size: 1.25rem; margin-bottom: .5rem; color: #2c5282; }
        .card p  { color: #718096; font-size: .92rem; margin-bottom: 1.5rem; }
        .card-links a { display: block; margin: .35rem 0; color: #2c5282; text-decoration: none; font-weight: 500; font-size: .9rem; }
        .card-links a:hover { text-decoration: underline; }
        footer { text-align: center; padding: 1.5rem; color: #718096; font-size: .85rem; background: #fff; border-top: 1px solid #e2e8f0; }
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

<div class="hero">
    <h1>Library Management System</h1>
    <p>Manage your collection of books and authors with full Create, Read, and Update support.</p>
    <a href="/authors" class="btn btn-light">Browse Authors</a>
    &nbsp;&nbsp;
    <a href="/books" class="btn btn-light">Browse Books</a>
</div>

<div class="cards">
    <div class="card">
        <div class="card-icon">&#9997;</div>
        <h2>Authors</h2>
        <p>Add and manage author profiles with nationalities and contact details.</p>
        <div class="card-links">
            <a href="/authors">&#128203; View All Authors</a>
            <a href="/authors/new">&#10133; Add New Author</a>
        </div>
    </div>
    <div class="card">
        <div class="card-icon">&#128214;</div>
        <h2>Books</h2>
        <p>Catalog books with ISBN, genre, and publication year linked to their authors.</p>
        <div class="card-links">
            <a href="/books">&#128203; View All Books</a>
            <a href="/books/new">&#10133; Add New Book</a>
        </div>
    </div>
    <div class="card">
        <div class="card-icon">&#128279;</div>
        <h2>Joined View</h2>
        <p>Explore the INNER JOIN result set — books paired with their author details.</p>
        <div class="card-links">
            <a href="/books/joined">&#128203; View Joined Data</a>
        </div>
    </div>
</div>

<footer>
    &copy; 2025 Library Management System &mdash; Built with Spring Boot + JPA
</footer>
</body>
</html>
