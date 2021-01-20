// import { redirect } from "./app.js";

const user = {};

const postRegistrationRequest = () => {
    const xhr = new XMLHttpRequest();
    const url = "/api/register"; // localhost:8080 not needed
    xhr.open("POST",url,true);
    xhr.setRequestHeader("Content-Type","application/json");

    xhr.onreadystatechange = function(){
        if(xhr.status === 200){
            alert('kaydınız alınmıştır..');
            // redirect("/main");
        }
    };
    //convert the data to json objects
    const data = JSON.stringify({
        "first_name": user.first_name,
        "last_name": user.last_name,
        "id": user.id,
        "password": user.password,
        "phone_number": user.phone_number,
        "city": user.city,
        "address": user.address
    });
    console.log(user);
    xhr.send(data);
}

document.querySelector('#register-button').addEventListener('click', function(){
    user.id = document.querySelector('#userID').value;
    user.password = document.querySelector('#password').value;
    user.address = document.querySelector('#user-address').value;
    user.phone_number = document.querySelector('#user-phone-number').value;
    user.city = document.querySelector('#user-city').value;
    user.first_name = document.querySelector('#user-name').value;
    user.last_name = document.querySelector('#user-surname').value;

    postRegistrationRequest();

});
