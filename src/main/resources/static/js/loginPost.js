$(document).ready(function() {
    $("#login-button").click(function() {
        console.log("i am in the jquery")
        const loginInfo = {
            id: $("userID"),
            password: $("password")
        };
        console.log(loginInfo)
        const requestJSON = JSON.stringify(loginInfo);
        $.ajax({
            type : "POST",
            url : "http://localhost:8080/api/users",
            headers : {
                "Content-Type" : "application/json; charset=utf-8"
            },
            data : requestJSON,
            success : function(data) {
                console.log("i posted data")
            },
            error : function(data) {
                console.log("data is not received")
            }
        });
    });
});