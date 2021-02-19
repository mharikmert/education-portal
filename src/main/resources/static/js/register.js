
//post request
$(document).ready(() => {
    let formState = false;
    $('#register-button').on('click', function(event) {
        event.preventDefault();
        let formData= $('#form').serializeArray();
        let obj = {};
        $.each(formData, (index, value) => {
            //radio button conversion
            if(value['value'] === 'yes' || value['value'] === 'no'){
                //yes -> true, no -> false
                value['value'] = value['value'] === 'yes';
                value['name'] = 'hasInternet';
            }

            //id check
            else if(value['name'] === 'id'){
                const id = value['value'];
                if(!isValidUserID(id)){
                    throwWarning('#id', '#invalid-tckn')
                }
            }

            // name fields check
            else if(value['name'] === 'firstName' || value['name'] === 'lastName'){
                const nameField = value['name'];
                if(!validateNameField('#' + nameField)){
                    throwWarning('#'+ nameField, '#invalid-' + nameField + '-warning')
                }
            }

            obj[value['name']] = value['value']
        })

        const formJson = JSON.stringify(obj);

        console.log('state -> ', formState);
        if(formState){
            $.ajax({
                url : '/api/register',
                type : 'POST',
                dataType: 'json',
                headers : {'Content-Type' : 'application/json; charset=utf-8'},
                data : formJson,
                success : (result) =>{
                    console.log('result', result);
                    redirect('/');
                },
                error : (xhr, resp, text) => {
                    console.log(xhr, resp, text)
                }
            });
        }
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
            'Content-Type' : 'application/json; charset=utf-8'
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

//id validation method for turkish identity number
const isValidUserID = (userID) => {
    userID = String(userID);
    if (userID.substring(0, 1) === '0') return false;
    if (userID.length !== 11) return false;

    const tenTotalArray = userID.substr(0, 10).split('');
    let odd = 0,even = 0, tenTotal = 0;

    let j;
    for (let i = j = 0; i < 9; ++i) {
        j = parseInt(tenTotalArray[i], 10);
        if (i & 1) even += j;
        else odd += j;
        tenTotal += j;
    }
    if ( (odd * 7 -even ) % 10 !== parseInt(userID.substr(-2, 1), 10)) {
        return false;
    }
    tenTotal += parseInt(tenTotalArray[9], 10);
    return tenTotal % 10 === parseInt(userID.substr(-1), 10);
};

//validates the name fields
const validateNameField = (fieldId) => {
    //regex pattern for name fields
    const regex = /^[a-zA-Z ]{2,30}$/; // between 2-30, might contain any character from a to Z
    const field = document.querySelector(fieldId).value;
    return regex.test(field);
}

//throws warning with given input field id and its warning
// warning id's might edit, to avoid second parameter
const throwWarning = (inputInputFieldId, warningId) => {
    $(inputInputFieldId).css('border', '2px solid red')
    $(warningId).css('display', 'block');
}

//focusin event will be appended
const takeBackWarning = (warningId) => {
    $(warningId).css('display', 'none');
}
