import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-login-error',
  templateUrl: './login-error.component.html',
  styleUrls: ['./login-error.component.css']
})
export class LoginErrorComponent implements OnInit {
  @Input() errorType?: string; 
  constructor() { }

  ngOnInit(): void {}

  warningMessage () {
    return this.errorType === 'empty-data' ? 'Lütfen Bilgilerinizi Giriniz' : 'Kullanıcı adınız ya da şifreniz hatalı!'
  }
}
