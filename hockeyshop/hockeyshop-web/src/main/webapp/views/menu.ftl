<nav role="navigation" class="navbar">

    <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
<#if user??>
    <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li id="nws"><a href="/home"><div class="menu_button_text">Главная</div></a></li>
            <li id="nws"><a href="/tov"><div class="menu_button_text">Товары</div></a></li>
            <li id="im"><a href="/basket"><div class="menu_button_text">Корзина</div></a></li>
            <li id="ex"><a href="/home?action=logout"><div class="menu_button_text">Выход</div></a></li>
        </ul>
    </div>
</#if>

<#if !user?? >
    <#if !admin??>
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li id="nws"><a href="/home"><div class="menu_button_text">Главная</div></a></li>
                <li id="nws"><a href="/products"><div class="menu_button_text">Товары</div></a></li>
                <li id="im"><a href="/basket"><div class="menu_button_text">Корзина</div></a></li>
                <li id="ex"><a href="/login"><div class="menu_button_text">Вход</div></a></li>
            </ul>
        </div>
    </#if>
    <#if admin??>
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li id="nws"><a href="/home"><div class="menu_button_text">Главная</div></a></li>
                <li id="nws"><a href="/products"><div class="menu_button_text">Товары</div></a></li>
                <!--    <li id="im"><a href="/basket"><div class="menu_button_text">Корзина</div></a></li>  -->
                <li id="ex"><a href="/home?action=logout"><div class="menu_button_text">Выход</div></a></li>
                <li id="ex"><a href="/admin"><div class="menu_button_text">Админ</div></a></li>
            </ul>
        </div>
    </#if>
</#if>

</nav>