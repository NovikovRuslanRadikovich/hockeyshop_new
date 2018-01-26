<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Товары</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/menu.css" rel="stylesheet">
    <link href="/css/news_item.css" rel="stylesheet">

    <script src="/js/jquery.min.js"></script>
    <script src="/js/products.js"></script>
</head>

<body>


<#if productsDecade??>

    <#list productsDecade as product>

        <div class="news_item">

            <div id="product${product.getProduct_id()}">

                <div class="news_item_text_time">Имя: ${product.getName()}</div>
                <div class="news_item_text">Описание: ${product.getDescription()}</div>
                <div class="news_item_text">Цена: ${product.getPrice()}</div>
                <div class="news_item_text">Оценка: ${product.getQuantity()}</div>
                <br/><br/>
                <img style="margin: auto" src="/imagesOfproducts/${product.getName()}.jpg" class="images"/>
                <br/><br/>
                <a href="/product_detail/${product.getProduct_id()}">Подробно...</a>
                <#if admin??>
                    <p>
                        <button style="display: block" onclick="deleteProduct(${product.getProduct_id()})">Удалить</button>
                    </p>

                </#if>
                <#if admin??>
                    <p><a href="/edit_product/${product.getProduct_id()}">Редактировать</a></p>
                </#if>

            </div>

        </div>

        <#if product_index < productsDecade?size - 1>
                <div class="divider"></div>
        </#if>
    </#list>

</#if>


<#if nextProductsDecades??>

    <#list nextProductsDecades as nextProductDecade>
        <a href="/getproductsnext/${nextProductDecade}"> ${nextProductDecade} </a> &nbsp;
    </#list>

</#if>


</body>
</html>