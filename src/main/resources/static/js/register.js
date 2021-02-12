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

$(document).ready(function(){
    $('#city').click(function(){
        const state = $(this).val(), city = $('#city');
        //get json from endpoint
        $.ajax({
            url : '/api/cities',
            type  : 'GET',
            dataType: 'json',
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            success: function(result){
                /* TO DO
                * cityNames will make appear in city-container div
                * after cityName is selected, get districts of the city to district-container
                * */
                if(result !== undefined && result.length > 0){
                    city.find('option').remove();
                    $(result).each(function(index, element){
                        city.append($('<option> </option>').attr('value', element).text(element));
                    })
                }
            },
            error: function (result){
                console.log('error occurred')
                console.log(result);
            }
        });
    });
})
