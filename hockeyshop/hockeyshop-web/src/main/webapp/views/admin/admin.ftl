<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html">


    <title>Главная</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/menu.css" rel="stylesheet">
    <link href="/css/news_item.css" rel="stylesheet">

    <script src="/js/jquery.min.js"></script>
    <script src="/js/products.js"></script>
</head>
<body>
<div class="container">

<#include "../header.ftl">
<#include "../menu.ftl">

    <div class="center-content">
        <div class="container">
            Новый товар:
            <br>
            <form enctype="multipart/form-data" name="new_product" class="news_item" action="/admin" method="post">
                <input name="action" value="new_feed_item" type="hidden">
                <label>
                    Имя:
                    <textarea class="product_input" name="product_name"></textarea>
                </label>
                <label>
                    Описание:
                    <textarea class="product_input" name="description"></textarea>
                </label>
                <label>
                    Подробное описание:
                    <textarea class="product_input" name="detailed_description"></textarea>
                </label>
                <label>
                    Цена:
                    <input type="number" class="product_input" name="price"/>
                </label>
                <label>
                    Оценка:
                    <input type="number" class="product_input" name="quantity">
                </label>
                <br><br>
                <label>
                    <p> Загрузка изображения: </p>

                    <input type="file" name="picture" accept="image/jpeg,image/png" multiple>
                    <label>
                        <input class="button_add_news_item" type="submit">

            </form>

        </div>
    </div>

<#if error??>
    <h2>${error}</h2>
</#if>

</div>

</body>
</html>