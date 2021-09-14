import { HashLocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Term } from 'src/app/models/Term';
import { TermService } from 'src/app/services/term.service';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
    styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  termButtonClicked : boolean = false
  term: Term = {};
  terms: Term [] = []
  isTermActive: boolean = false;
  missingTermInfo: boolean = false; 

  constructor(private termService: TermService) { }

  ngOnInit(): void  {
     this.loadPage();
  }

  loadPage() {
    this.getAdmin();
    this.getTerms();
  }

  onClick(){
    this.termButtonClicked = true
  }

  getAdmin = () => this.termService.getAdmin().subscribe(resp => console.log(resp));

  addTerm(term: Term){
    if(Object.keys(term).length !== 3){
      this.missingTermInfo = true; 
      setTimeout( () => {
        this.missingTermInfo = false; 
      }, 2000)
      return;
    }
    this.termService.addTerm(term).subscribe(
      term => this.terms.push(term)
    );
  }

  getTerms = () => this.termService.getTerms().subscribe(terms => this.terms = terms);

  missingTermWarningMessage(){
    return 'Detailed warnings here'
  }

  //should be able to call from modal component
  saveChanges = () => {
    //this.termService.updateTerms(terms : Term[]) ?; 
    console.log(this.terms)
  }


}
