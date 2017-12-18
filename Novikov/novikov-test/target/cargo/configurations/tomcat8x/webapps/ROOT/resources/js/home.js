$(document).ready(function() {

     $("#exit").click(function(e) {

         $.ajax({

           type: "GET",
           url: "/home",

           data : {
             "exit" : "true"
           },

           success : function() {
              $("#adminhref").hide();
           }

         })


     });

});