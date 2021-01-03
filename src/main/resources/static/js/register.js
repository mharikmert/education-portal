const user = {};

postRegistrationRequest = () => {
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/register";
    xhr.open("POST",url,true);
    xhr.setRequestHeader("Content-Type","application/json");

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 3 && xhr.status === 200){
          alert('kaydınız alınmıştır..');
        }
    };
    //convert the data to json objects
    const data = JSON.stringify({
        "name": user.name,
        "surname": user.surname,
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
    user.name = document.querySelector('#user-name').value;
    user.surname = document.querySelector('#user-surname').value;

    postRegistrationRequest();
    
});
