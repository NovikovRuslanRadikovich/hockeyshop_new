
function deleteProduct(id) {
    $.ajax({
        url: "/products?action=delete&id=" + id,
        type: "GET"
    }).done(function(data) {
        $('#product'+id).hide(400);
    })
}