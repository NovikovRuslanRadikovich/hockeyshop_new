<!DOCTYPE html>
<html lang="en">
<head>
    <meta content="text/html" http-equiv="Content-Type" charset="UTF-8">
    <title>Home</title>

    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>

    <script type="text/javascript"  charset="utf-8" src="/resources/js/home.js" ></script>

    <script>

    <!--function exit(){-->

         <!--$.ajax({-->

              <!--type: "GET",-->
              <!--url: "/home",-->

              <!--data : {-->
                  <!--"exit" : "true"-->
              <!--},-->

              <!--success: function() {-->
                  <!--$("#adminhref").hide();-->
            <!--&lt;!&ndash;      $("#form").show(); &ndash;&gt;-->
              <!--}-->

         <!--});-->

    <!--}-->

    </script>
</head>

<body>


<br>

<#if not_logged??>
    <p style="color:red">First you have to login </p>

</#if>

Hello ${username!"anonymous"} !!!

<br>

<#if anonymous??>
    <form action="/home" method="post" id="form">
        <label for="username"> Username</label>Y
        <input type="text" name="username" id="username">
        <br>
        <label for="password">Password</label>
        <input type="password" name="password" id="password">
        <br>
        <input type="submit" id="submit" value="Войти">
    </form>

</#if>

<#if anonymous??>
    <br> <a href="/registration">On registration</a>
</#if>


<br> <a href="/admin" id="adminhref">On admin Page</a>

<#if username??>
    <button id="exit" onclick="exit()"> Exit</button>
</#if>

</body>
</html>