var termExp,startDate,endDate
getTermInputs = () => {
     termExp = document.querySelector('#term-exp').value;
     startDate = document.querySelector('#term-start-date').value;
     endDate = document.querySelector('#term-end-date').value;
}
displayInfo = () => {
    document.querySelector('#term-exp-content').innerHTML = '<strong>Dönem Açıklaması</strong><br>' + termExp;
    document.querySelector('#term-start-date-content').innerHTML = '<strong>Dönem Başlangıç Tarihi</strong><br>' + startDate;
    document.querySelector('#term-end-date-content').innerHTML = '<strong>Dönem Bitiş Tarihi </strong><br>' + endDate;
    document.querySelector('.switch').style.display = 'block';
    document.querySelector('#create-class-btn').style.display = 'block';
    document.querySelectorAll('.term-input').forEach(item=>{item.value = '';});
}
termValidation = () => {
    /*
    //to do
    ->inputs are ok acc to current time?
    ->if not, warnings.
    */
}
const addTermButton = document.querySelector('#add-term-button');
addTermButton.addEventListener('click',function(){
    getTermInputs();
    displayInfo();
});
document.querySelectorAll('.term-input').forEach(item => {
    item.addEventListener('keypress',function(e){
        if(e.keyCode == 13){
            getTermInputs();
            displayInfo();
        }
    })
})
/*
//to do
->adding one term div under the other term div
    respectively. 
*/
