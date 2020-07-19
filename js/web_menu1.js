var termExp,startDate,endDate;
getTermInputs = () => {
     termExp = document.querySelector('#term-exp').value;
     startDate = document.querySelector('#term-start-date').value;
     endDate = document.querySelector('#term-end-date').value;
}
const addTermButton = document.querySelector('#add-term-button');
addTermButton.addEventListener('click',function(){
    getTermInputs();
});
document.querySelectorAll('.term-input').forEach(item => {
    item.addEventListener('keypress',function(e){
        if(e.keyCode == 13){
            getTermInputs();
            document.querySelector('#term-exp-content').innerHTML = 'Dönem Açıklaması<br>' + termExp;
            document.querySelector('#term-start-date-content').innerHTML = 'Dönem Başlangıç Tarihi<br>' + startDate;
            document.querySelector('#term-end-date-content').innerHTML = 'Dönem Bitiş Tarihi <br>' + endDate;
        }     
    })
})