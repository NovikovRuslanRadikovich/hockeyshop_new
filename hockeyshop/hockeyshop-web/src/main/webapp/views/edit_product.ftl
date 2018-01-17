<#if product??>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Подробно</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/menu.css" rel="stylesheet">
    <link href="/css/news_item.css" rel="stylesheet">
    <link href="/css/singin.css" rel="stylesheet">

    <script src="/js/jquery.min.js"></script>
</head>
<body>
<div class="container">

    <#include "header.ftl">
    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">

            <div class="container_heading" name="name">${product.getName() ! "Товар не найден"}</div>
            <div id="products_container">
                <#if product??>
                    <div class="news_item">
                        <form action="/edit_product/${product.getId()}" method="post">
                            <div class="form-signin-heading">Редактирование товара</div>
                            <label>Подробное Описание:
                                <input name="detailed_description" class="form-control" value="${product.getDetailed_description()}"/>
                            </label>
                            <label>Цена:
                                <input name="price" class="form-control" value="${product.getPrice()}"/>
                            </label>
                            <input class="login-button" type="submit" value="Изменить">
                        </form>
                    </div>
                </#if>
            </div>
        </div>

    </div>
</div>


</body>
</html>
</#if>