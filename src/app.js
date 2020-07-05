var showWarning = false;
function userNoCheck(userNo){
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
/*document.getElementById("warn_userID").style.display = 'none';
document.getElementById("warn_location").style.display = 'none';
document.getElementById("entressMessage").style.display = 'none';

*/
function showWarning(showWarning){
    
} 
/*document.querySelector("#button").addEventListener('click',function(){
    var userNo = document.querySelector('#user_ID').value;
    if(userNoCheck(userNo)){
        var location = document.querySelector('#select').value;
        if(location != 0){
            document.querySelector('#entressMessage').style.display = 'block';
            }else  document.querySelector('#warn_location').style.display = 'block';  
    }else {
        document.querySelector('#warn_userID').style.display = 'block';
    }
});*/
var buttonDom = document.querySelector('#enterButton');
function redirect(URL){
    console.log("clicked..")
    window.location.replace(URL);
}
buttonDom.addEventListener('click',function(){
    console.log('clicked..')
});
/*To Do
-->İnputs connection with each other..get rid of the surplus
-->Warning messages will be done
-->Click event and redirection
*/