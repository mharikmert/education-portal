import { Component, OnInit } from '@angular/core';
import { ButtonComponent } from 'src/app/components/button/button.component';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  termButtonClicked : boolean = false
  faTimes = faTimes
  constructor() { }

  ngOnInit(): void {
  }

  terms = ['2018-2019', '2019-2020', '2020-2021']
  
  onClick(){
    console.log('clicked in admin')
    // this.termButtonClicked = !this.termButtonClicked
    this.termButtonClicked = true 
  }

}
