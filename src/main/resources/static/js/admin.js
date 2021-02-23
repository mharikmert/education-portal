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
                if (value["approved"] === false){
                    var id = value.id;
                    $('#approval-list').append("<tr>"+"<td>"+value.id+"</td>"+"<td>"+value.firstName+"</td>"+"<td>"+value.lastName+"</td>"+
                        "<td>"+31+"</td>"+"<td>"+value.grade+"</td>"+"<td>"+value.section+"</td>"+"<td>"+value.phoneNumber+"</td>"+"<td>"+value.email+"</td>"+
                        '<td><button  onclick="SomeDeleteRowFunction(this)" id="'+id+"assign"+'">'+"Onay"+"</button></td>"+'<td><button  onclick="SomeDeleteRowFunction(this)" id="'+id+"delete"+'">'+"Sil"+"</button></td>"+"<tr>")
                    $('#'+id+"assign").on('click', function() {
                        $.ajax({
                            url: '/api/approveUser/'+id, // contains all the cities
                            type: 'POST',
                            dataType : 'json',
                            headers : {
                                'Content-Type' : 'application/json; charset=utf-8'
                            },
                        });
                    });

                    $('#'+id+"delete").on('click', function() {
                        $.ajax({
                            url: 'api/rejectUser/'+id,
                            type: 'POST',
                            dataType: 'json',
                            headers: {
                                'Content-Type': 'application/json; charset=utf-8'
                            }
                        })

                    });
                }
            });
        }, // end of ajax success
        error : function (result){
            console.log('city ajax GET failed! with result : ', result);
        }
    }); // end of ajax GET cities
});


function SomeDeleteRowFunction(o) {
    const p = o.parentNode.parentNode;
    p.parentNode.removeChild(p);
}