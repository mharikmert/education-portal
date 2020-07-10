var userID,password,branch;
checkUserID = (userNo) => {
    userNo = String(userNo);
    if (userNo.substring(0, 1) === '0') {
        return false;
    }
    if (userNo.length !== 11) {
        return false;
    }
    var tenTotalArray = userNo.substr(0, 10).split('');
    var tenTotal = odd = even = 0;

    for (var i = j = 0; i < 9; ++i) {
      j = parseInt(tenTotalArray[i], 10);
        if (i & 1) even  += j;
        else odd += j;
      tenTotal += j;
    }
    if ( (odd * 7 -even ) % 10 !== parseInt(userNo.substr(-2, 1), 10)) {
        return false;
    }
    tenTotal += parseInt(tenTotalArray[9], 10); 
    if (tenTotal % 10 !== parseInt(userNo.substr(-1), 10)) {
        return false;
    }
    return true;
}
checkPassword = (password) => {
    //this part is after from DB
}
document.querySelector("#login-button").addEventListener('click',function() {
    userID = document.querySelector('#userID').value;
    password = document.querySelector('#password').value;
    branch = document.querySelector('#branch').value;
    //console.log(userIDCheck(userID));
    if(userID == 0 || password == 0) showWarning('lack-of-data')
    else if(branch == 0) showWarning('branch-warn')
    else{
     if(checkUserID(userID) && isValidPassword(password)){
        redirect('web_menu1.html');
        console.log(isValidPassword(password));
     }else showWarning('wrong-data');  
    }
});
showWarning = (id) => {
    document.querySelector('#'+id).style.display = 'block';
    setTimeout(() => {
    document.querySelector('#'+id).style.display = 'none';
    },1500)
}   
redirect = (URL) => {
    window.location.href = URL;
}
isValidPassword = (password) => {
    /*at least 6 chars
    *both number and letter for now
    */
    var intCounter = 0,lowerCaseCounter= 0,upperCaseCounter = 0,elseCounter = 0;
    password = String(password);
    var passArr = password.substr(0,password.length).split('');
    console.log(passArr);
    for(var i = 0; i < passArr.length; i++){
        //upper case- lower caseletter control
        var ch = password.charAt(i);
        if(ch >= 'A' && ch <= 'Z') upperCaseCounter++;
        else if(ch >= 'a' && ch <= 'z') lowerCaseCounter++;
        //integer control
        else if(ch >= '0' && ch <= '9') intCounter++;
        //if(Number.isInteger(parseInt(passArr[i]))) intCounter++;
        else elseCounter++; //it will be edited, which characters?
    }
    if(password.length >= 6 && upperCaseCounter > 0 && lowerCaseCounter > 0 && intCounter > 0)
        return true;
    //console.log('total integer count in password: ' + intCounter)
    return false;
}
const togglePassword = document.querySelector('#toggle-password');
const passwordX = document.querySelector('#password');
togglePassword.addEventListener('click',function(){
    const type = passwordX.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordX.setAttribute('type',type);
    this.classList.toggle('fa-eye-slash');
})
e /*To Do
-> mobile compatibility, uh!
*/