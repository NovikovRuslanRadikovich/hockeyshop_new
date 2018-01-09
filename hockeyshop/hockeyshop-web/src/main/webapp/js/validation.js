

$(document).ready(function () {
    $(".form-center-content").each(function () {

        var form = $(this);
        
        function addClassEmptyIfNoValue() {
            form.find(".form-control").each(function () {
                if($(this).val() != ''){
                    $(this).removeClass("empty-field")
                } else {
                    $(this).addClass("empty-field")
                }
            })
        }
        
        function lightEmpty(interval) {
            var emptyFields = form.find('.empty-field');

            emptyFields.css({
                'border-color' : 'red',
                'border-width' : '1px',
                'box-shadow' : 'inset 0px 0px 5px rgba(100, 0, 0, 70)'
            });

            setTimeout(function () {
                emptyFields.removeAttr('style')
            }, interval)
        }


        function isValidPhone(phone) {
            var pattern = new RegExp(/^[0-9]+$/i);
            return pattern.test(phone);
        }

        function checkOther(){
            var phone = $('input[name=phone]');
            if( ! isValidPhone(phone.val())){
                phone.appendChild("Не верный телефон");
                return false;
            }

            return true;
        }

        form.submit(function() {

            addClassEmptyIfNoValue();
            var countOfEmptyFields = form.find('.empty-field').size();

            if(countOfEmptyFields > 0){
                lightEmpty(600)
            } else {

                if(checkOther()){
                    //noinspection JSUnresolvedFunction
                    $.ajax({
                        url: "register",
                        type: "POST",
                        data: {
                            "name" : $('input[name=name]').val(),
                            "phone" : $('input[name=phone]').val(),
                            "password" : $('input[name=password]').val(),
                            "password2" : $('input[name=password2]').val(),
                            "dob" : $('input[name=dob]').val(),
                            "city" : $('select option:selected').val(),
                            "sex" : $('input[name=sex]:checked').val()
                        }
                    }).done(function(data) {
                        if(data ==  "success"){
                            form.html("Успешно!");

                            setTimeout(function () {
                                location.href = "/"
                            }, 500);

                        } else {
                            alert(data)
                        }
                    });
                }
            }

            return false
        })
    })
});