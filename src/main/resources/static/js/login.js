//current user object
const currentUser = {};

const postLoginRequest  = () => {
    const xhr = new XMLHttpRequest();
    const url = "/api/auth";
    xhr.open("POST",url, true);
    xhr.setRequestHeader("Authorization","Basic " + btoa(currentUser.id + ":" + currentUser.password))
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
    $('#login-spinner').css('display','inline-block');
    xhr.onreadystatechange = function (){
      //if response is ok, redirect the page
      if(xhr.status === 200){
        redirect("/menu"); // gives the mapping and redirect the page
      }else // else show warning acc to id and password
        errorMessage(currentUser.id, currentUser.password);
      $('#login-spinner').css('display', 'none');

    }
}

// login verification
const errorMessage = (userID,password) =>{
    if(userID.length === 0 || password.length === 0) showWarning('lack-of-data');
    else showWarning('wrong-data');
};

//event listener for all input divs, click function will be updated
document.querySelectorAll('.input').forEach(item => {
    item.addEventListener('keypress',function(e){
        if(e.keyCode === 13){ // enter key code
            // set the id and password of current id input value to current user
            currentUser.id = document.querySelector('#userID').value;
            currentUser.password = document.querySelector('#password').value;
            //post login data to the service
            postLoginRequest();
        }
    })
});

document.querySelector('#login-button').addEventListener('click', function (){
    currentUser.id = document.querySelector('#userID').value;
    currentUser.password = document.querySelector('#password').value;
    postLoginRequest();
});

//take the id property as parameter of warning place and display during 1.5 secs
const showWarning = (id) => {
    document.querySelector('#'+id).style.display = 'block';
    // warning place visibility timout
    setTimeout(() => {
    document.querySelector('#'+id).style.display = 'none';
},1000)
};

//redirect the page to given url
const redirect = (URL) => {
    window.location.href = URL;
    return true;
}

//toggle password visibility
const togglePassword = document.querySelector('#toggle-password');
const passwordX = document.querySelector('#password');

togglePassword.addEventListener('click',function(){
    //when clicked the eye, change the password visibility
    const type = passwordX.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordX.setAttribute('type',type);
    this.classList.toggle('fa-eye-slash');
});

