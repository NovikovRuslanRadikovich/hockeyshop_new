function deleteFromCart(id) {
    $.ajax({
                   url: "/deleteFromCart/" + id,
                   type: "GET"
    }).done(function(data) {
        $('#product'+id).hide(400);
    })
}

function buyProduct(price) {
     alert("Вы купили товара на сумму " + price);
}