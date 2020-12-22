var inputList = [''];
isValidUserID = (userID) => {
    userID = String(userID);
    if (userID.substring(0, 1) === '0') return false;
    if (userID.length !== 11) return false;

    var tenTotalArray = userID.substr(0, 10).split('');
    var tenTotal = odd = even = 0;

    for (var i = j = 0; i < 9; ++i) {
      j = parseInt(tenTotalArray[i], 10);
        if (i & 1) even  += j;
        else odd += j;
      tenTotal += j;
    }
    if ( (odd * 7 -even ) % 10 !== parseInt(userID.substr(-2, 1), 10)) {
        return false;
    }
    tenTotal += parseInt(tenTotalArray[9], 10);
    if (tenTotal % 10 !== parseInt(userID.substr(-1), 10)) {
        return false;
    }
    return true;
};
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
getElement = (id) =>{
    return document.querySelector(id);
};
checkPassword = (password) => {
    //this part is after from DB
};
checkUserID = (userID) =>{
    //this part is also after from DB
};
postRequest = (userID, password) => {
    console.log("in post request");
    const xhr = new XMLHttpRequest();
    const url = "http://localhost:8080/api/users";
    xhr.open("POST",url,true);
    xhr.setRequestHeader("Content-Type","application/json");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 3 && xhr.status === 200){
            const json = JSON.parse(xhr.responseText);
            console.log(json.id,json.password);
        }
    };
    const data = JSON.stringify({
        "id": inputList[0],
        "password": inputList[1]
    });
    xhr.send(data);
}
loginVerification = (userID,password) =>{
    if(userID === 0 || password === 0) showWarning('lack-of-data');
    else if(isValidUserID(userID) && isValidPassword(password)){
        postRequest(userID, password);
    }
    else showWarning('wrong-data');
};
getInputValues = () =>{
    inputList[0] = getElement('#userID').value;
    inputList[1] = getElement('#password').value;
};
getElement("#login-button").addEventListener('click',function() {
    getInputValues();
    loginVerification(inputList[0],inputList[1]);
});
document.querySelectorAll('.input').forEach(item => {
    item.addEventListener('keypress',function(e){
    getInputValues();
    if(e.keyCode === 13)//enter key code
        loginVerification(inputList[0],inputList[1],inputList[2]);
    })
});
showWarning = (id) => {
    getElement('#'+id).style.display = 'block';
    setTimeout(() => {
    getElement('#'+id).style.display = 'none';
    },1500)
};
redirect = (URL) => {
    window.location.href = URL;
    return true;
};
//toggle password visibility
const togglePassword = getElement('#toggle-password');
const passwordX = getElement('#password');
togglePassword.addEventListener('click',function(){
    const type = passwordX.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordX.setAttribute('type',type);
    this.classList.toggle('fa-eye-slash');
});
document.querySelector('#forgot-password').addEventListener('click', function(){
    redirect('../text/forgot-password-page.html');
});
document.querySelector('#create-password').addEventListener('click', function(){
    redirect('../text/create-password-page.html');
});

















