<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Корзина</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/menu.css" rel="stylesheet">
    <link href="/css/news_item.css" rel="stylesheet">

    <script src="/js/jquery.min.js"></script>

    <script>
        function Delete(i) {
            $.ajax({
                url: "/deleteFromCart/" + i,
                type: "GET"
            });
        }
    </script>

    <script type="text/javascript">
        function ByProduct(i) {
            alert("Вы купили товара на сумму " + i);
        }
    </script>

</head>
<body>
<div class="container">

<#include "header.ftl">
<#include "menu.ftl">

    <div class="center-content">
        <div class="container">

            <div class="container_heading">Корзина</div>
            <div id="products_container">
            <#if basket??>
                <#list basket as productInbasket>
                    <div class="news_item">
                        <div class="news_item_text_time">Имя: ${productInbasket.getName()}</div>
                        <div class="news_item_text">ПодробноеОписание: ${productInbasket.getDetailed_description()}</div>
                        <div class="news_item_text">Цена: ${productInbasket.getPrice()}</div>
                        <div class="news_item_text">Оценка: ${productInbasket.getQuantity()}</div>
                        <br><br>
                        <img src="/imagesOfproducts/${productInbasket.getName()}.jpg" class="images">
                        <br>
                        <#if user??>
                            <button style="display: block" onclick="ByProduct(${productInbasket.getPrice()})">Купить</button>
                            <p></p>
                            <button style="display: block" onclick="Delete(${productInbasket.getId()})">Удалить из корзины
                            </button>
                        </#if>
                    </div>
                    <#if productInbasket_index<basket?size - 1>
                        <div class="divider"></div>
                    </#if>

                </#list>
            </#if>
            </div>
        </div>
    </div>

<#if error??>
    <h2>${error}</h2>
</#if>

</div>

</body>
</html>