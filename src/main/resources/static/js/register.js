$(document).ready(function() {
    $("#register-button").on('click', function() {
        $.ajax({
            url : "localhost:8080/api/register",
            type : "POST",
            dataType: 'json',
            headers : {"Content-Type" : "application/json; charset=utf-8"},
            data : $('#form').serialize(),
            /* data json should be edited */
            success : function(result) {
                console.log(result);
                redirect('registrationApproval.html');
            },
            error : function(xhr, resp, text) {
                console.log(xhr, resp, text)
            }
        });
    });
});

//add all the cities to the #city select box with get request
$(document).ready(function(){
    $.ajax({
        url: '/api/cities',
        type: 'GET',
        dataType : 'json',
        headers : {"Content-Type" : "application/json; charset=utf-8"},
        success: function (result){
            $.each(result, function(index,value){
                const option = document.createElement('option');
                option.text = value['cityName'];
                $('#city').append(option);
            });
        },
    });
});
/* district select box should be filled with selection city */
$(document).ready(function (){
    $('#city').change( function() {
        console.log('city is changed..');
        let selectedOption = $(this).children('option:selected').val();
        console.log(selectedOption);
    });
})
const redirect = (URL) => {
    window.location.href = URL;
    return true;
}