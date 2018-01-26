$(document).ready(function() {

          $("#commentForm").submit(function() {

                   var form = $(this);

                   var comment = form.find('[name=comment]').val();
                   var productId = form.find('[name=comment]').prop('id');

                   $.ajax({
                      url : "/comments",
                      type: "POST",
                      data: {
                          "comment" : comment,
                          "productId" : productId,
                      }
                   }).done(function(data) {

                       $("#comments").append("<p>" + data + "</p>");

                       form[0].reset();

                   });

                   return false;

         })


});