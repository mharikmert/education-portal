var firstYear,secondYear,expFirstYear,expSecondYear ;
var term = {
  explanation: '',
  startDate: '',
  endDate: '',
  count: 0
}
getTermInputs = () => {
     term.explanation = document.querySelector('#term-exp').value;
     term.startDate = document.querySelector('#term-start-date').value;
     term.endDate = document.querySelector('#term-end-date').value;
}
displayInfo = () => {
    document.querySelector('#term-exp-content').innerHTML = '<strong>Dönem Açıklaması</strong><br>' + term.explanation;
    document.querySelector('#term-start-date-content').innerHTML = '<strong>Dönem Başlangıç Tarihi</strong><br>' + term.startDate;
    document.querySelector('#term-end-date-content').innerHTML = '<strong>Dönem Bitiş Tarihi </strong><br>' + term.endDate;
    document.querySelectorAll('.term-input').forEach(item=>{item.value = '';});
    document.querySelector('#toggle-switch-container').style.display = 'flex';
    document.querySelector('#save-btn').style.display = 'block';
    document.querySelector('#create-class-btn').style.display = 'block';
}
termValidation = (input) => {
      var parseSlash = input.split('/'),parseDash = input.split('-');
      var day = parseSlash[0], month = parseSlash[1], year = parseSlash[2];
      var day_monthValidation = day > 0 && day <= 31 && month > 0 && month <= 12;
      switch(input){
        case term.explanation: expFirstYear = parseDash[0], expSecondYear = parseDash[1]; break;
        case term.startDate: firstYear = year; break;
        case term.endDate: secondYear = year; break;
      }
      return input === term.explanation ? parseDash[1]-parseDash[0] === 1 : day_monthValidation;
}
checkInfo = () => {
    getTermInputs();
    if(termValidation(term.explanation) && termValidation(term.startDate) && termValidation(term.endDate)){
        if(expFirstYear == firstYear && expSecondYear == secondYear){
            var date = new Date();
            if(!date.getFullYear() < firstYear){
              displayInfo();
              return true;
            }else alert('İleri tarihli bi dönem ekleyemezsiniz')
        }
        else alert('Lütfen dönem yılını kontrol ediniz!');
      }else alert('Lütfen dönem bilgilerini kontrol ediniz!');
    return false;
}
var termCounter = 1;
const addTermButton = document.querySelector('#add-term-button');
addTermButton.addEventListener('click',function(){
  if(checkInfo()){
    // cloneDiv();
    displayInfo();
    // addDiv();
  }
});
document.querySelectorAll('.term-input').forEach(item => {
    item.addEventListener('keypress',function(e){
        if(e.keyCode == 13){
          if(checkInfo()){
              // cloneDiv();
              displayInfo();
              // addDiv();
          }
        }
    })
})
cloneDiv = () => {
  var elem = document.querySelector('#existing-term-container')
  var clone = elem.cloneNode(true)
const setItem = (name, value) => localStorage.setItem(name,value) //sets the var to local storage
const removeItem = (name) => localStorage.removeItem(name) //removes from the local storage
const getItem = (item) => localStorage.getItem(item) // gets
