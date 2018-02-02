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

</head>

<body>


<#if productsDecade??>

    <#list productsDecade as product>

        <div class="news_item">

                <div class="news_item_text_time">Имя: ${product.getName()}</div>

        </div>

        <#if product_index < productsDecade?size - 1>
              <div class="divider"></div>
         </#if>
    </#list>

</#if>

</body>

</html>