<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Barbiere</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 50px;
        }

        .login-form {
            max-width: 300px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px 0px #000000;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: none;
            background-color: #007bff;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .register-link {
            margin-top: 15px;
            text-align: center;
        }

        .alert {
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-form">
            <h2 class="mt-5">Login Barbiere</h2>

            <% if (request.getAttribute("error") != null && !((String)request.getAttribute("error")).isEmpty()) { %>
                <div class="alert alert-danger" role="alert">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <form action="/login" method="post" class="mt-4">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit">Accedi</button>
            </form>

            <p class="register-link">Non hai un account? <a href="/registrazione">Registrati</a></p>
        </div>
    </div>
</body>
</html>