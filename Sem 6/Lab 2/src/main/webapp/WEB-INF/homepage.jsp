<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>

<h1>Login</h1>

<form action="/home" method="post">
    <table>
        <tr>
            <td><label for="email">Email</label></td>
            <td><input type="text" name="email" id="email"></td>
        </tr>
        <tr>
            <td><label for="password">Password</label></td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Create">
            </td>
            <td>
                <input type="reset" value="Clear">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
