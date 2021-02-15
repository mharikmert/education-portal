//post request
$(document).ready(() => {
    $("#register-button").on('click', function() {
        $.ajax({
            url : "/api/register",
            type : "POST",
            dataType: 'json',
            headers : {"Content-Type" : "application/json; charset=utf-8"},
            data : $('#form').serialize(),
            /* data json should be edited */
            success : (result) =>{
                console.log(result);
                redirect('registrationApproval.html');
            },
            error : (xhr, resp, text) => {
                console.log(xhr, resp, text)
            }
        });
    });
});

let citiesJson;
//add all the cities to the #city select box with get request
$(document).ready(() => {
    //ajax post to get all the cities
    $.ajax({
        url: '/api/cities', // contains all the cities
        type: 'GET',
        dataType : 'json',
        headers : {
            "Content-Type" : "application/json; charset=utf-8"
        },
        success: (result) => {
            citiesJson = result;
            $.each(result, function(index,value){
                //appends cities to the select box
                const option = document.createElement('option');
                option.text = value['cityName'];
                $('#city').append(option);
            });
        }, // end of ajax success
        error : function (result){
            console.log('city ajax GET failed! with result : ', result);
        }
    }); // end of ajax GET cities
});

//city and district selections
$(document).ready( () => {
    $('#city').change( () =>{
        //city changes, then clear the content of district
        $('#district').attr('disabled',false).html(
            '<option> İlçe seçiniz </option>'
        );
        //defines the selected option
        let option = document.querySelector('#city');
        let selectedOption = option.options[option.selectedIndex].value;
        //iterate through cities json
        $.each(citiesJson, (index, value) => {
            //cities are matched, bring the districts that one with get request to the endpoint
            if(selectedOption === value['cityName']){
                const plateNo = value['plateNo'];
                $.ajax({
                    url: '/api/cities/' + plateNo + '/districts', //contains all the districts with the plateNo
                    type: 'GET',
                    dataType: 'json',
                    headers: {
                        'Content-Type': 'application/json; charset = utf-8'
                    },
                    success: (result) => {
                        //districts are selectable
                        $('#district').attr('disabled',false);
                        //fill the districts
                        $.each(result, (index, value) => {
                            const option = document.createElement('option');
                            option.text = value['districtName'];
                            $('#district').append(option);
                        });
                    },
                    error :  (xhr, rsp, txt ) => {
                        console.log(xhr,rsp,txt);
                    }
                }); // enf of ajax GET
            }// end of if case
        }); // end of cities loop
    }); // end of city change function

})
//redirects to pages to the given url
const redirect = (URL) => {
    window.location.href = URL;
    return true;
}

