$(document).ready(function() {
    $("#register-button").on('click', function() {
        console.log("i am in the jquery")
        $.ajax({
            url : "localhost:8080/api/register",
            type : "POST",
            dataType: 'json',
            headers : {"Content-Type" : "application/json; charset=utf-8"},
            data : $('#form').serialize(),
            success : function(result) {
                console.log(result)
            },
            error : function(xhr, resp, text) {
                console.log(xhr, resp, text)
            }
        });
    });
});

