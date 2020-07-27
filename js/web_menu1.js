var termExp,startDate,endDate,termYear1,termYear2;
getTermInputs = () => {
     termExp = document.querySelector('#term-exp').value;
     startDate = document.querySelector('#term-start-date').value;
     endDate = document.querySelector('#term-end-date').value;
}
displayInfo = () => {
    document.querySelector('#term-exp-content').innerHTML = '<strong>Dönem Açıklaması</strong><br>' + termExp;
    document.querySelector('#term-start-date-content').innerHTML = '<strong>Dönem Başlangıç Tarihi</strong><br>' + startDate;
    document.querySelector('#term-end-date-content').innerHTML = '<strong>Dönem Bitiş Tarihi </strong><br>' + endDate;
    document.querySelectorAll('.term-input').forEach(item=>{item.value = '';});
    document.querySelector('#toggle-switch-container').style.display = 'flex';
    document.querySelector('#create-class-btn').style.display = 'block';
}
termValidation = (input) => {
      var parse = input.split('/');
      var day = parse[0], month = parse[1], year = parse[2];
      var parse2 = input.split('-');
      if(parse2[1] !== undefined){
        var firstY = parse2[0], secondY = parse2[1];
      }
      return day > 0 && day <= 31 && month > 0 && month <= 12;
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
->adding one term div under the other term div respectively.
*/
