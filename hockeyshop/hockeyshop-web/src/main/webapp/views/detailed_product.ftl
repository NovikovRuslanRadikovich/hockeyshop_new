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

    <script src="/js/jquery.min.js"></script>

    <script src="/js/detailed_product.js"></script>


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
                <div class="news_item" id="detailed_product${product.getId()}">
                    <div class="news_item_text">Цена: ${product.getPrice()}</div>
                    <div class="news_item_text">Оценка: ${product.getQuantity()}</div>
                    <div class="news_item_text">Подробное Описание: ${product.getDetailed_description()}</div>
                    <br><br>
                    <img src="/imagesOfproducts/${product.getName()}.jpg" class="images"/>
                    <br><br>
                    <#if admin??>
                        <p> <button style="display: block" onclick="deleteProduct(${product.getId()})">Удалить</button> </p>

                    </#if>
                    <#if admin??>
                        <p> <a href="/edit_product/${product.getId()}">Редактировать</a> </p>
                    </#if>
                    <#if user??>
                        <button style="display: block" onclick="addToCart(${product.getId()})">Добавить в корзину
                        </button>

                        <form>
                            <p>Комментарий<br>
                                <label>
                                    <textarea id="comment" name="comment" action="" method="get" cols="40" rows="3"></textarea>
                                </label></p>
                            <p><input type="submit"  value="Отправить"</p>
                        </form>

                        <#if comments??>

                            <#list comments as comment>
                                <p>${comment}</p>
                            </#list>

                        </#if>

                    </#if>
                </div>
            </#if>
            </div>
        </div>

    </div>
</div>


</body>
</html>