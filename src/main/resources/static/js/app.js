//current user object
const currentUser = {
    id: null,
    password: null
};

// use for registration part

//id validation method for turkish identity number
isValidUserID = (userID) => {
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

// user to registration part

// password validation, for strong passwords
isValidPassword = (password) => {
    let intCounter = 0, lowerCaseCounter = 0, upperCaseCounter = 0, elseCounter = 0;
    password = String(password);
    const passArr = password.substr(0, password.length).split('');
    for(let i = 0; i < passArr.length; i++){
        //upper case- lower case letter control
        const ch = password.charAt(i);
        if(ch >= 'A' && ch <= 'Z') upperCaseCounter++;
        else if(ch >= 'a' && ch <= 'z') lowerCaseCounter++;
        //integer control
        else if(ch >= '0' && ch <= '9') intCounter++;
        //if(Number.isInteger(parseInt(passArr[i]))) intCounter++;
        else elseCounter++; //it will be edited, which characters?
    }
    return password.length >= 6 && upperCaseCounter > 0 && lowerCaseCounter > 0 && intCounter > 0;

};

checkPassword = (password) => {
    /* TO DO
    * get password with get request and match info with database
    */

};

checkUserID = (userID) =>{
    //get user id from db
};

//get request to the service for login verification
getRequest = () => {
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/users";
    // open a synchronous request
    xhr.open("GET",url,false);
    xhr.send(null);
    // assign the response to a const and parse
    const responseJson = xhr.responseText;
}


postRegistrationRequest = () => {
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/users";
    xhr.open("POST",url,true);
    xhr.setRequestHeader("Content-Type","application/json");

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 3 && xhr.status === 200){
          //redirect the page
        }
    };
    //convert the data to json objects
    const data = JSON.stringify({
        "id": currentUser.id,
        "password": currentUser.password
    });
    xhr.send(data);
}

postLoginRequest = () => {
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/login";
    xhr.open("POST",url, true);
    xhr.setRequestHeader("Content-Type", "application/json");

    const data = JSON.stringify({
        "id" : currentUser.id,
        "password" : currentUser.password
    });
    xhr.send(data);

    xhr.onreadystatechange = function (){
      //if response is ok, redirect the page
      if(xhr.readyState ===3 && xhr.status === 200){
        redirect("../text/web_menu.html");
      }else // else show warning acc to id and password
        loginVerification(currentUser.id, currentUser.password);
    }
}

// login verification
loginVerification = (userID,password) =>{
    if(userID === 0 || password === 0) showWarning('lack-of-data');
    else if(!((isValidPassword(password)) && isValidUserID(userID)))
        showWarning('wrong-data');
    else return true;
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
showWarning = (id) => {
    document.querySelector('#'+id).style.display = 'block';
    // warning place visibility timout
    setTimeout(() => {
    document.querySelector('#'+id).style.display = 'none';
    },1500)
};


//redirect the page to given url
redirect = (URL) => {
    window.location.href = URL;
    return true;
};

//toggle password visibility
const togglePassword = document.querySelector('#toggle-password');
const passwordX = document.querySelector('#password');

togglePassword.addEventListener('click',function(){
    //when clicked the eye, change the password visibility
    const type = passwordX.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordX.setAttribute('type',type);
    this.classList.toggle('fa-eye-slash');
});

document.querySelector('#forgot-password').addEventListener('click', function(){
    redirect('../text/forgot-password-page.html');
});

document.querySelector('#create-password').addEventListener('click', function(){
    redirect('../text/register.html');
});
