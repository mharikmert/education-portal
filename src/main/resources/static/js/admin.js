$(document).ready(() => {
    $('#main').on('click', function(event) {
        $.ajax({
            success : () =>{
                redirect('/admin')
            }
        });
    });
});

$(document).ready(() => {
    $('#studentApproval').on('click', function(event) {
        $.ajax({
            success : () =>{
                redirect('/studentApproval')
            }
        });
    });
});

$(document).ready(() => {
    $('#roleAssign').on('click', function(event) {
        $.ajax({
            success : () =>{
                redirect('/roleAssign')
            }
        });
    });
});

let usersJson ;
$(document).ready(() => {
    //ajax post to get all the cities
    $.ajax({
        url: '/api/users', // contains all the cities
        type: 'GET',
        dataType : 'json',
        headers : {
            'Content-Type' : 'application/json; charset=utf-8'
        },
        success: (result) => {
            usersJson = result;
            let idIndex = 0 ;
            $.each(result, function(index,value){
                $('#approval-list').append("<tr id=idIndex>"+"<td>"+value.id+"</td>"+"<td>"+value.firstName+"</td>"+"<td>"+value.lastName+"</td>"+
                    "<td>"+31+"</td>"+"<td>"+value.grade+"</td>"+"<td>"+value.section+"</td>"+"<td>"+value.phoneNumber+"</td>"+"<td>"+value.email+"</td>"+
                    "<td><button id='accept-approval'>"+"Onay"+"</button></td>"+"<td><button id='reject-approval'>"+"Sil"+"</button></td>"+"</tr>")


                idIndex++;

            });

        }, // end of ajax success
        error : function (result){
            console.log('city ajax GET failed! with result : ', result);
        }
    }); // end of ajax GET cities
});


