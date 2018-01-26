 function addToCart(i) {
            $.ajax({
                url: "/addToCart/" + i,
                type: "GET"
            });
 };

 function deleteProduct(id) {
     $.ajax({
         url: "/products?action=delete&id=" + id,
         type: "GET"
     }).done(function(data) {
         $('#detailed_product'+id).hide(400);
     })
 }
