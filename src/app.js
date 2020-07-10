var userID,password,branch;
var inputList = [userID,password,branch];
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
getElement = (id) =>{
    return document.querySelector(id);
}
checkPassword = (password) => {
    //this part is after from DB
}
checkUserID = (userID) =>{
    //this part is also after from DB
}
loginVerification = (userID,password,branch) =>{
    if(userID == 0 || password == 0) showWarning('lack-of-data')
    else if(branch == 0) showWarning('branch-warn')
    else if(isValidUserID(userID) && isValidPassword(password)) return true; 
    else showWarning('wrong-data');
    return false;
}
getInputValues = (userID,password,branch) =>{
    //not sure about returning a inputList and check it like that
}
getElement("#login-button").addEventListener('click',function() {
    userID = getElement('#userID').value;   
    password = getElement('#password').value;   
    branch = getElement('#branch').value;   
    if(loginVerification(userID,password,branch))
        redirect('web_menu1.html');
});
document.querySelectorAll('.input').forEach(item => {
    item.addEventListener('keypress',function(e){
    userID = getElement('#userID').value;   
    password = getElement('#password').value;   
    branch = getElement('#branch').value;
    if(e.keyCode == 13){
        console.log(userID,password,branch);
        if(loginVerification(userID,password,branch))
            redirect('web_menu1.html');
        }
    })
})
showWarning = (id) => {
    getElement('#'+id).style.display = 'block';
    setTimeout(() => {
    getElement('#'+id).style.display = 'none';
    },1500)
}   
redirect = (URL) => {
    window.location.href = URL;
}
//toggle password visibility
const togglePassword = getElement('#toggle-password');
const passwordX = getElement('#password');
togglePassword.addEventListener('click',function(){
    const type = passwordX.getAttribute('type') === 'password' ? 'text' : 'password';
    passwordX.setAttribute('type',type);
    this.classList.toggle('fa-eye-slash');
})
 /*To Do
-> mobile compatibility, uh!
*/