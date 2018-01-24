<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Вход</title>

    <link href="../../css/singin.css" rel="stylesheet">
    <link href="../../css/style.css" rel="stylesheet">
    <link href="../../css/menu.css" rel="stylesheet">
    <script src="../../js/jquery.min.js"></script>
</head>
<body>

<div class="container">

<#include "../header.ftl">

    <nav role="navigation" class="navbar">

        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li id="nws"><a href="/home"><div class="menu_button_text">Главная</div></a></li>
                <li id="nws"><a href="/products"><div class="menu_button_text">Товары</div></a></li>
            </ul>
        </div>
    </nav>

    <form class="form-center-content" action="/login" method="post">
        <div class="form-signin-heading">Вход</div>

        <input class="form-control" type="text" id="name" name="name" placeholder="Логин"/>
        <input class="form-control" type="password" id="password" name="password" placeholder="Пароль"/>

        <label for="save">Запомнить меня</label>
        <input id="save" type="checkbox" name="save" checked="checked"/>
    <#if error??>
        <div class="text-danger">${error}</div>
    </#if>

        <input class="login-button" type="submit" id="login" name="login" value="Войти"/>
        <a class="register-button" href="/register">Регистрация</a>
    </form>

</div>

</body>
</html>