<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        nav {
            background-color: #333;
            color: white;
            padding: 10px 0;
        }
        nav ul {
            list-style: none;
            display: flex;
            justify-content: center;
            margin: 0;
            padding: 0;
        }
        nav li {
            margin: 0 20px;
        }
        nav a {
            color: white;
            text-decoration: none;
            transition: color 0.3s;
        }
        nav a:hover {
            color: #ff9900;
        }
    </style>
    <script>
        // JavaScript to toggle mobile menu
        function toggleMenu() {
            var navList = document.querySelector('.nav-list');
            navList.classList.toggle('active');
        }
    </script>
</head>
<body>
    <nav>
        <div class="menu-toggle" onclick="toggleMenu()">
            <span></span>
            <span></span>
            <span></span>
        </div>
        <ul class="nav-list">
            <li><a href="login.jsp">Login</a></li>
            <!-- Add more links to other pages if needed -->
        </ul>
    </nav>
</body>
</html>
