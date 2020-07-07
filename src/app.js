var userID,password,branch;
function userIDCheck(userNo){
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
document.querySelector("#login-button").addEventListener('click',function(){
    userID = document.querySelector('#userID').value;
    password = document.querySelector('#password-field').value;
    branch = document.querySelector('#branch').value;
    console.log('user no: ' + userID + '\npassword: ' + password + '\n' + branch);
    console.log(userIDCheck(userID));
    if(userID == 0 || password == 0) showWarning('lack-of-data')
    else if(branch == 0) showWarning('branch-warn')
    else{
     if(userIDCheck(userID)){
        redirect('directionPage.html');
     }else showWarning('wrong-data');  
    }
});
function showWarning(id){
    document.querySelector('#'+id).style.display = 'block';
    setTimeout(() => {
    document.querySelector('#'+id).style.display = 'none';
    },1500)
}   
function redirect(url){
    window.location.href = url;
}
