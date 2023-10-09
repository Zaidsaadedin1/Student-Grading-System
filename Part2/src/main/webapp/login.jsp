<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script type="text/javascript" src="script.js"></script>
</head>
<body>
    <div class="container">
        <h2>Login Page</h2>
        <form class="login-form" action="loginAction" method="post">
            <div class="form-group">
                <label class="label" for="id">ID:</label>
                <input class="input id" type="text" name="id">
            </div>
            <div class="form-group">
                <label class="label" for="password">Password:</label>
                <input class="input password" type="password" name="password">
            </div>
            <button class="button" type="submit">Login</button>
        </form>
    </div>
</body>
</html>
