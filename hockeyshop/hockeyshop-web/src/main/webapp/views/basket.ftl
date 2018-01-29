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
    <script src="/js/basket.js"></script>

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
                            <#list basket as productInBasket>
                                <div class="news_item">
                                    <div id="product${productInBasket.getProduct_id()}">
                                        <div class="news_item_text_time">Имя: ${productInBasket.getName()}</div>
                                        <div class="news_item_text">ПодробноеОписание:
                                            ${productInBasket.getDetailed_description()}
                                        </div>
                                        <div class="news_item_text">Цена: ${productInBasket.getPrice()}</div>
                                        <div class="news_item_text">Оценка: ${productInBasket.getQuantity()}</div>
                                        <br><br>
                                        <img src="/imagesOfproducts/${productInBasket.getName()}.jpg" class="images">
                                        <br>
                                        <#if user??>
                                            <button style="display: block"
                                                    onclick="buyProduct(${productInBasket.getPrice()})">Купить
                                            </button>
                                            <p></p>
                                            <button style="display: block"
                                                    onclick="deleteFromCart(${productInBasket.getProduct_id()})">Удалить
                                                из корзины
                                            </button>
                                        </#if>

                                    </div>
                                </div>
                                <#if productInBasket_index
                                <
                                basket?size - 1>
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