$(document).ready(function() {

         var username = '${username}';


         $("#commentForm").each(function() {

              var form = $(this);

              form.submit(function() {

                   var comment = form.find("[name='comment']");
                   var productId = form.attr('id');

                   alert(productId);

                   $.ajax({
                      url : form.attr('action'),
                      type: form.attr('method'),
                      data: {
                          "comment" : comment,
                          "productId" : productId
                      }
                   }).done(function(data) {
                       alert("YEAHHHHHH");
                       $("#comments").append("<p>" + data + "</p>");

                       form[0].reset();



                   });

                   return false;

              })




         })




});