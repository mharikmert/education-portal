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
            $.each(result, function(index,value){
                var id = value.id;
                $('#approval-list').append("<tr>"+"<td>"+value.id+"</td>"+"<td>"+value.firstName+"</td>"+"<td>"+value.lastName+"</td>"+
                    "<td>"+31+"</td>"+"<td>"+value.grade+"</td>"+"<td>"+value.section+"</td>"+"<td>"+value.phoneNumber+"</td>"+"<td>"+value.email+"</td>"+
                    '<td><button id="'+id+"assign"+'">'+"Onay"+"</button></td>"+'<td><button id="'+id+"delete"+'">'+"Sil"+"</button></td>")
                $('#'+id+"assign").on('click', function(event) {
                    $.ajax({
                        success : () =>{
                            console.log(id);
                        }
                    });
                });
                $('#'+id+"delete").on('click', function(event) {
                    $.ajax({
                        success : () =>{
                            console.log(id);
                        }
                    });
                });
            });
        }, // end of ajax success
        error : function (result){
            console.log('city ajax GET failed! with result : ', result);
        }
    }); // end of ajax GET cities
});


