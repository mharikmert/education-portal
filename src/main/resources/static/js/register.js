
//post request
$(document).ready(() => {
    $('#register-button').on('click', function(event) {
        event.preventDefault();
        const formData = $('#form').serializeArray();
        let obj = {}, formState = {}, registerCondition = true;
        $.each(formData, (index, value) => {
            //radio button conversion
            if(value['value'] === 'yes' || value['value'] === 'no'){
                //yes -> true, no -> false
                value['value'] = value['value'] === 'yes';
                value['name'] = 'hasInternet';
            }

            obj[value['name']] = value['value']

            if(value['name'].includes('Name') || value['name'] === 'educationReason')
                formState[value['name']] = validateNameForm(value['name']);

            else if(value['name'] === 'id')
                formState[value['name']] = validateIdForm(value['name'])

            else if(value['name'] === 'email')
                formState[value['name']] = validateEmail(value['name'])

            else if(value['name'].includes('Number'))
                formState[value['name']] = validatePhoneForm(value['name'])

            $.each(formState, (index, value) => {
                if(!value) registerCondition = false;
            })
        })
        const formJson = JSON.stringify(obj);
        if(registerCondition){
            $.ajax({
                url : '/api/register',
                type : 'POST',
                dataType: 'json',
                headers : {'Content-Type' : 'application/json; charset=utf-8'},
                data : formJson,
                success : () =>{
                    redirect('/approval')
                },
                error : (xhr, resp, text) => {
                    console.log(xhr, resp, text)
                    $(document).ready(function(){
                        $("#myModal").modal();
                    });
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
        //district becomes enable to select
        $('#district').attr('disabled',false).html('<option> İlçe Seçiniz </option>');
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
const throwWarning = (inputFieldId) => {
    $('#'+inputFieldId).css('border', '1px solid red')
    $('#invalid-' + inputFieldId + '-warning').css('display', 'block');
    $('#' + inputFieldId + 'Label').css('display','none');
}

// const throwEmptyFieldWarning = (inputFieldId) => {
//     $('#'+inputFieldId).css('border', '1px solid red')
//     const warningDiv = $('#invalid-' + inputFieldId + '-warning');
//     warningDiv.text('Bu alan boş bırakılamaz');
//     warningDiv.css('display', 'block');
//     $('#' + inputFieldId + 'Label').css('display','none');
// }

//focusin event will be appended
const takeBackWarning = (inputFieldId) => {
    $('#'+inputFieldId).css('border', 'none')
    $('#invalid-' + inputFieldId + '-warning').css('display', 'none');
    $('#' + inputFieldId + 'Label').css('display','block');
}

function validateNameForm(id) {
    var x = document.forms["form"][id].value;
    // if (x === "")
    //     throwEmptyFieldWarning(id)

    if (x.length<2)
        throwWarning(id);
    else{
        takeBackWarning(id);
        return true;
    }
    return false;
}

function validateIdForm(id) {
    var x = document.forms["form"][id].value;
    // if(x.length === 0)
    //     throwEmptyFieldWarning(id);
    if (isValidUserID(x)){
        takeBackWarning(id)
        return true;
    }
    else throwWarning(id)
    return false;
}

function validatePhoneForm(id) {
    var x = document.forms["form"][id].value;
    // if(x.length === 0)
    //     throwEmptyFieldWarning(id)

    if(x === 'string') throwWarning(id)
    else if (x.length !== 11)
        throwWarning(id);
    else {
        takeBackWarning(id);
        return true ;
    }
    return false;
}

function validateEmail(id){
    var x = document.forms["form"][id].value;
    const emailRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/

    if (emailRegex.test(x)){
        takeBackWarning(id);
        return true;
    }
    else
        throwWarning(id);

    return false;
}

const nameFieldsControl = (id) => {
    $('#' + id).bind('keypress', function(e) {

        const k = e.which;
        const ok =
            k >= 65 && k <= 90 || // A-Z
            k >= 97 && k <= 122 ||// a-z
            k === 32 || k === 199 ||
            k === 231 || k === 286 ||
            k === 287 || k === 304 ||
            k === 305 || k === 214 ||
            k === 246 || k === 350 ||
            k === 351 || k === 220 ||
            k === 252

        if (!ok){
            // throwWarning(id)
            e.preventDefault();
        }
    });
}
const numericalFieldsControl = (id) => {
    $('#' + id).bind('keypress', function(e) {
         if($("#"+id).val().length < 11){
            var k = e.which;
            var ok = k >= 48 && k <= 57; // 0-9

            if (!ok){
                e.preventDefault();
            }
        }else{
            // throwWarning(id);
            e.preventDefault();
        }
    });
}

nameFieldsControl('firstName');
nameFieldsControl('lastName');
nameFieldsControl('parentFirstName');
nameFieldsControl('parentLastName');
numericalFieldsControl('id');
numericalFieldsControl('phoneNumber');
numericalFieldsControl('parentPhoneNumber');

const inputFocusIn = (id) => {
    $('#' + id).focus( () => {
        takeBackWarning(id);
    })
}

/* TO DO -> iterate through all the inputs and invoke the method */
inputFocusIn('id')
inputFocusIn('firstName')
inputFocusIn('lastName')
inputFocusIn('phoneNumber')
inputFocusIn('email')
inputFocusIn('birthDate')
inputFocusIn('address')
inputFocusIn('schoolName')
inputFocusIn('parentFirstName')
inputFocusIn('parentLastName')
inputFocusIn('parentPhoneNumber')
inputFocusIn('educationReason')


const redirect = (url) => {
    window.location.href = url;
    return true;
}
