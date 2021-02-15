//post request
$(document).ready(function() {
    $("#register-button").on('click', function() {
        $.ajax({
            url : "/api/register",
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
        //appends the cities to the city select box
        success: function (result){
            $.each(result, function(index,value){
                const option = document.createElement('option');
                option.text = value['cityName'];
                $('#city').append(option);
            });
        },
        error : function (result){
            console.log('city ajax GET failed! with result : ', result);
        }
    });
});

$(document).ready(function () {
    //when option value is selected or changed
    $('#city').change( () => {
        let option = document.querySelector('#city');
        let selectedOption = option.options[option.selectedIndex].value;
        console.log(selectedOption);
    });
});
const redirect = (URL) => {
    window.location.href = URL;
    return true;
}

