
function updateProducts() {
    $.ajax({
        url: "/products?action=get_products",
        type: "GET"
    }).done(function(data) {
        var newsContainer = $('#products_container');
        newsContainer.html(data);
        newsContainer.hide();
        newsContainer.show(350);
    })
}
$(document).ready(function () {
    updateProducts();

    $("#new_product").each(function () {
        var form = $(this);

        form.submit(function() {
            var name = form.find('.product_input[name=product_name]').val();
            var description = form.find('.product_input[name=description]').val();
            var detailed_description = form.find('.product_input[name=detailed_description]').val();
            var price = form.find('.product_input[name=price]').val();
            var quantity = form.find('.product_input[name=quantity]').val();
            var image =form.find('.images[name=picture]').val();
            if (name == '') return false;

            $.ajax({
                url: "/tov",
                type: "POST",
                data: {
                    "description" : description,
                    "detailed_description" : detailed_description,
                    "name" : name,
                    "price" : price,
                    "quantity" : quantity,
                    "image" : image
                }
            }).done(function(data) {
                if(data == "success"){
                    form[0].reset();

                    setTimeout(function () {
                        updateProducts();
                    }, 100);

                } else {
                    alert(data)
                }
            });

            return false;
        })
    })
});

function deleteProduct(id) {
    $.ajax({
        url: "/products?action=delete&id=" + id,
        type: "GET"
    }).done(function(data) {
        updateProducts()
    })
}