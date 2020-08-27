var termExp,startDate,endDate,firstYear,secondYear,expFirstYear,expSecondYear ;
var iter = 25;
var temp = 77;
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
      var parseSlash = input.split('/'),parseDash = input.split('-');
      var day = parseSlash[0], month = parseSlash[1], year = parseSlash[2];
      var day_monthValidation = day > 0 && day <= 31 && month > 0 && month <= 12;
      switch(input){
        case termExp: expFirstYear = parseDash[0], expSecondYear = parseDash[1]; break;
        case startDate: firstYear = year; break;  //global variables assignment
        case endDate: secondYear = year; break;
      }
      return input === termExp ? parseDash[1]-parseDash[0] === 1 : day_monthValidation;
}
checkInfo = () => {
    getTermInputs();
    if(termValidation(termExp) && termValidation(startDate) && termValidation(endDate)){
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
const addTermButton = document.querySelector('#add-term-button');
addTermButton.addEventListener('click',function(){
    checkInfo();
});
document.querySelectorAll('.term-input').forEach(item => {
    item.addEventListener('keypress',function(e){
        if(e.keyCode == 13){
          iter += 25;
          temp += 70;
            div.style.marginTop= temp + 'px'
            createClass.style.marginTop = iter + '%'
            checkInfo()
        }
    })
})
displayInfo()
const setItem = (name, value) => localStorage.setItem(name,value) //sets the var to local storage
const removeItem = (name) => localStorage.removeItem(name) //removes from the local storage
const getItem = (item) => localStorage.getItem(item) // gets

var div = document.querySelector('#existing-term-container')
var createClass = document.querySelector('#create-class-btn')
